package helperMethods;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WindowsMethods {

    public WebDriver driver;

    //Constructor
    public WindowsMethods(WebDriver driver){
        this.driver = driver;
    }

    //Metode generale pentru interactiunea cu tab/window

    public void switchSpecificTab(Integer index){

        List <String> window = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(driver.get(index));
    }

    public void closeCurrentTab(){
        driver.close();
    }
}
