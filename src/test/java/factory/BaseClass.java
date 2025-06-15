package factory;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class BaseClass {

	static WebDriver driver;
	static Properties p;
	static Logger logger;

	public static WebDriver initilizeBrowser() throws IOException {
		if (getProperties().getProperty("exicution_env").equalsIgnoreCase("remote")) {

			// os
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (getProperties().getProperty("os").equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (getProperties().getProperty("os").equalsIgnoreCase("mac"))

			{
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS");
			}

			// browser

			switch (getProperties().getProperty("browser").toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;

			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;

			case "firefox":
				capabilities.setBrowserName("firefox");
				break;

			default:
				System.out.println("No matching browser");

			}
			driver = new RemoteWebDriver(new URL("http://192.168.158.188:4444"), capabilities);

		}
		
		else if(getProperties().getProperty("exicution_env").equalsIgnoreCase("local"))
		{
			switch (getProperties().getProperty("browser").toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;

			case "edge":
				driver = new EdgeDriver();
				break;

			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("No matching browser");
				driver =null;
			}
				
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		
		return driver;
				
		

	}

	public static WebDriver getDriver() {
		return driver;

	}

	public static Properties getProperties() throws IOException {
		FileReader file = new FileReader("C:\\Users\\pratik.kadam\\eclipse-workspace\\OpenCart_Cucumber\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		return p;

	}
	
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
	
	public static String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public static String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
		
	public static String randomAlphaNumeric()
	{
	String str=RandomStringUtils.randomAlphabetic(5);
	 String num=RandomStringUtils.randomNumeric(10);
	return str+num;
	}
}
