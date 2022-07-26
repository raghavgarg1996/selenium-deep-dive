package com.se4.module7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class InteractWithUIElements {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginTest(){
        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");

        WebElement loginBtn = driver.findElement(By.xpath("//input[@value='Log In']"));
        assertThat(loginBtn).as("Dynamic color button is null").isNotNull();

        WebElement usernameTxtBox = driver.findElement(By.name("username"));
        WebElement pwdTxtBox = driver.findElement(By.name("password"));

        usernameTxtBox.sendKeys("test");
        pwdTxtBox.sendKeys("test");
        loginBtn.click();

        WebElement accountsTextElem = driver.findElement(By.xpath("//h1[text()='Accounts Overview']"));
        String accountsText = accountsTextElem.getText();
        assertThat(accountsText)
                .as("Accounts text is not 'Accounts Overview'")
                .isEqualTo("Accounts Overview");
    }

    @Test
    public void dropDownTest() {
        driver.get("https://artoftesting.com/samplesiteforselenium");
        WebElement dropdown = driver.findElement(By.id("testingDropdown"));
        Select select = new Select(dropdown);
        String firstOption = select.getFirstSelectedOption().getText();
        System.out.println("The selected value is : " + firstOption);

        assertThat(firstOption)
                .as("Drop down is not selected by default with 'Automation Testing'")
                .isEqualTo("Automation Testing");

        System.out.println("**** List of all options available ****");
        List<WebElement> allOptions = select.getOptions();
        for(WebElement opt : allOptions){
            System.out.println(opt.getText());
        }

        select.selectByVisibleText("Manual Testing");
        firstOption = select.getFirstSelectedOption().getText();
        assertThat(firstOption)
                .as("Drop down is not selected with 'Manual Testing'")
                .isEqualTo("Manual Testing");
    }

    @Test
    public void dragAndDropTest() {
        String URL = "https://demoqa.com/droppable/";
        driver.get(URL);

        // It is always advisable to Maximize the window before performing DragNDrop action
        driver.manage().window().maximize();

        //Actions class method to drag and drop
        Actions builder = new Actions(driver);

        WebElement from = driver.findElement(By.id("draggable"));

        WebElement to = driver.findElement(By.id("droppable"));
        //Perform drag and drop
        builder.dragAndDrop(from, to).perform();
    }

    @Test
    public void hoverTest() {
        driver.get("https://applitools.com/");

        Actions actions = new Actions(driver);
        WebElement mainMenu = driver.findElement(By.xpath("//a[text()='Product']"));
        actions.moveToElement(mainMenu);

        WebElement subMenu = driver.findElement(By.xpath("//span[text()='Ultrafast Grid']"));
        actions.moveToElement(subMenu);
        actions.click().build().perform();

        driver.quit();
    }

}
