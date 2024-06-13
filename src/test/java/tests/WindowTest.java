package tests;

import helperMethods.ElementMethods;
import helperMethods.PageMethods;
import helperMethods.WindowsMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WindowTest {


    public WebDriver driver;

    @Test
    public void metodaTest() {

        //deschidem un browser
        driver = new ChromeDriver();

        //accesam un anumit URL
        driver.get("https://demoqa.com/");

        //facem browserul in mod maximize
        driver.manage().window().maximize();

        //definim un obiect de tipul window Methods
        WindowsMethods windowsMethods = new WindowsMethods(driver);
        ElementMethods elementMethods = new ElementMethods(driver);

        //facem un scroll la pagina pentru vizibilitate
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 350)", "");
        PageMethods pageMethods = new PageMethods();
        pageMethods.scrollPage(0, 500);

        //interactionam cu meniul/submeniul de pe site
        WebElement alertMenu = driver.findElement(By.xpath("//h5[text()='Alerts, Frame & Windows']"));
        //alertMenu.click();
        elementMethods.clickElement(alertMenu);

        WebElement browserWindows = driver.findElement(By.xpath("//span[text()='Browser Windows']"));
        browserWindows.click();

        WebElement newTab = driver.findElement(By.id("tabButton"));
        js.executeScript("arguments[0].click();", newTab);

        //interactionam cu un tab
        System.out.println(driver.getTitle()); // titlul paginii curente pe care

        //List<String> tabs = new ArrayList<>(driver.getWindowHandles()); // gaseste cate tab/windowuri gaseste in timplu
        //nue mutam cu focusul pe un anumit tab
        //driver.switchTo().window(tabs.get(1));

        windowsMethods.switchSpecificTab(1);
        System.out.println(driver.getCurrentUrl());

        //inchidem tab-ul curent
       // driver.close();
       // driver.switchTo().window(tabs.get(0));


        js.executeScript("window.scrollBy(0, 350)", "");
        WebElement newWindow = driver.findElement(By.id("windowButton"));
        newWindow.click();

        System.out.println(driver.getCurrentUrl());
        List<String> window = new ArrayList<>(driver.getWindowHandles());
        //Ne mutam cu focusul pe un anumit tab
        driver.switchTo().window(window.get(1));
        System.out.println(driver.getCurrentUrl());

        driver.close();
        driver.quit();

    }

}


