package util;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CommonFunction {

	public APIUtils apiUtilObj;
	public APIResponse apiResponseObj;
	public WebDriver chromeD;

	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public AppiumDriver<MobileElement> commonStartAndOpenURLBrowser()
			throws ParserConfigurationException, SAXException, IOException {

		DesiredCapabilities capability = null;
		AppiumDriver<MobileElement> driver = null;
		try {
			capability = new DesiredCapabilities();
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
					capability.setCapability("automationName", getCapebility().get("automationName"));
				}
				capability.setCapability("platformName", "Android");
//			    capability.setCapability("clearDeviceLogsOnStart",true);
				capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
				capability.setCapability("appPackage", getCapebility().get("appPackage"));
				capability.setCapability("appActivity", "com.studyiq.android.activities.SplashActivity");
//			    capability.setCapability("clearDeviceLogsOnStart", true);
				capability.setCapability("noReset", false);
				capability.setCapability("unicodeKeyboard", false);
				capability.setCapability("resetKeyboard", false);
//				capability.setCapability("autoGrantPermissions", "true");
				capability.setCapability("autoDismissAlerts", true);
				capability.setCapability("appium:autoLogcat", "true");
				capability.setCapability("appium:logLevel", "debug");
				capability.setCapability("appium:androidLogcatBufferSize", 1024 * 1024);
				capability.setCapability("autoGrantPermissions", true);
				capability.setCapability("adbExecTimeout", 20000);

				if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
					capability.setCapability("build", "Android Test");
					capability.setCapability("deviceName", ConfigFileReader.strDeviceName);
					capability.setCapability("version", ConfigFileReader.strDeviceVersion);
					capability.setCapability("platformVersion", "11");
					capability.setCapability("app", getCapebility().get("strFilePath"));
					capability.setCapability("isRealMobile", true);
					capability.setCapability("visual", true);
//				    capability.setCapability("browserstack.debug", true);
//					capability.setCapability("network", "true");
					capability.setCapability("devicelog", "true");
				}

				System.out.println("capability " + capability);
				System.out.println("hubUrl: " + getCapebility().get("remoteAddress"));
				driver = new AndroidDriver<MobileElement>(new URL(getCapebility().get("remoteAddress")), capability);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

				if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {

					HashMap paramsRotate = new HashMap();
					paramsRotate.put("command", "autorotate");
					paramsRotate.put("enableAutoRotate", false);
					driver.executeScript("lambda-adb", paramsRotate);
				}

			} else if (ConfigFileReader.strApplicationType.equalsIgnoreCase("IOS")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("platformName", "ios");
				capabilities.setCapability("deviceName", "iPhone 12 Mini");
				capabilities.setCapability("platformVersion", "14");
				capabilities.setCapability("devicelog", true);
				capabilities.setCapability("video", true);
				capabilities.setCapability("isRealMobile", true);
//				capabilities.setCapability("playStoreLogin", playstorelogin);
				capabilities.setCapability("build", "iOS Test");

				if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
//					capability.setCapability("app", getCapability().get("strFilePath"));
					capabilities.setCapability("app", ConfigFileReader.strFilePath);
//					capability.setCapability("browserstack.debug", true);
				}
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);
				driver = new IOSDriver<MobileElement>(new URL(getCapebility().get("remoteAddress")), capabilities);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return driver;
	}

	public Map<String, String> getCapebility() {
		Map<String, String> capebilityMap = new HashMap<>();
		try {

			capebilityMap.put("appPackage", "com.studyiq.android");

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

				capebilityMap.put("deviceName", ConfigFileReader.strDeviceName);
				capebilityMap.put("deviceVersion", ConfigFileReader.strDeviceVersion);
				capebilityMap.put("remoteAddress", "http://localhost:4723/wd/hub");
				capebilityMap.put("automationName", "UiAutomator2");

			} else if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				capebilityMap.put("deviceName", ConfigFileReader.strDeviceName);
				capebilityMap.put("deviceVersion", ConfigFileReader.strDeviceVersion);
				capebilityMap.put("remoteAddress", "https://" + ConfigFileReader.userName + ":"
						+ ConfigFileReader.accessKey + "@mobile-hub.lambdatest.com/wd/hub");
				capebilityMap.put("strFilePath", ConfigFileReader.strFilePath);

			}
		} catch (Exception e) {

		}
		return capebilityMap;
	}

}
