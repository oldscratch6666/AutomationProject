package tests;

import helperMethods.AlertMethods;
import helperMethods.ElementMethods;
import helperMethods.PageMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertTests {

    public WebDriver driver;

    @Test
    public void metodaTest() {

        //deschidem un browser
        driver = new ChromeDriver();

        //accesam un anumit URL
        driver.get("https://demoqa.com/");

        //facem browserul in mod maximize
        driver.manage().window().maximize();

        //wait implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //definim un obiect de tip alert methods ca sa apelam metodele generale din clasa
        AlertMethods alertMethods = new AlertMethods(driver);
        ElementMethods elementMethods = new ElementMethods(driver);


        //facem un scroll la pagina pentru vizibilitate
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 350)", "");

        //interactionam cu meniul/submeniul de pe site
        WebElement alertMenu = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        alertMenu.click();

        WebElement alertsSubmenu = driver.findElement(By.xpath("//span[text()='Alerts']"));
        alertsSubmenu.click();

        //Interactionam cu prima alerta
        WebElement firstAlertButton = driver.findElement(By.id("alertButton"));
        firstAlertButton.click();

        alertMethods.acceptAlert();
/*
        //interactionam cu o alerta in general(nu poate fi dat inspect pe ea)
        Alert alertOK = driver.switchTo().alert();
        //System.out.println(alertOK.getText());
        alertOK.accept();
*/

        alertMethods.dismissAlert();

        //interactionam cu a 3-a alerta
/*        WebElement thirdAlertButton = driver.findElement(By.id("confirmButton"));
        thirdAlertButton.click();
        Alert alertConfirm = driver.switchTo().alert();
        alertConfirm.accept();
*/

        //interactionam cu a 4-a alerta
        WebElement forthdAlertButton = driver.findElement(By.id("promtButton"));
        forthdAlertButton.click();
        String alertValue = "Dorel";
        Alert alertPromt = driver.switchTo().alert();
        alertPromt.sendKeys(alertValue);
        alertPromt.accept();

        //interactionam cu o alerta pentru care trebuie sa asteptam putin
        WebElement alertWait = driver.findElement(By.id("timerAlertButton"));
        alertWait.click();

        alertMethods.acceptAlert();

        /*
        //definim un wait explicit
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.alertIsPresent());
        */

        alertMethods.fillAlert("Adevarat");


        Alert waitConfirm = driver.switchTo().alert();
        System.out.println(waitConfirm.getText());
        waitConfirm.accept();

   }

}
