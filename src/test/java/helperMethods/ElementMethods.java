package helperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementMethods {

    public WebDriver driver;

    //Constructor
    public  ElementMethods(WebDriver driver){
        this.driver = driver;
    }

    //Metode generale pentru interactiunea cu elemente

    public void waitForElementVisible(WebElement element){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // asteapta max 10 sec
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    public void clickElement(WebElement element){

        waitForElementVisible(element);
        element.click();

    }

    public void fillElement(WebElement element, String text){
        waitForElementVisible(element);
        element.sendKeys(text);
    }

}
