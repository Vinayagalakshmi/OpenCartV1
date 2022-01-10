package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCompare
{

	WebDriver driver;
	public ProductCompare(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[3]")
	WebElement btnCompareProduct;
	
	public void clickCompareProduct()
	{
		btnCompareProduct.click();
	}
}
