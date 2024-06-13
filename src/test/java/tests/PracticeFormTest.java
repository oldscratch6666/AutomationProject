package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class PracticeFormTest {

    public WebDriver driver;

    @Test
    public void metodaTest() {

        //deschidem un browser
        driver = new ChromeDriver();

        //accesam un anumit URL
        driver.get("https://demoqa.com/");

        //facem browserul in mod maximize
        driver.manage().window().maximize();

        //facem un scroll la pagina pentru vizibilitate
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 350)", "");

        //interactionam cu meniul/submeniul de pe site
        WebElement formMenu = driver.findElement(By.xpath("//h5[text()='Forms']"));
        formMenu.click();

        WebElement practiceFormSubmenu = driver.findElement(By.xpath("//span[text()='Practice Form']"));
        practiceFormSubmenu.click();

        //firstName
        WebElement firstNameElement = driver.findElement(By.id("firstName"));
        String firstNameValue = "Alabala";
        firstNameElement.sendKeys(firstNameValue);

        //lastName
        WebElement lastNameElement = driver.findElement(By.id("lastName"));
        String lastNameValue = "Portocala";
        lastNameElement.sendKeys(lastNameValue);

        //userEmail
        WebElement emailElement = driver.findElement(By.id("userEmail"));
        String emailValue = "shalala@yahoo.com";
        emailElement.sendKeys(emailValue);

        //input[@name='gender']
        //definim o logica generala de a selecta un element dintr-o lista
        List<WebElement> genderElements = driver.findElements(By.xpath("//div[@id='genterWrapper']/div/div/label[@class='custom-control-label']"));
        String genderValue = "Male";
        switch (genderValue){
            case "Male":
                genderElements.get(0).click();
                break;
            case "Female":
                genderElements.get(1).click();
                break;
            case "Other":
                genderElements.get(2).click();
                break;
        }

        //userNumber
        WebElement mobileNumberElement = driver.findElement(By.id("userNumber"));
        String mobileNumberValue = "0754225852";
        mobileNumberElement.sendKeys(mobileNumberValue);

        //Date of birth
        WebElement dateOfBirthElement = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthElement.click();
        WebElement dateOfBirthMonthElement = driver.findElement(By.className("react-datepicker__month-select"));
        Select monthSelect =  new Select(dateOfBirthMonthElement);
        monthSelect.selectByVisibleText("September");

        WebElement dateOfBirthYearElement = driver.findElement(By.className("react-datepicker__year-select"));
        Select yearSelect =  new Select(dateOfBirthYearElement);
        yearSelect.selectByValue("2000");

        List<WebElement> weekDaysElements = driver.findElements(By.xpath("//div[@class='react-datepicker__month']//div[not(contains(@class,'--outside-month')) and @role='option']"));
        String weekDaysValue = "15";
        for (Integer index = 0; index < weekDaysElements.size(); index++){
            if (weekDaysElements.get(index).getText().equals(weekDaysValue)) {
                weekDaysElements.get(index).click();
                break;
            }

        }

        //subjectInput
        WebElement subjectInputElement = driver.findElement(By.id("subjectsInput"));
        String subjectInputValue = "Accounting";
        subjectInputElement.sendKeys(subjectInputValue);
        subjectInputElement.sendKeys(Keys.ENTER);

        List<WebElement> hobbiesElements = driver.findElements(By.xpath("//div[@id = 'hobbiesWrapper']/div/div/label[@class='custom-control-label']"));
        List<String> hobbiesValues = Arrays.asList("Sports", "Reading", "Music");

        js.executeScript("window.scrollBy(0,360)" , "");
        for (Integer index = 0; index<hobbiesElements.size(); index++){
            String hobbieText = hobbiesElements.get(index).getText();
            if (hobbiesValues.contains(hobbieText)){
                JavascriptExecutor jsclick = (JavascriptExecutor) driver;
                jsclick.executeScript("arguments[0].click();", hobbiesElements.get(index));
            }
        }

        //interactionam cu un fisier: uploadPicture
        WebElement uploadPicturelement = driver.findElement(By.id("uploadPicture"));
        String picturePathValue = "text.txt";
        File file = new File("src/test/resources/" + picturePathValue);
        uploadPicturelement.sendKeys(file.getAbsolutePath());

        //current address
        WebElement addressElement = driver.findElement(By.id("currentAddress"));
        String addressElementValue = "Adresa mea este: 123 Odei 155 430 doispe";
        addressElement.sendKeys(addressElementValue);
        js.executeScript("window.scrollBy(0, 350)", "");

        WebElement stateElement = driver.findElement(By.id("state"));
        stateElement.click();

        WebElement stateInputElement = driver.findElement(By.id("react-select-3-input"));
        String stateInputElementValue = "Uttar Pradesh";
        stateInputElement.sendKeys(stateInputElementValue);
        stateInputElement.sendKeys(Keys.ENTER);

        WebElement cityElement = driver.findElement(By.id("city"));
        cityElement.click();

        WebElement cityInputElement = driver.findElement(By.id("react-select-4-input"));
        String cityInputElementValue = "Agra";
        cityInputElement.sendKeys(cityInputElementValue);
        cityInputElement.sendKeys(Keys.ENTER);

        WebElement submitElement = driver.findElement(By.id("submit"));
        submitElement.click();

        //validam tabelul cu valorile introduse dupa submit
        WebElement successSubmitMessageElement = driver.findElement(By.id("example-modal-sizes-title-lg"));
        Assert.assertEquals(successSubmitMessageElement.getText(), "Thanks for submitting the form");

        List<WebElement> tableElements = driver.findElements(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']/tbody/tr"));
        Assert.assertEquals(tableElements.get(0).getText(), "Student Name " + firstNameValue + " " + lastNameValue);
        Assert.assertEquals(tableElements.get(1).getText(), "Student Email " + emailValue);
        Assert.assertEquals(tableElements.get(2).getText(), "Gender " + genderValue);
        Assert.assertEquals(tableElements.get(3).getText(), "Mobile " + mobileNumberValue);
        Assert.assertEquals(tableElements.get(5).getText(), "Subjects " + subjectInputValue);
        //transformam list array in string pentru hobbiesValue, altfel fara veneau cu paranteza [] si dadea eroare
        String expectedHobbiesText = "Hobbies " + String.join(", ", hobbiesValues);
        Assert.assertEquals(tableElements.get(6).getText(), expectedHobbiesText);
        Assert.assertEquals(tableElements.get(7).getText(), "Picture " + picturePathValue);
        Assert.assertEquals(tableElements.get(8).getText(), "Address " + addressElementValue);
        Assert.assertEquals(tableElements.get(9).getText(), "State and City " + stateInputElementValue + " " + cityInputElementValue);

        WebElement closeElement = driver.findElement(By.id("closeLargeModal"));
        js.executeScript("arguments[0].click();", closeElement);






    }
}
