package util;

public class ConfigFileReader {
	
	public static String strEnv;
	public static String strDeviceName, userName, accessKey, strDeviceVersion, strRunMode, strDeviceIndex, strFilePath,
			strUserMobileNumber, strRetryCount;

	public static String strApplicationType, is_quiz_old_flow, is_download_old_flow, is_onboarding_old_flow;
	static {

		strEnv = "stag";
		userName = "shubham.bansal";
		accessKey = "22Nq6SjYmwT02sOKkpzDJCeSQmtE2kG8glK61VlSCTzqKu62xK";
		strRunMode = "local";
		strDeviceName = "Moto G30";
		strDeviceVersion = "11";
		strDeviceIndex = "0";
		strUserMobileNumber = "9878252339";
		strFilePath = "lt://APP10160522261722236868092191"; //main
		strApplicationType = "Android"; // Android

		// strFilePath = "lt://APP101605731695039538308509"; // IOS
		// strApplicationType = "IOS"; // IOS

		strRetryCount = "0";
		is_download_old_flow = "true";
		is_quiz_old_flow = "false";
		is_onboarding_old_flow = "true";

//		strEnv = System.getProperty("env");
//		strDeviceName = System.getProperty("deviceId");
//		userName = System.getProperty("userName");
//		accessKey = System.getProperty("accessKey");
//		strDeviceName = "Moto G30";
//		strDeviceVersion = "11";
//		strRunMode = System.getProperty("runMode");
//		strDeviceIndex = System.getProperty("deviceIndex");
//		strFilePath = System.getProperty("apkId");
//		strUserMobileNumber = System.getProperty("mobileNumber");
//		strApplicationType = System.getProperty("applicationType");
//		strRetryCount = System.getProperty("strRetryCount");
//		is_download_old_flow = System.getProperty("is_download_old_flow");
//		is_quiz_old_flow = System.getProperty("is_quiz_old_flow");
//		is_onboarding_old_flow = System.getProperty("is_onboarding_old_flow");
	}

}
