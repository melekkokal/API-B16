package Homeworks.Homework1.stepdef;

import Utils.BrowserUtils;
import Utils.ConfigReader;
import Utils.DriverHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class SlackHook {

    WebDriver driver= DriverHelper.getDriver();

    @Before
    public void setup(){
        driver.get(ConfigReader.readProperty("QA_slack_url"));
    }

    @After
    public void tearDown(Scenario scenario){
        BrowserUtils.getScreenShotForCucumber(driver, scenario);
        //driver.quit();
    }
}
