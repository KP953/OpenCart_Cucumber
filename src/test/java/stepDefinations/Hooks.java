package stepDefinations;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	

	Properties p;
	
	@Before
	public void setup() throws IOException
	{
		WebDriver driver=BaseClass.initilizeBrowser();
		p=BaseClass.getProperties();
		driver.get(p.getProperty("appurl1"));
		driver.manage().window().maximize();
		
	}
	
	@After
	public void tearDown()
	{
		
		WebDriver driver = BaseClass.getDriver();
	    if (driver != null) {
	        driver.quit();
	    }
		
	}
	
	@AfterStep
	public void addScreenshot(Scenario scenario)
	{
		
		WebDriver driver = BaseClass.getDriver();
		if(scenario.isFailed())
		{
			TakesScreenshot ts=(TakesScreenshot) driver;
			byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png",scenario.getName());
		}
	}

}
