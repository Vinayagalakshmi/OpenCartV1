package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{
	WebDriver driver;
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement txtSearch;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement btnSearch;
	
     @FindBy(xpath="//p[contains(text(),'iMac')]")
	//@FindBy(xpath="//div[@class='caption']//h4")
	WebElement productName;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement msgProductNotFound;
	
	
	
	@FindBy(xpath="//*[@id=\"product-search\"]/div[1]/text()[1]")
	WebElement msgProductAdded;
	
	
	
	public void setSearchText(String product)
	{
		txtSearch.sendKeys(product);
	}
	
	public void clickSearch()
	{
		btnSearch.click();
	}
	
	public boolean isCompareProductClicked()
	{
		try
		{
			return msgProductAdded.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean isProductExists()
	{
		try
		{
		return productName.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean isProductNotExists()
	{
		try
		{
		return msgProductNotFound.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
