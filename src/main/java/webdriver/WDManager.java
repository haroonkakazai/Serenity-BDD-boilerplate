package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.util.EnvironmentVariables;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


/**
 * This class is ,written by Muhammad Ibrahim Ahsan,  about the WebDriver Manager, the purpose of this class is simply run the automation script on the available search engine, we don't need to add the webDriver separately
 */
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

/**
 * The Simple Logging Facade for Java (SLF4J) serves as a simple facade or abstraction for various logging frameworks, such as java.util.logging, log4j 1.x, reload4j and logback.
 */
@Slf4j
public class WDManager {

    private static Boolean headless;
    private static Browser browser;
    private static String browserVersion;
    private static Boolean local;
    private static String remoteAddress;
    private static Platform platform;

    private static EnvironmentVariables environmentVariables;

    private WDManager(){
        // Hide Implicit Public Constructor
    }

    @NotNull
    static EnvironmentVariables getEnvironmentVariables(){
        log.debug("Getting Environment Variables!");
        if (environmentVariables == null){environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();}
        return environmentVariables;
    }

    @NotNull
    static String getRemoteAddress(){
        log.debug("Getting Remote Address from Environment Variables");
        if (remoteAddress == null){
            remoteAddress = getEnvironmentVariables()
                    .getProperty("webdriver.remote.address","");
            if (remoteAddress.isEmpty()){
                log.warn("No remote address Set, setting local to \"true\"");
                local = true;}
        }
        return remoteAddress;
    }


    @NotNull
    static String getBrowserVersion(){
        log.debug("Getting Browser Version from Environment Variables");
        if (browserVersion == null){
            browserVersion = getEnvironmentVariables().getProperty("webdriver.remote.browser.version","");
        }
        return browserVersion;
    }

    @NotNull
    static Boolean isLocal(){
        log.debug("Getting Local from Environment Variables");
        if (local == null){local = getEnvironmentVariables()
                .getPropertyAsBoolean("webdriver.local",true);}
        return local;
    }

    @NotNull
    static Boolean isHeadless(){
        log.debug("Getting Headless from Environment Variables");
        if (headless == null) {
            if (isLocal()) {
                headless = getEnvironmentVariables()
                        .getPropertyAsBoolean("headless.mode", false);
            } else {
                log.warn("Headless set to False for remote");
                headless = false;
            }
        }
        return headless;
    }

    @NotNull
    static Platform getPlatform(){
        log.debug("Getting Platform from Environment Variables");
        if (platform == null){
            String configurationValue = getEnvironmentVariables().getProperty("webdriver.remote.os","");
            if (configurationValue.isEmpty()){
                platform = Platform.ANY;
            } else {
                platform = Platform.fromString(configurationValue);
            }
        }
        return platform;
    }

    @NotNull
    static Browser browser(){
        log.debug("Getting Browser from Environment Variables");
        if (browser == null){
            String configurationValue = getEnvironmentVariables().getProperty("webdriver.driver");
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

    @NotNull
    private static List<String> getSwitches(String switchPath){
        log.debug("Getting Switches for " + switchPath);
        String rawString = getEnvironmentVariables().getProperty(switchPath);
        return Arrays.asList(rawString.split(";"));
    }

    @SneakyThrows
    @NotNull
    private static <T extends MutableCapabilities> WebDriver  getRemote(T options){
        if (!getBrowserVersion().isEmpty()){
            options.setCapability("browserVersion",getBrowserVersion());
        }
        options.setCapability("platformName", getPlatform());
        return new RemoteWebDriver(new URL(getRemoteAddress()),options);
    }


    @NotNull
    @Contract(" -> new")
    private static WebDriver getChrome() {
        log.info("Getting Driver for Chrome");
        // Set Chrome options
        ChromeOptions chromeOptions = new ChromeOptions();

        // Get Switches defined in Serenity conf
        List<String> switches = getSwitches("chrome.switches");

        for (String switchArgument : switches){
            chromeOptions.addArguments(switchArgument);
        }
        chromeOptions.setHeadless(isHeadless());
        // Set up the WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        if (isLocal()) {
            return new ChromeDriver(chromeOptions);        }
        else {
            return getRemote(chromeOptions);
        }


    }


    @NotNull
    @Contract(" -> new")
    private static WebDriver getFirefox() {
        log.info("Getting Driver for Firefox");
        // Set Chrome options
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Get Switches defined in Serenity conf
        List<String> switches = getSwitches("firefox.switches");

        for (String switchArgument : switches){
            firefoxOptions.addArguments(switchArgument);
        }
        firefoxOptions.setHeadless(isHeadless());

        // Set up the WebDriver using WebDriverManager
        WebDriverManager.firefoxdriver().setup();
        if (isLocal()) {
            return new FirefoxDriver(firefoxOptions);
        }
        else {
            return getRemote(firefoxOptions);
        }
    }

    @NotNull
    @Contract("_ -> new")
    public static WebDriver attachToChrome(String port) throws IOException {
        WebDriverManager driverManager = WebDriverManager.getInstance(ChromeDriver.class);
        driverManager.setup();
        String binaryPath = driverManager.getDownloadedDriverPath();
        ChromeDriverService.Builder builder = new ChromeDriverService.Builder().usingDriverExecutable(new File(Paths.get(binaryPath).toRealPath().toString()));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:" + port);
        return new ChromeDriver(builder.build(),options);
    }

    @Nullable
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