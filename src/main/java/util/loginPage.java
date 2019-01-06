package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitUtil;

import java.util.List;
import java.util.logging.Logger;

public class loginPage {
    Logger log = Logger.getLogger(loginPage.class.toString());

    By userNameElement = By.xpath("//form[@id='pageLogin']/dl[1]/dd/input");
    By passWordElement = By.xpath("//form[@id='pageLogin']/dl[2]/dd/ul/li[@id = 'ctrl_pageLogin_registered_Disabler']/input");
    By loginBtnElement = By.xpath("//form[@id='pageLogin']/dl[3]/dd/input");
    By pageHeaderElement = By.xpath("//div[contains(@class,'item user')]");

    public boolean login(WebDriver driver , String userName, String passWord){
        log.info("Starting login");
        //Navigate to login page
        driver.navigate().to("https://tinhte.vn/login");
        WaitUtil waitUtil = new WaitUtil();
        waitUtil.isElementPresent2(driver,By.id("ctrl_pageLogin_login"),10);
        WebElement userInput = driver.findElement(userNameElement);
        WebElement passInput = driver.findElement(passWordElement);
        WebElement submitBtn = driver.findElement(loginBtnElement);

        try{

            userInput.sendKeys(userName);
            passInput.sendKeys(passWord);

            submitBtn.click();



            WebElement headerPage = driver.findElement(By.tagName("header"));
            List<WebElement> headerElement = driver.findElements(By.xpath("//div[contains(@class,'item user')]"));
            waitUtil.isElementPresent2(driver, pageHeaderElement, 10);
        } catch (Exception e){
            log.info("Error while loging " + e);
            return false;
        }

        log.info("Login successful");
        return true;
    }
}
