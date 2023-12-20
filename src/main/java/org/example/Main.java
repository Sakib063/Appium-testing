package org.example;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Main{

    public AndroidDriver driver;
    @BeforeClass //calling this prereq function before running the actual test
    public void configure_appium() throws MalformedURLException{
        //must turn on appium server using the CLI and import SLF4J-nop libs
        UiAutomator2Options options=new UiAutomator2Options();
        options.setDeviceName("VM_Phone"); //using android studio emulator

//        options.setApp(System.getProperty("user.dir")+"\\src\\main\\java\\app\\ApiDemos-debug.apk"); //the full path is required. use user.dir
        options.setApp(System.getProperty("user.dir")+"\\src\\main\\java\\app\\General-Store.apk"); //the full path is required. use user.dir
        options.setChromedriverExecutable(System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");

        driver=new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass //turning off the driver after running test
    public void teardown(){
        driver.quit();
    }
}