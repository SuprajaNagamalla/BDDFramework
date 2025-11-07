package com.qa.factory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

//import jdk.jpackage.internal.Log;
import com.qa.util.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.safari.options.SafariOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.apache.logging.log4j.Logger;
import com.qa.util.LoggerHelper;

import java.io.IOException;
import java.util.Properties;

import com.qa.util.ConfigReader;

public class DriverFactory {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static final Logger Log = LoggerHelper.getLogger();

    ConfigReader configReader = new ConfigReader();
    Properties prop;

    public static String USERNAME = "";//""your_browserstack_username";
    public static String ACCESS_KEY = "";//""your_browserstack_access_key";
    public static String URL = "";

    MutableCapabilities bstackOptions = new MutableCapabilities();

    public WebDriver initializeDriver(String browser) throws IOException {

        Log.info("Browser is: " + browser);
        System.setProperty("browserstack.disable-automate-session", "true");

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if(!System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
                	System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
                }
                // Disable autofill and save prompts
                chromeOptions.addArguments("--disable-save-password-bubble");
                chromeOptions.addArguments("--disable-autofill-keyboard-accessory-view[8]");

                // Disable automation flags and browser features
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");

//                browser zoom level setup
//                if(!System.getProperty("os.name").equalsIgnoreCase("Mac OS X")){
//                	chromeOptions.addArguments("--force-device-scale-factor=0.80");
//                }


                // Window size and stability flags
                chromeOptions.addArguments("--window-size=1920,1080");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                // Realistic user-agent (optional but recommended for bot protection)
                chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");

                // Disable credential saving/autofill
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.enabled", false);
                prefs.put("profile.autofill_profile_enabled", false);
                chromeOptions.setExperimentalOption("prefs", prefs);

                // Remove automation flags
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

                // Enable headless if running in Jenkins
                if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--disable-gpu");
                }

                tlDriver.set(new ChromeDriver(chromeOptions));
                break;

            case "firefox":
                // Set Firefox preferences
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addPreference("permissions.default.geo", 2); // Block location access
                if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
                    firefoxOptions.addArguments("--headless");
                }
                tlDriver.set(new FirefoxDriver(firefoxOptions));
                break;

            case "safari":
                tlDriver.set(new SafariDriver());
                break;

            case "edge":
                tlDriver.set(new EdgeDriver());
                break;

            case "android":
                Log.info("Launching Chrome in Android Emulator Mode...");
                ChromeOptions mobileOptions = new ChromeOptions();

                // Set mobile emulation with a specific Android device
                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "Pixel 4"); // working

                mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
                    mobileOptions.addArguments("--headless", "--disable-gpu");
                }

                tlDriver.set(new ChromeDriver(mobileOptions));
                break;

			/*case "android":
				Log.info("Launching Chrome in Android Emulator Mode...");
				ChromeOptions mobileOptions = new ChromeOptions();

				// Set mobile emulation with a specific Android device and resolution
				Map<String, Object> deviceMetrics = new HashMap<>();
				deviceMetrics.put("width", 412);
				deviceMetrics.put("height", 915);
				deviceMetrics.put("pixelRatio", 3.0);

				Map<String, Object> mobileEmulation = new HashMap<>();
				mobileEmulation.put("deviceName", "Pixel 4"); // working
				mobileEmulation.put("deviceMetrics", deviceMetrics);

				mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

				if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
					mobileOptions.addArguments("--headless", "--disable-gpu");
				}

				tlDriver.set(new ChromeDriver(mobileOptions));
				break;*/


            case "ios":
                Log.info("Launching Chrome in iOS Emulator Mode...");
                ChromeOptions iosOptions = new ChromeOptions();

                // Set mobile emulation with a specific iOS device
                Map<String, String> iosEmulation = new HashMap<>();
                iosEmulation.put("deviceName", "iPhone XR"); // Change to any valid iOS device

                iosOptions.setExperimentalOption("mobileEmulation", iosEmulation);

                if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
                    iosOptions.addArguments("--headless", "--disable-gpu");
                }

                tlDriver.set(new ChromeDriver(iosOptions));
                break;

            case "android_chrome":
                Log.info("Launching Emulator Android Chrome browser...");
                tlDriver.set(launchAndroidDriver());
                break;

            case "android_chrome_bs":
                Log.info("Launching Android Chrome on BrowserStack...");
                //tlDriver.set(launchAndroidBrowserOnBrowserStack());
                break;

            case "bsandroid_chrome":
                Log.info("Launching Android Chrome browser in Browser Stack...");
                USERNAME = ConfigReader.getProperty("bsUserName");
                ACCESS_KEY = ConfigReader.getProperty("bsPassword");
                URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

               // MutableCapabilities bstackOptions = new MutableCapabilities();
                bstackOptions.setCapability("osVersion", "12.0");
                bstackOptions.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
                bstackOptions.setCapability("browserVersion", "latest");
