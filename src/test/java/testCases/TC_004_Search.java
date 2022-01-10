package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC_004_Search extends BaseClass
{
	@Test(priority=1)
	public void Test_Search_Exists()
	{
		logger.info("TC_004_Search started");
		try
		{
			logger.info("Launching ChromeBrowser");
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
			logger.info("Window maximised");
			
			SearchPage sp = new SearchPage(driver);
			logger.info("Search product set");
			sp.setSearchText(rb.getString("searchExisting"));
			sp.clickSearch();
			logger.info("Clicked on Search button");
			sp.clickCompareProduct();
			logger.info("Clicked on compare product button");
			Thread.sleep(4000);

			boolean isProductAvailable = sp.isProductExists();
			if (isProductAvailable)
			{
				
				logger.info("Product is found");
				Assert.assertTrue(true);
				
			}
			else
			{
				logger.info("Product not found");
				captureScreen(driver, "Test_Search_Exists");
			Assert.assertTrue(false);
				
			}
			
			
		}
		catch(Exception e)
		{
			logger.fatal("Search failed");
			Assert.fail();
		}
	}
		
	@Test(priority=2)
	public void Test_Search_NotExists()
	{
		logger.info("TC_004_Search started");
		try
		{
			logger.info("Launching ChromeBrowser for searching a non existing product");
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
			logger.info("Window maximised");
			
			SearchPage sp = new SearchPage(driver);
			logger.info("Search product set to a non existing item");
			sp.setSearchText(rb.getString("searchNonExisting"));
			sp.clickSearch();
			Thread.sleep(4000);
			logger.info("Clicked on Search button");
						
			boolean isMessageDisplayed = sp.isProductNotExists();
			if (isMessageDisplayed)
			{
				
				logger.info("item not found");
				Assert.assertTrue(true);
				
			}
			else
			{
				logger.info("Search Error");
				captureScreen(driver, "Test_Search_NotExists");
			Assert.assertTrue(false);
				
			}		
		}
		catch(Exception e)
		{
			logger.fatal("Search failed");
			Assert.fail();
		}
	}

}
