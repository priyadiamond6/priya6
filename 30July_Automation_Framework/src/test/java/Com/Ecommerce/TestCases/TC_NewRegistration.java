package Com.Ecommerce.TestCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import Com.Ecommerce.BaseClass.Ecommerce_BaseClass;
import Com.Ecommerce.PageClass.TC_Ecommerce_Registration;

public class TC_NewRegistration extends Ecommerce_BaseClass {

	@Test
	public void Registration() throws IOException {
		
		TC_Ecommerce_Registration TER  = new TC_Ecommerce_Registration();
		
		TER.RegistrationForm();
		TER.SetFirstName("Ranjeet");
		TER.SetLastName("Kendre");
		TER.SetEmail("e@gmail.com");
		TER.SetPassoword("Dell1234");
		TER.SetConfirmedPassoword("Dell1234");
		TER.Submit();
		
		boolean test=driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).isDisplayed();

		if(test==true) {
			logger.info("User created");
			AssertJUnit.assertTrue(true);
		}
		else {
			logger.info("User creation failed");
			getScreenshotAs("Registration");
			AssertJUnit.assertTrue(false);


		}
	}
   
	
}
