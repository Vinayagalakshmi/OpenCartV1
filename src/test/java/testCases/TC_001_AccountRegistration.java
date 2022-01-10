package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass
{
	@Test(groups = {"Regression","Master"})
	
	public  void Test_Account_Registration() throws IOException 
	{
		logger.info("Starting TC_001_AccountRegistration ");
		logger.info("Opening Application URL");
		driver.get(rb.getString("appURL"));
		//driver.get("https://demo.opencart.com/");
		driver.manage().window().maximize();
		
		HomePage hp = new HomePage(driver);
		logger.info("Clicking on MyAccount link");
		hp.clickMyAccount();
		logger.info("Clicking on Register link");
		hp.clickRegister();
		
		logger.info("Providing user details ");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName("FirstName");
		logger.info("Provided first name");
		regpage.setLastName("LastName");
		logger.info("Provided last name");

		regpage.setEmail(randomString()+"@gmail.com");
		logger.info("Provided email");

		regpage.setTelephone("7362467851");
		logger.info("Provided Telephone number");

		regpage.setPassword("abc123");
		regpage.setConfirmPassword("abc123");
		regpage.setPrivacyPolicy();
		//Thread.sleep(4000);
		regpage.clickContinue();
		String message = regpage.getConfirmationMessage();
		System.out.println(message);
		if (message.equals("Your Account Has Been Created!"))
		{
			logger.info("Account registration success!!");
			Assert.assertTrue(true);
		}
		else
		{
			System.out.println("Amin else condition");
			captureScreen(driver,"Test_Account_Registration");
			logger.info("Account registration failed!!");
			Assert.assertTrue(false);
		}
		logger.info("TC_001_AccountRegistration execution completed!");
	}
}
