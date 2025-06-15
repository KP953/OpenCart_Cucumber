package stepDefinations;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {

	WebDriver driver;
	HomePage hp;
	LoginPage lp;
	MyAccountPage macc;

	List<HashMap<String, String>> datamap;

	@Given("the user navigate to the login page")
	public void the_user_navigate_to_the_login_page() {

		BaseClass.getLogger().info("Goto MyAccount Page --> Click on Login......");
		hp = new HomePage(BaseClass.getDriver());
		hp.clickMyAccount();
		hp.clickLogin();

	}

	@When("user enter the email as {string} and password as {string};")
	public void user_enter_the_email_as_and_password_as(String username, String pswd) {
		BaseClass.getLogger().info("Entering email and password");
		lp = new LoginPage(BaseClass.getDriver());
		lp.setEmail(username);
		lp.setPassword(pswd);

	}

	@When("the user clicks on the loggin button")
	public void the_user_clicks_on_the_loggin_button() {

		lp.clickLogin();
		BaseClass.getLogger().info("clicked on login button...");

	}

	@Then("the user should redirected to the MyAccount Page")
	public void the_user_should_redirected_to_the_my_account_page() {

		macc = new MyAccountPage(BaseClass.getDriver());
		boolean targetpage = macc.isMyAccountPageExists();
		Assert.assertEquals(targetpage, true);

	}

	// ****Data Driven */////

	@Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
	public void the_user_should_be_redirected_to_the_my_account_page_by_passing_email_and_password_with_excel_row(
			String rows) {

		try {
			datamap = DataReader.data(
					"C:\\Users\\pratik.kadam\\eclipse-workspace\\OpenCart_Cucumber\\testData\\Opencart_LoginData.xlsx",
					"Sheet1");
		} catch (IOException e) {
			e.getStackTrace();
		}

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		String exp_res = datamap.get(index).get("res");

		lp = new LoginPage(BaseClass.getDriver());
		lp.setEmail(email);
		lp.setPassword(pwd);

		lp.clickLogin();

		macc = new MyAccountPage(BaseClass.getDriver());
		
		try {
		boolean targetPage = macc.isMyAccountPageExists();
		System.out.println("target page: " + targetPage);

		if (exp_res.equalsIgnoreCase("valid")) {
			if (targetPage == true) {
				MyAccountPage myaccpage = new MyAccountPage(BaseClass.getDriver());
				myaccpage.clickLogout();
				Assert.assertTrue(true);

			}

			else {
				Assert.assertTrue(false);

			}
		}

		if (exp_res.equalsIgnoreCase("Invalid")) {
			if (targetPage == true) {
				MyAccountPage myaccpage = new MyAccountPage(BaseClass.getDriver());
				myaccpage.clickLogout();
				Assert.assertTrue(false);

			}

			else {
				Assert.assertTrue(true);

			}

		}

	
	}
	catch(Exception  e)
			{
		    Assert.assertTrue(false);
			}
		
			}

}
