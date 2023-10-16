package ejerciciosParcial2.ejercicio2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Ejercicio2 {
    ChromeDriver chrome;
    String email = "priset@gmail.com";
    String password = "priset1234";
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("https://app.todoist.com");
    }

    @Test
    public void verifyLoginTest() throws InterruptedException {
        // set email
        chrome.findElement(By.id("element-0")).click();
        chrome.findElement(By.id("element-0")).sendKeys(email);
        // set password
        chrome.findElement(By.id("element-3")).click();
        chrome.findElement(By.id("element-3")).sendKeys(password);
        // click login button
        chrome.findElement(By.xpath("//button[@class='F9gvIPl rWfXb_e _8313bd46 _7a4dbd5f _95951888 _2a3b75a1 _8c75067a']")).click();

        WebElement userElement = chrome.findElement(By.id(":r0:"));
        Assertions.assertNotNull(userElement, "ERROR: El inicio de sesión falló");

        Thread.sleep(5000);
    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }
}
