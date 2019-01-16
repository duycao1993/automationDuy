package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;
import java.util.List;

public class LoginPage {
    private Logger log = Logger.getLogger(LoginPage.class);
    private WebDriver driver;

    //private By userNameElement = By.xpath("//form[@id='pageLogin']/dl[1]/dd/input");
    //private By passWordElement = By.xpath("//form[@id='pageLogin']/dl[2]/dd/ul/li[@id = 'ctrl_pageLogin_registered_Disabler']/input");
    //private By loginBtnElement = By.xpath("//form[@id='pageLogin']/dl[3]/dd/input");
    //private By pageHeaderElement = By.xpath("//div[contains(@class,'item user')]");

    @FindBy(css = "input[data-reactid='46']")
    WebElement userNameTxt;

    @FindBy(css = "input[data-reactid='47']")
    WebElement passWordTxt;

    @FindBy(css = "button[data-reactid='51']")
    WebElement submitBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean login(WebDriver driver , String userName, String passWord){
        log.info("Starting login");

        By elementToCheck = By.id("abc");

        try{
            userNameTxt.sendKeys(userName);

            passWordTxt.sendKeys(passWord);

            submitBtn.click();

            WaitUtil wait = new WaitUtil(driver);
            if(!wait.isElementPresent(elementToCheck,5)){
                return false;
            }

        } catch (Exception e){
            log.info("Error while loging " + e);
            return false;
        }

        log.info("Login successful");
        return true;
    }
}
