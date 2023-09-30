package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.POM.HomePage;
import com.Actitime.POM.LoginPage;

public class BaseClass 
{
	public static WebDriver driver;
	@BeforeSuite
	public void databaseConnection()
	{
		Reporter.log("Database Connected", true);
	}
	@BeforeClass
	public void launchBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://demo.actitime.com/login.do");
		Reporter.log("Browser Launched Successfully",true);
	}
	@BeforeMethod
	public void loginToActitime() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		FileLibrary f = new FileLibrary();
		String un = f.readDataFromProperty("username");
		lp.getUntx().sendKeys(un);
		String pwd = f.readDataFromProperty("password");
		lp.getPwtx().sendKeys(pwd);
		lp.getLgbtx().click();
		Reporter.log("Logged In Successfully",true);
	}
	@AfterMethod
	public void logoutFromActitime()
	{
		HomePage hp = new HomePage(driver);
		hp.getLogoutlink().click();
		Reporter.log("Logged Out Successfully",true);
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
		Reporter.log("Browser Closed Successfully",true);
	}
	@AfterSuite
	public void databaseDisconnected()
	{
		Reporter.log("Database Disconnected Successfully",true);
	}
}