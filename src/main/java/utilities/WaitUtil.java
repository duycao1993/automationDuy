package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    public boolean isElementPresent(WebDriver driver, By element, int maxDuration){
        try{
            new WebDriverWait(driver, maxDuration).until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
