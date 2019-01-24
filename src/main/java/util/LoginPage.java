package util;

import Environement.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitUtil;

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

    public void login(WebDriver driver , String userName, String passWord){
        log.info("Starting login");

        By elementToCheck = By.xpath("//div[@id='app']/div[2]/header");

        userNameTxt.sendKeys(userName);

        passWordTxt.sendKeys(passWord);

        submitBtn.click();

        WaitUtil wait = new WaitUtil(driver);

        if(wait.isElementPresent(elementToCheck, Configuration.getInstance().getTimeOut())){
            driver.findElement(elementToCheck).findElement(By.xpath("div/div[2]/div[2]")).click();
            wait.isElementPresent(By.xpath("//div[@class='appWrapper-UserMenuItem']"),3);
        } else {

        }

    }
}
