package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.util.Set;

public class GeneralStore extends Main{
    @Test
    public void create_account(){
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Bangladesh\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Bangladesh']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Test Name");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/radioMale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test
    public void create_account_error_msg(){
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String msg=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(msg,"Please enter your name");
    }

    //adding a product and checking out cart and the webviewer
    @Test
    public void shopping() throws InterruptedException{
        create_account();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
        driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        Thread.sleep(2000);
        Set <String> context_names=driver.getContextHandles();

        for(String context_name:context_names){
            System.out.println(context_name);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(6000);
        driver.findElement(By.name("q")).sendKeys("UIU");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    //adding multiple products
    @Test
    public void shopping_multi() throws InterruptedException{
        create_account();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
        driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"PG 3\"))"));
        driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        Thread.sleep(2000);
    }

    //null cart error msg
    @Test
    public void cart_error_msg(){
        create_account();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        String msg=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
        Assert.assertEquals(msg,"Please add some product at first");
    }
}
