import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidApp {

    String userName = "mohammadk";
    String accessKey = "gkrzT0iFKjDjehXpMTznxM1lHIZXSYjV3H8Ntk0s2rCUJJO3WU";
    String builName = System.getenv("LT_BUILD_NAME") == null ?
            "accessKey" : System.getenv("LT_BUILD_NAME"); //Add accessKey here

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

   // AppiumDriver driver;
    protected static ThreadLocal<AppiumDriver> threadLocalDriver = new ThreadLocal<>();


    @BeforeTest
    @org.testng.annotations.Parameters(value = {"device", "platform"})
    public void setUp(String device, String platform) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("build",System.getenv("LT_BUILD_NAME"));
        capabilities.setCapability("name",platform+" "+device);
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("isRealMobile", true);
        capabilities.setCapability("app", "proverbial-android"); //Enter your app url
        capabilities.setCapability("deviceOrientation", "PORTRAIT");
        capabilities.setCapability("geoLocation", "IN");



        String hub = "https://" + userName + ":" + accessKey + gridURL;
        AppiumDriver driver = new AppiumDriver(new URL(hub), capabilities);
        threadLocalDriver.set(driver);
        System.out.println("Before Test Thread ID: "+Thread.currentThread().getId());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void AndroidApp1(/*String device, String platform*/) throws InterruptedException {

            MobileElement color = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/color");
            //Changes color to pink
            color.click();
            Thread.sleep(1000);
            //Back to orginal color
            color.click();

            MobileElement text = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/Text");
            //Changes the text to "Proverbial"
            text.click();


            //toast will be visible
            MobileElement toast = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/toast");
            toast.click();

            //notification will be visible
            MobileElement notification = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Thread.sleep(2000);

            //Opens the geolocation page
            MobileElement geo = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/geoLocation");
            geo.click();
            Thread.sleep(5000);

            //takes back to home page
            MobileElement home = (MobileElement) getDriver().findElementByAccessibilityId("Home");
            home.click();

            //Takes to speed test page
            MobileElement speedtest = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/speedTest");
            speedtest.click();
            Thread.sleep(5000);

            MobileElement Home = (MobileElement) getDriver().findElementByAccessibilityId("Home");
            Home.click();

            //Opens the browser
            MobileElement browser = (MobileElement) getDriver().findElementByAccessibilityId("Browser");
            browser.click();

            MobileElement url = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/url");
            url.sendKeys("https://www.lambdatest.com");

            MobileElement find = (MobileElement) getDriver().findElementById("com.lambdatest.proverbial:id/find");
            find.click();

           // getDriver().quit();

    }

    @AfterTest
    public void tearDown(){
        getDriver().quit();
        System.out.println("After Test Thread ID: "+Thread.currentThread().getId());
        threadLocalDriver.remove();
    }




    //get thread-safe driver
    public static AppiumDriver getDriver(){
        return threadLocalDriver.get();
    }


}
