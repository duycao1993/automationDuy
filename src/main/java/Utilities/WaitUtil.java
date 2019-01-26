package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private WebDriver webDriver;

    public WaitUtil(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    /**
     * Verify if the element is present or not
     * @param element
     * @param maxDuration
     * @return true if the element is present, otherwise return false
     */
    public boolean isElementPresent(By element, int maxDuration){
        try{
            new WebDriverWait(webDriver, maxDuration).until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
