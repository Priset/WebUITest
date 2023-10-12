package taskTodoLyTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class CreateUpdateTodoLyTest {
    private ChromeDriver chrome;
    private final String nombreProyecto = "Ganzo3";
    private final String nuevoNombreProyecto = "Tuqui";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        chrome.get("http://todo.ly/");
        login();
    }

    private void login() {
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("priset@gmail.com");
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("priset1234");
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Assertions.assertTrue(chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1, "ERROR: No se pudo ingresar a la sesión");
    }

    @Test
    public void verifyCreateProject() throws InterruptedException {
        chrome.findElement(By.xpath("//div[@class=\"AddProjectLiDiv\"]")).click();
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nombreProyecto);
        chrome.findElement(By.id("NewProjNameButton")).click();

        List<WebElement> projects = chrome.findElements(By.xpath(String.format("//td[text()='%s']", nombreProyecto)));
        Assertions.assertTrue(projects.size() > 0, "ERROR: No existe un proyecto con ese nombre");
    }

    @Test
    public void verifyUpdateProject() {
        List<WebElement> projects = chrome.findElements(By.xpath(String.format("//tr[td[@class='ProjItemContent' and text()='%s']]", nombreProyecto)));
        Assertions.assertFalse(projects.isEmpty(), "ERROR: No se encontró el proyecto a editar");

        WebElement lastProjectCreated = projects.get(projects.size() - 1);
        lastProjectCreated.click();
        lastProjectCreated.findElement(By.xpath(".//div[@class='ProjItemMenu']/img")).click();

        chrome.findElement(By.xpath("//ul[@id=\"projectContextMenu\"]/li[@class='edit']")).click();
        WebElement projectNameInput = chrome.findElement(By.id("ItemEditTextbox"));
        projectNameInput.clear();
        projectNameInput.sendKeys(nuevoNombreProyecto);
        chrome.findElement(By.id("ItemEditSubmit")).click();

        List<WebElement> updatedProjects = chrome.findElements(By.xpath(String.format("//td[text()='%s']", nuevoNombreProyecto)));
        Assertions.assertTrue(updatedProjects.size() > 0, "ERROR: El proyecto no fue actualizado correctamente");
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        chrome.quit();
    }
}
