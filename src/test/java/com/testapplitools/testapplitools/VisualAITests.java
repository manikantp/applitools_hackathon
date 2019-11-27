package com.testapplitools.testapplitools;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

@RunWith(JUnit4.class)
public class VisualAITests {

	private static EyesRunner runner;
	private static Eyes eyes;
	private static BatchInfo batch;
	private static WebDriver driver;
	
	public String baseUrl = "https://demo.applitools.com/hackathon.html";
	public String baseUrl1 = "https://demo.applitools.com/hackathon.html?showAd=true";
	public String baseUrl2 = "https://demo.applitools.com/hackathonV2.html";
	public static String driverPath = "E:\\TestApplitools\\testapplitools\\Drivers\\chromedriver.exe";
	
	
	@BeforeClass
	public static void setBatch() {
		batch =  new BatchInfo("Login Test Visual AI");
		runner = new ClassicRunner();
		
		eyes = new Eyes(runner);
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		eyes.setBatch(batch);
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		
		
		
		
	}
	
	@Test
	public void loginTest() {
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		eyes.open(driver, "Login App", "Login Page Test", new RectangleSize(1366,440));
		eyes.checkWindow("Before Login");
		
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		eyes.checkWindow("After clicking Login");
		
		//Trying to login without password
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		eyes.checkWindow("Trying login without password");
				
				//Trying to login without username
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		eyes.checkWindow("Trying login without username");
		
		// Login with valid credentials
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		eyes.checkWindow("Trying login with valid credentials");
		
		//Table
		driver.findElement(By.xpath("//th[@class='text-right']")).click(); 
		
		for(int i= 0; i<6;i++) {
			List<WebElement> listOfElements = driver.findElements(By.xpath("//td[@class='text-right bolder nowrap']/span"));
		System.out.println(listOfElements.get(i).getText());
		}
		
		eyes.checkWindow("Table Contents");
		
		// Expenses Chart
		driver.findElement(By.xpath("//a[@id='showExpensesChart']")).click();
		
		driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();
		eyes.checkWindow("Expenses Chart");
		
		driver.get(baseUrl1);
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		
		WebElement FlashIcon1 = driver.findElement(By.xpath("//img[@src='img/flashSale.gif']"));
		Assert.assertTrue(FlashIcon1.isDisplayed());
		
		WebElement FlashIcon2 = driver.findElement(By.xpath("//img[@src='img/flashSale2.gif']"));
		Assert.assertTrue(FlashIcon2.isDisplayed());
		
		eyes.checkWindow("Flash Sale gifs");
		
		eyes.closeAsync();
		driver.close();
	}
}
