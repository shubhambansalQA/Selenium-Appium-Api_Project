package testScripts;

import java.lang.reflect.Method;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import util.CommonFunction;
import util.ConfigFileReader;

public class BaseTest {

	CommonFunction cfObj = new CommonFunction();
	public static AppiumDriver<MobileElement> driver;

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	@BeforeSuite
	public void startSuite() {
		try {

			driver = cfObj.commonStartAndOpenURLBrowser();
			System.out.println("SessionId: " + driver.getSessionId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void setUp(Method method) {
		String strRunMode, strTestCaseName;
		try {
			strRunMode = ConfigFileReader.strRunMode;
			strTestCaseName = method.getName();

			if (driver == null) {

				driver = cfObj.commonStartAndOpenURLBrowser();
				System.out.println("SessionId: " + driver.getSessionId());
			}

			if (strRunMode.equalsIgnoreCase("Cloud")) {

				driver.executeScript("lambda-name=" + strTestCaseName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		try {

			driver.resetApp();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void endSuite() {
		try {

			driver.resetApp();

			if (driver != null) {
				driver.quit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
