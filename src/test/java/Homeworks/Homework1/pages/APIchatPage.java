package Homeworks.Homework1.pages;

import Utils.BrowserUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class APIchatPage {

    public APIchatPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//div[@class='c-texty_input_unstyled ql-container']")
    WebElement inputBox;

    @FindBy(xpath = "//button[@aria-label='Send now']")
    WebElement button;

    public void sendMessage(String message, WebDriver driver){


        WebDriverWait wait1= new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOf(inputBox));
        inputBox.click();
        inputBox.sendKeys(message);
      WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
      wait.until(ExpectedConditions.visibilityOf(button));
        BrowserUtils.clickWithJS(driver,button);

        Actions actions=new Actions(driver);
        actions.moveToElement(button).perform();
        actions.click(button).perform();
        //button.click();

    }
}