//				bstackOptions.setCapability("local", "true");
//				bstackOptions.setCapability("loglevel", "debug");
//				bstackOptions.setCapability("debug", "true");
//				bstackOptions.setCapability("networkLogs", "true");
                bstackOptions.setCapability("realMobile", "true");

//				bstackOptions.setCapability("seleniumVersion", "4.13.0");
                bstackOptions.setCapability("seleniumVersion", "4.25.0");
                bstackOptions.setCapability("telemetryLogs", "true");
                bstackOptions.setCapability("acceptInsecureCerts", "true");
                bstackOptions.setCapability("buildName", "SFCC-RAC");
                bstackOptions.setCapability("sessionName", "Android Chrome Test");

                // Enable debug and network logs
                bstackOptions.setCapability("debug", "true");
                bstackOptions.setCapability("networkLogs", "true");

                //bstackOptions.setCapability("local", "true");

                //Enable interactive debugging
                bstackOptions.setCapability("interactiveDebugging", "true");

                // Browser capabilities (Chrome in this case)
                ChromeOptions bschromeOptions = new ChromeOptions();
//				bschromeOptions.setCapability("browserName", "Chrome");
                bschromeOptions.setCapability("bstack:options", bstackOptions);

                // Create RemoteWebDriver
                //WebDriver driver = new RemoteWebDriver(new URL(URL), bschromeOptions);
                Log.info("URL::" + URL);

                AndroidDriver driver = new AndroidDriver(new URL(URL), bschromeOptions);

                tlDriver.set(driver);
                // Try to hide keyboard (only works with native apps, not Chrome mobile browser)
                try {
                    ((JavascriptExecutor) driver).executeScript("if(document.activeElement) document.activeElement.blur();");
                    Log.info("Attempted to blur active element to hide keyboard.");
                } catch (Exception e) {
                    Log.warn("Hiding keyboard using JS blur() failed: " + e.getMessage());
                }
                //tlDriver.set(launchAndroidDriver());
                break;
            case "bs_ios_safari":
                Log.info("Launching iOS Safari browser in Browser Stack...");
                USERNAME = ConfigReader.getProperty("bsUserName");
                ACCESS_KEY = ConfigReader.getProperty("bsPassword");
                URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub";

                MutableCapabilities bstackOptions = new MutableCapabilities();
                bstackOptions.setCapability("osVersion", "16");
                bstackOptions.setCapability("deviceName", "iPhone 16");
                bstackOptions.setCapability("realMobile", "true");
                bstackOptions.setCapability("browserVersion", "latest");
                bstackOptions.setCapability("seleniumVersion", "4.25.0");
                bstackOptions.setCapability("telemetryLogs", "true");
                bstackOptions.setCapability("acceptInsecureCerts", "true");
                bstackOptions.setCapability("buildName", "SFCC-RAC");
                bstackOptions.setCapability("sessionName", "iOS Safari Test");
                bstackOptions.setCapability("debug", "true");
                bstackOptions.setCapability("networkLogs", "true");
                bstackOptions.setCapability("interactiveDebugging", "true");

                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setCapability("browserName", "Safari");
                safariOptions.setCapability("bstack:options", bstackOptions);

                Log.info("URL::" + URL);

                IOSDriver driverIOS = new IOSDriver(new URL(URL), safariOptions);
                tlDriver.set(driverIOS);

                try {
                    ((JavascriptExecutor) driverIOS).executeScript("if(document.activeElement) document.activeElement.blur();");
                    Log.info("Attempted to blur active element to hide keyboard iOS.");
                } catch (Exception e) {
                    Log.warn("Hiding keyboard using JS blur() failed iOS: " + e.getMessage());
                }
                break;

            case "remote":
                ChromeOptions remoteOptions = new ChromeOptions();              


                // Disable autofill and save prompts
                remoteOptions.addArguments("--disable-save-password-bubble");
                remoteOptions.addArguments("--disable-autofill-keyboard-accessory-view[8]");

                // Disable automation flags and browser features
                remoteOptions.addArguments("--disable-popup-blocking");
                remoteOptions.addArguments("--disable-notifications");
                remoteOptions.addArguments("--disable-infobars");
                remoteOptions.addArguments("--disable-extensions");
                remoteOptions.addArguments("--disable-blink-features=AutomationControlled");

                // Window size and stability flags
                remoteOptions.addArguments("--window-size=1920,1080");
                remoteOptions.addArguments("--no-sandbox");
                remoteOptions.addArguments("--disable-dev-shm-usage");

                // Realistic user-agent (optional but recommended for bot protection)
                remoteOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");

                // Disable credential saving/autofill
                Map<String, Object> remotePrefs = new HashMap<>();
                remotePrefs.put("profile.default_content_setting_values.notifications", 2);
                remotePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
                remotePrefs.put("credentials_enable_service", false);
                remotePrefs.put("profile.password_manager_enabled", false);
                remotePrefs.put("autofill.profile_enabled", false);
                remotePrefs.put("autofill.enabled", false);
                remotePrefs.put("profile.autofill_profile_enabled", false);
                remoteOptions.setExperimentalOption("prefs", remotePrefs);

                // Remove automation flags
                remoteOptions.setExperimentalOption("useAutomationExtension", false);
                remoteOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});


