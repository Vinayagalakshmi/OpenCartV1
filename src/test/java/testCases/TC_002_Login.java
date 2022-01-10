package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC_002_Login extends BaseClass
{
	@Test(groups = {"Sanity","Master"})
	public void Test_Login() throws IOException
	{
		logger.info("Starting TC_002_Login");
		try
		{
		
		driver.get(rb.getString("appURL"));
		logger.info("HomePage Displayed");
		driver.manage().window().maximize();
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked MyAccount");
		hp.clickLogin();
		logger.info("Clicked login");
		
		LoginPage lp = new LoginPage(driver);
	
		lp.setEmail(rb.getString("email"));
		logger.info("Email provided");
		
		lp.setPassword(rb.getString("password"));
		logger.info("Password provided");
		
		lp.clickLogin();
		logger.info("Clicked login button");
		
		boolean targetPage = lp.isMyAccountPageExists();
		if (targetPage)
		{
			logger.info("Login success!!!");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("Login failed!!");
			captureScreen(driver,"Test_Login");
			
			Assert.assertTrue(false);
			
		}
		}catch(Exception e)
		{
			logger.fatal("Login failed");
			Assert.fail();

		}
		logger.info("TC_002_Login completed!!!");
	}
}
