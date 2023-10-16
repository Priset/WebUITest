package ejerciciosParcial2.ejercicio1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class ejercicio1 {
    ChromeDriver chrome;

    @BeforeEach
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }

    @Test
    public void createUpdateDeleteProject() throws InterruptedException {
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("priset@gmail.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("priset1234");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout

        /* CREATE PROJECT*/
        // click add new project
        String nameProject = "WEBTEST"+new Date().getTime();

        chrome.findElement(By.xpath("//td[text()='Add New Project']")).click();
        // fill name
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        // click save
        chrome.findElement(By.id("NewProjNameButton")).click();
        //verificat que se creo el elemento
        Thread.sleep(2000);
        String actualResult= chrome.findElement(By.id("CurrentProjectTitle")).getText();
        String expectedResult= nameProject;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el projecto no se creo");

        //CREAR ITEM
        String nameItem = "WebUiTesting";

        List<WebElement> projects = chrome.findElements(By.xpath(String.format("//tr[td[@class='ProjItemContent' and text()='%s']]", nameProject)));
        Assertions.assertFalse(projects.isEmpty(), "ERROR: No se encontró el proyecto a editar");

        WebElement lastProjectCreated = projects.get(projects.size() - 1);
        lastProjectCreated.click();

        chrome.findElement(By.id("NewItemContentInput")).click();
        chrome.findElement(By.id("NewItemContentInput")).sendKeys(nameItem);
        chrome.findElement(By.id("NewItemAddButton")).click();

        // Verificar que el elemento se haya creado correctamente
        List<WebElement> items = chrome.findElements(By.xpath(String.format("//tr[td/div[@class='ItemContentDiv' and text()='%s']]", nameItem)));
        Assertions.assertFalse(items.isEmpty(), "ERROR: No se encontró el item creado");


        //UPDATE ITEM
        String newNameItem = "TestingDone";

        WebElement lastItemCreated = items.get(projects.size() - 1);
        lastItemCreated.click();
        chrome.findElement(By.id("ItemEditTextbox")).clear();
        chrome.findElement(By.id("ItemEditTextbox")).sendKeys(newNameItem);

        // Verificar que el elemento se haya actualizado correctamente
        List<WebElement> updatedItems = chrome.findElements(By.xpath(String.format("//tr[td/div[@class='ItemContentDiv' and text()='%s']]", newNameItem)));
        Assertions.assertFalse(updatedItems.isEmpty(), "ERROR: No se encontró el elemento actualizado");


        Thread.sleep(2000);
    }

    @AfterEach
    public void closeBrowser() {
        chrome.quit();
    }
}

