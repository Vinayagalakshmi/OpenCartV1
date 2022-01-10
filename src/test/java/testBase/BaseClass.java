package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@SuppressWarnings("deprecation")
	@BeforeClass (groups = {"Master", "Regression","Sanity"})
	@Parameters({"browser"})
	public void setup(String br)
	{
		
		//Load config.properties file from /src/test/resources
		rb = ResourceBundle.getBundle("config");
		
		//Logging
		logger = LogManager.getLogger(this.getClass());
		
		//Driver based on different browser
		if (br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
			driver=new ChromeDriver();
			logger.info("Launched Chrome browser");
		}
		else if (br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			logger.info("Launched Edge browser");		
		}
		else if (br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(true);
		
			driver=new FirefoxDriver();
			logger.info("Launched Firefox browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass(groups = {"Master", "Regression","Sanity"})
	public void tearDown()
	{
	driver.quit();
	}
	
	
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}
	
	public int randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString2));
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
	}
}