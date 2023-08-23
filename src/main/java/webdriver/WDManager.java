package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox");

    private final String expectedValue;

    private Browser(String name) {
        this.expectedValue = name;
    }

    public boolean equalValue(String givenValue) {
        return expectedValue.equalsIgnoreCase(givenValue);

    }
}

@Slf4j
public class WDManager {
    private static Boolean headless;
    private static Browser browser;
    private static EnvironmentVariables environmentVariables;

    private WDManager(){
        // Hide Implicit Public Constructor
    }

    static EnvironmentVariables environmentVariables(){
        log.debug("Getting Environment Variables!");
        if (environmentVariables == null){environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();}
        return environmentVariables;
    }

    static Boolean isHeadless(){
        log.debug("Getting Headless from Configuration");
        if (headless == null){headless = environmentVariables()
                .getProperty("headless.mode").equals("true");}
        return headless;
    }

    static Browser browser(){
        log.debug("Getting Browser from Configuration");
        if (browser == null){
            String configurationValue = environmentVariables().getProperty("webdriver.driver");
            for (Browser availableBrowser : Browser.values()){
                if (availableBrowser.equalValue(configurationValue)){
                    browser = availableBrowser;
                }
            }
            if (browser == null){
                throw new IllegalArgumentException("Invalid Driver in Configuration!");
            }
        }
        return browser;
    }

    private static List<String> getSwitches(String switchPath){
        log.debug("Getting Switches for " + switchPath);
        String rawString = environmentVariables().getProperty(switchPath);
        return Arrays.asList(rawString.split(";"));
    }
    private static WebDriver getChrome() {
        log.info("Getting Driver for Chrome");
        // Set Chrome options
        ChromeOptions chromeOptions = new ChromeOptions();

        // Create a temporary directory for each driver instance
        Path tempUserDataDir;
        try {
            tempUserDataDir = Files.createTempDirectory("chrome-profile");
            chromeOptions.addArguments("--user-data-dir=" + tempUserDataDir.toString());
        } catch (IOException e) {
            log.warn("Failed to create a temporary user data directory.");
        }


        // Get Switches defined in Serenity conf
        List<String> switches = getSwitches("chrome.switches");

        for (String switchArgument : switches){
            chromeOptions.addArguments(switchArgument);
        }
        chromeOptions.setHeadless(isHeadless());
        // Set up the WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(chromeOptions);

    }

    private static WebDriver getFirefox() {
        log.info("Getting Driver for Firefox");
        // Set Chrome options
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Create a temporary directory for each driver instance
        Path tempUserDataDir;
        try {
            tempUserDataDir = Files.createTempDirectory("firefox-profile");
            firefoxOptions.addArguments("-profile \"" + tempUserDataDir.toString() + "\"");
        } catch (IOException e) {
            log.warn("Failed to create a temporary user data directory.");
        }

        // Get Switches defined in Serenity conf
        List<String> switches = getSwitches("firefox.switches");

        for (String switchArgument : switches){
            firefoxOptions.addArguments(switchArgument);
        }
        firefoxOptions.setHeadless(isHeadless());

        // Set up the WebDriver using WebDriverManager
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(firefoxOptions);
    }

    public static WebDriver attachToChrome(String port) throws IOException {
        WebDriverManager driverManager = WebDriverManager.getInstance(ChromeDriver.class);
        driverManager.setup();
        String binaryPath = driverManager.getDownloadedDriverPath();
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder().usingDriverExecutable(new File(Paths.get(binaryPath).toRealPath().toString()));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + port);
        return new ChromeDriver(builder.build(),options);
    }

    public static WebDriver getDriver() {
        if (browser() == Browser.CHROME){
            return getChrome();
        } else if (browser() == Browser.FIREFOX) {
            return getFirefox();
        } else {
            return null;
        }
    }
}
