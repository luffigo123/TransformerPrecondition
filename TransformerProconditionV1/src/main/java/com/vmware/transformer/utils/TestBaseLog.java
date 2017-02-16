package com.vmware.transformer.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.vmware.g11n.log.GLogger;
import com.vmware.g11n.log.TestCaseConfig;
import com.vmware.g11n.log.TestSetConfig;


public abstract class TestBaseLog {
	public String featureName = null;
	public String testId = null;
	public String testName = null;
	public String testDescription = null;
	public static GLogger log = GLogger.getInstance(TestBaseLog.class.getName());
	
	//get log module var values
	private String bu = DefaultEnvironment.BU;
	private String product = DefaultEnvironment.Product;
	private String buildId = DefaultEnvironment.BuildId;
	private String buildType = DefaultEnvironment.BuildType;
	
	private String branch = DefaultEnvironment.Branch;
	private String testType = DefaultEnvironment.TestType;
	private String locale = DefaultEnvironment.Locale;
	private String hostOS = DefaultEnvironment.HostOS;
	
	private String hostOSPlatform = DefaultEnvironment.HostOSPlatform;
	private String serverBuildId = DefaultEnvironment.ServerBuildID;
	private String browserType = DefaultEnvironment.BrowserType;

	private String browserPlatform = DefaultEnvironment.BrowserPlatform;
	private String browserVerNo = DefaultEnvironment.BrowserVerNo;
	private String user = DefaultEnvironment.User;
	private String logOnRacetrack = DefaultEnvironment.LogOnRacetrack;
	
	private String release = DefaultEnvironment.Release;
	
	
	
	/**
	 * Method to initialize all necessary instance, create VC session
	 */
	@BeforeSuite(alwaysRun=true)
	public void suiteSetUp() throws Exception {
		System.out.println("=============TestBase.suiteSetUp==============");
//		log = GLogger.getInstance(TestBaseLog.class.getName());
		log.enableScreenshot(false);
		log.setConfig(bu, product, release,
				buildId, buildType,
				branch, testType, locale, hostOS, 
				hostOSPlatform, serverBuildId, browserType,
				browserPlatform, browserVerNo, user, logOnRacetrack);
		TestSetConfig testSetConfig = new TestSetConfig("Bumblebee Test Set");
		log.testSetBegin(testSetConfig);
	}
	
	/**
	 * Every test case must implement this method
	 * and mark as annotation '@BeforeTest'.
	 * In the method, call super method 'setCaseInfo()' 
	 *  by given all test case info like ID, Name, Feature, Description
	 */
	public abstract void testBegin()
		      throws Exception;
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param feature
	 * @param description
	 * @param type
	 * @param priority
	 * @throws Exception 
	 */
	public void setCaseInfo(String id, String name,
			String feature, String description, String type, String priority) throws Exception{
		this.testId = id;
		this.testName = name;
		this.featureName = feature;
		this.testDescription = description;
		
		//id , name, feature, tpye, priority
			TestCaseConfig testCaseConfig = new TestCaseConfig(id,name, feature, type, priority);
			log.testCaseBegin(testCaseConfig);

		
	}
	
	@AfterClass(alwaysRun=true)
	public void testEnd(){
		try {
			log.testCaseEnd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to clean up
	 */
    @AfterSuite(alwaysRun=true)
    public static void suiteCleanUp() {
        System.out.println("=============TestBase.suiteCleanUp==============");
        try {
			log.testSetEnd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
