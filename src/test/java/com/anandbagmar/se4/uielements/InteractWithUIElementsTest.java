package com.anandbagmar.se4.uielements;

import com.anandbagmar.se4.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InteractWithUIElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new Driver().createChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void sendKeysTest() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC");

        WebElement loginButton = driver.findElement(By.xpath("//input[@value='Log In']"));
        assertThat(loginButton).as("Login button not found")
                               .isNotNull();

        WebElement usernameTextBox = driver.findElement(By.name("username"));
        WebElement pwdTextBox = driver.findElement(By.name("password"));

        usernameTextBox.clear();
        usernameTextBox.sendKeys("test");
        pwdTextBox.clear();
        pwdTextBox.sendKeys("test");
        loginButton.click();

        String accountsText = driver.findElement(By.xpath("//h1[text()='Accounts Overview']"))
                                    .getText();
        assertThat(accountsText).as("Accounts text is not 'Accounts Overview'")
                                .isEqualTo("Accounts Overview");
    }

    @Test
    public void dropDownTest() {
        driver.get("https://artoftesting.com/samplesiteforselenium");
        WebElement dropdown = driver.findElement(By.id("testingDropdown"));
        Select select = new Select(dropdown);
        String firstOption = select.getFirstSelectedOption()
                                   .getText();
        System.out.println("The selected value is : " + firstOption);

        assertThat(firstOption).as("Drop down is not selected by default with 'Automation Testing'")
                               .isEqualTo("Automation Testing");

        System.out.println("**** List of all options available ****");
        List<WebElement> allOptions = select.getOptions();
        for(WebElement option : allOptions) {
            System.out.println("\t" + option.getText());
        }

        select.selectByVisibleText("Manual Testing");
        firstOption = select.getFirstSelectedOption()
                            .getText();
        assertThat(firstOption).as("Drop down is not selected with 'Manual Testing'")
                               .isEqualTo("Manual Testing");
    }

    @Test
    public void dragAndDropTest() {
        driver.get("https://demoqa.com/droppable/");

        // It may be advisable to Maximize the window before performing DragNDrop action
        driver.manage()
              .window()
              .maximize();

        //Actions class method to drag and drop
        Actions builder = new Actions(driver);

        WebElement from = driver.findElement(By.id("draggable"));

        WebElement to = driver.findElement(By.id("droppable"));
        //Perform drag and drop
        builder.dragAndDrop(from, to)
               .perform();
    }

    @Test
    public void hoverTest() {
        driver.get("https://applitools.com/");

        Actions actions = new Actions(driver);
        WebElement mainMenu = driver.findElement(By.xpath("//a[text()='Product']"));
        actions.moveToElement(mainMenu);

        WebElement subMenu = driver.findElement(By.xpath("//span[text()='Ultrafast Grid']"));
        actions.moveToElement(subMenu);
        actions.click()
               .build()
               .perform();
    }
}
