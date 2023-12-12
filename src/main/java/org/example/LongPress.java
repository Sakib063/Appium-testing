package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LongPress extends Main{
    @Test
    public void long_press(){
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        WebElement touch=driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId",((RemoteWebElement) touch).getId(),"duration",2000
        ));

        String text=driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(text,"Sample menu");
        boolean display=driver.findElement(By.id("android:id/title")).isDisplayed();
        Assert.assertTrue(display);
    }
}
