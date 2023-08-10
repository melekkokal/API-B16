package Homeworks.Homework1.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='c-nav--signed-out__link']")
    WebElement signIn;

    @FindBy(xpath = "//input[@id='domain']")
    WebElement workspace;

    @FindBy(xpath = "//span//a[@class='c-link bold']")
    WebElement signInManually;

    @FindBy(xpath = "//span//a[@class='c-link bold']")
    WebElement signInWithAPassword;

    @FindBy(xpath = "//input[@id='email']")
    WebElement typeEmail;

    @FindBy(xpath = "//input[@id='password']")
    WebElement password;

    @FindBy(xpath = "//button[@id='signin_btn']")
    WebElement signInButton;

    public void launchSlack(){
        signIn.click();
        signInManually.click();
        workspace.sendKeys("Codefish-Batch16", Keys.ENTER);
        signInWithAPassword.click();
        typeEmail.sendKeys("melekkokal@hotmail.com");
        password.sendKeys("072124Suna*");
        signInButton.click();




    }






}
