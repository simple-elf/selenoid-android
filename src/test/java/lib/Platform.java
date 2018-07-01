package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://46.101.105.58:4444/wd/hub"; // "http://127.0.0.1:4723/wd/hub"

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public WebDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);

        if (this.isAndroid()) {
            return new RemoteWebDriver(url, this.getAndroidDesiredCapabilities());
            //return new AndroidDriver(url, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(url, this.getIOSDesiredCapabilities());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        //capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("deviceName","AndroidTestDevice");
        //capabilities.setCapability("platformVersion","6.0");
        //capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","D:\\WORK\\IdeaProjects\\selenoid-android\\apks\\org.wikipedia.apk"); // for Windows
        //capabilities.setCapability("app","/Users/simple1elf/JavaAppium/apks/org.wikipedia.apk"); //for MacOs

        capabilities.setCapability("browserName", "android");
        capabilities.setCapability("version","6.0");
        //capabilities.setCapability("enableVNC",true);
        //capabilities.setCapability("enableVideo",true);

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("platformVersion","11.2");
        capabilities.setCapability("deviceName","iPhone SE");
        capabilities.setCapability("app","/Users/simple1elf/JavaAppium/apks/Wikipedia.app");

        return capabilities;
    }

    private boolean isPlatform(String myPlatform) {
        String platform = getPlatformVar();
        return  myPlatform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getProperty("PLATFORM");
    }

}
