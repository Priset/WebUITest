package ejerciciosParcial2.ejercicio3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Ejercicio3 {
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
    public void editFullName() throws InterruptedException {
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("priset@gmail.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("priset1234");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout
        Assertions.assertTrue((chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1),
                "ERROR no se pudo ingresar a la sesion");

        //EDIT FULL NAME
        String updateName = "Ganzo";

        chrome.findElement(By.xpath(String.format("//div/a[@href='javascript:OpenSettingsDialog();']"))).click();
        chrome.findElement(By.id("FullNameInput")).clear();
        chrome.findElement(By.id("FullNameInput")).sendKeys(updateName);
        chrome.findElement(By.xpath(String.format("//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only']"))).click();
        Thread.sleep(5000);
    }



    @AfterEach
    public void closeBrowser() {
        chrome.quit();
    }
}
