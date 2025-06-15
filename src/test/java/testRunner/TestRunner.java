package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( // features= {".//feature/"},
					// features= {".//feature/Login.feature"},
					 features= {".//feature/Login.feature",".//feature/Registration.feature"},
					// features= {".//feature/Registration.feature"},
		            //features = {".//feature/LoginDDTExcel.feature"},
					//  features= {"@target/rerun.txt"},	

		glue = "stepDefinations", 
		
		plugin = { "pretty", "html:reports/myreport.html",

				    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					
					"rerun:target/rerun.txt",
				 
					 },

		dryRun = false, monochrome = true, publish = true

		// tags="sanity"
		// tags="@regression"
		// tags= "@sanity" and "@regression"
		// tags= "@sanity" and not "@regression"
		//tags = "@sanity  or  @regression"

)
public class TestRunner {

}