//                    if (Optional.ofNullable(System.getenv("JENKINS_HOME")).isPresent()) {
//                    remoteOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
//                }
                tlDriver.set(new RemoteWebDriver(new URL("http://10.252.41.230:4444"), remoteOptions));
                break;

            default:
                throw new IllegalArgumentException("Invalid browser: " + browser + ". Supported browsers: Chrome, edge,android.");
        }

        // Set common configurations
        WebDriver driver = getDriver();
        driver.manage().deleteAllCookies();
        if (!browser.toLowerCase().contains("android")) {
            driver.manage().window().maximize();
        }
        //driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        return driver;
    }

    private WebDriver launchAndroidDriver() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            //options.setDeviceName("Android Emulator"); // Change as per actual device
            options.setDeviceName("emulator-5554"); // Change as per actual device
            options.withBrowserName("chrome");
            options.setAutomationName("UiAutomator2");
            //options.autoGrantPermissions();

			/*Map<String, Object> args = new HashMap<>();
			args.put("command", "pm grant com.android.chrome android.permission.ACCESS_FINE_LOCATION");
			driver.executeScript("mobile:shell", args);*/

            URL appiumServerURL = new URL("http://127.0.0.1:4723"); // Ensure Appium is running

            Log.info("Launching Android Chrome browser via Appium with UiAutomator2...");
            AndroidDriver androidDriver = new AndroidDriver(appiumServerURL, options);
            tlDriver.set(androidDriver); // Set AndroidDriver in ThreadLocal

            //appium --allow-insecure chromedriver_autodownload,adb_shell
            //adb shell pm grant com.android.chrome android.permission.ACCESS_FINE_LOCATION

            // 🔹 Grant location permission explicitly
            Map<String, Object> args = new HashMap<>();
            args.put("command", "pm grant com.android.chrome android.permission.ACCESS_FINE_LOCATION");
            androidDriver.executeScript("mobile:shell", args);
            Log.info("Granted location permission using mobile:shell command.");

            return androidDriver;

        } catch (MalformedURLException e) {
            Log.error("Invalid Appium Server URL", e);
            throw new RuntimeException("Failed to initialize AndroidDriver", e);
        }
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
