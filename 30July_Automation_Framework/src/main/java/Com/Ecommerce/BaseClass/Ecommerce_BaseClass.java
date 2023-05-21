package Com.Ecommerce.BaseClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import Com.Ecommerce.Configuration.Readconfiguration;
import Com.Ecommerce.Utilitities.TestUtils;
import net.bytebuddy.utility.RandomString;

public class Ecommerce_BaseClass {

	Readconfiguration readconfig = new Readconfiguration();	
	
	public String BaseURL = readconfig.getApplicationURL();
	public String username =readconfig.getUsername();
	public String password =readconfig.getPassword();	
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("Browser")
	@BeforeMethod 
	public void initialization(String Browsernames) {
		
		logger = logger.getLogger("30JulyEcommerce");
		PropertyConfigurator.configure("Log4j.properties");

		if(Browsernames.equals("Chrome")){
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		driver = new ChromeDriver();
		}
		else if(Browsernames.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.Geckodriver());
			driver = new FirefoxDriver();	
		}
		else if(Browsernames.equals("Edge")){
			System.setProperty("webdriver.edge.driver", readconfig.Geckodriver());
			driver = new EdgeDriver();	
		}
		else if(Browsernames.equals("IE")) {
			System.setProperty("webdriver.Ie.driver", readconfig.Geckodriver());
			driver = new InternetExplorerDriver();	
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(BaseURL);
	}
	
	public String getScreenshotAs(String TestCasename) throws IOException {		
		
		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String Timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String Name = "- Screenshot -"+Timestamp;

		String destination = System.getProperty("user.dir") + "/Screenshot/" + TestCasename + "" + Name + ".png";

		FileUtils.copyFile(source, new File(destination));

		return destination;
			
	}
	
	@AfterMethod
	public void TearDown() {
		driver.quit();
	}
}
