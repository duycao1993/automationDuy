package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private WebDriver webDriver;

    public WaitUtil(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean isElementPresent(By element, int maxDuration){
        try{
            new WebDriverWait(webDriver, maxDuration).until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
