package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WebTableTest {

    public WebDriver driver;

    @Test
    public void metodaTest(){

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
        WebElement elementsMenu = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsMenu.click();

        WebElement webTablesSubmenu = driver.findElement(By.xpath("//span[text()='Web Tables']"));
        webTablesSubmenu.click();

        //definim un element dupa ID
        //test 1: adaug un entry nou
        WebElement addElement = driver.findElement(By.id("addNewRecordButton"));
        addElement.click();

        WebElement firstNameElement = driver.findElement(By.id("firstName"));
        String firstNameValue = "Cristian";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.id("lastName"));
        String lastNameValue = "Constantin";
        lastNameElement.sendKeys(lastNameValue);

        WebElement emailElement = driver.findElement(By.id("userEmail"));
        String emailValue = "cristian.constantin@casa.ro";
        emailElement.sendKeys(emailValue);

        WebElement ageElement = driver.findElement(By.id("age"));
        String ageValue = "55";
        ageElement.sendKeys(ageValue);

        WebElement salaryElement = driver.findElement(By.id("salary"));
        String salaryValue = "10000";
        salaryElement.sendKeys(salaryValue);

        WebElement departmentElement = driver.findElement(By.id("department"));
        String departmentValue = "Testing";
        departmentElement.sendKeys(departmentValue);

        WebElement submitElement = driver.findElement(By.id("submit"));
        submitElement.click();

        //test 2: modific un entry existent
        WebElement editElement = driver.findElement(By.id("edit-record-4"));
        editElement.click();

        WebElement editSalaryElement = driver.findElement(By.id("salary"));
        String editSalaryValue = "25000";
        editSalaryElement.clear();
        editSalaryElement.sendKeys(editSalaryValue);

        WebElement editDepartmentElement = driver.findElement(By.id("department"));
        String editDepartmentValue = "HR";
        editDepartmentElement.clear();
        editDepartmentElement.sendKeys(editDepartmentValue);

        WebElement editSubmitElement = driver.findElement(By.id("submit"));
        editSubmitElement.click();

        //test 3: sterg un entry existent (generat tot de mine)
        WebElement deleteElement = driver.findElement(By.id("delete-record-4"));
        deleteElement.click();
        driver.quit();









    }


}
