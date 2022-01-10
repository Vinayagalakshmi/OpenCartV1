package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDataDDT extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("Starting TC_003_loginDDT");
		try
		{
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		
		logger.info("Launched Chrome browser");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked MyAccount");
		hp.clickLogin();
		logger.info("Clicked Login");
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		logger.info("Email set ");
		logger.info("Password set");
		logger.info("Login clicked");
		
		boolean targetPage = lp.isMyAccountPageExists();
		if (exp.equals("Valid"))
		{
			if(targetPage == true)
			{
				logger.info("Login success!!");
				MyAccountPage myaccpage = new MyAccountPage(driver);
				myaccpage.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
			logger.info("Login failed !!");
			Assert.assertTrue(false);
			}
			
		}
		if (exp.equals("Invalid"))
		{
			if (targetPage  == true)
			{
				MyAccountPage myaccpage = new MyAccountPage(driver);
				myaccpage.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				logger.info("Login failed ");
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			logger.fatal("Login failed");
			Assert.fail();
		}
		logger.info("TC_003_TestLogin completed");
	}

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\OpenCart-LoginData.xlsx";
		XLUtility xlutil = new XLUtility(path);
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols= xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++)
		{
			for (int j=0; j< totalCols;j++)
			{
				loginData[i-1][j] = xlutil.getCellData("Sheet1", i, j);
				
			}
		}
		return loginData;
		
	}
}
