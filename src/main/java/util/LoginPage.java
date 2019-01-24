package util;

import Environement.Configuration;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ExceptionUtil.PasswordIncorrectException;
import utilities.ExceptionUtil.SizeViolationException;
import utilities.ExceptionUtil.UnexpectedErrorException;
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

    @FindBy(css = "div[class='errorMessage']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(WebDriver driver , String userName, String passWord) throws Exception{
        log.info("Starting login");
        By elementToCheck = By.xpath("//div[@id='app']/div[2]/header");

        userNameTxt.sendKeys(userName);

        passWordTxt.sendKeys(passWord);

        submitBtn.click();

        WaitUtil wait = new WaitUtil(driver);

        By errorMessageBy = By.cssSelector("div[class='errorMessage']");

        if(wait.isElementPresent(errorMessageBy, 5)){
            if(errorMessage.getText().contains("Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại")){
                throw new PasswordIncorrectException();
            }

            if(errorMessage.getText().contains("Phone: Số điện thoại không hợp lệ") ||
                    errorMessage.getText().contains("Password: Mật khẩu phải có ít nhất 5 kí tự")){
                throw new SizeViolationException();
            }
        }
    }
}
