package com.testapplitools.testapplitools;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Interaction;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;




public class TraditonalTests {
	public String baseUrl = "https://demo.applitools.com/hackathon.html";
	public String baseUrl1 = "https://demo.applitools.com/hackathon.html?showAd=true";
	public String baseUrl2 = "https://demo.applitools.com/hackathonV2.html";
	String driverPath = "E:\\TestApplitools\\testapplitools\\Drivers\\chromedriver.exe";
	public WebDriver driver; 
	
	@BeforeTest
	public void browserLaunch( ) throws Exception {
		
		System.setProperty("webdriver.chrome.driver", driverPath );
		driver = new ChromeDriver();
		driver.get(baseUrl2);
		
	}
	
	@Test(priority=1)
	public void HomeScreen() {
		 
		 driver.manage().window().maximize();
		 
		 Assert.assertEquals(driver.getTitle(), "ACME demo app");
		
	}
	
	@Test(priority=2)
	public void Test1() {
		
		//Trying to login without username and password
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		//Assert if error message is shown
		WebElement errmessage = driver.findElement(By.xpath("//div[@class='alert alert-warning']"));
		Assert.assertTrue(errmessage.isDisplayed());
		
		//Trying to login without password
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		//Trying to login without username
		driver.findElement(By.xpath("//input[@id='username']")).clear();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		//Assert username field
		WebElement usrname = driver.findElement(By.xpath("//input[@id='username']"));
		Assert.assertTrue(usrname.isDisplayed());
		//Assert pwd field
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		Assert.assertTrue(pwd.isDisplayed());
		
		//Login with all credentials
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		
		/*WebElement element = driver.findElement(By.xpath("//button[@id='log-in']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();
		*/
		
		WebElement element = driver.findElement(By.xpath("//button[@id='log-in']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
		
		
		WebElement RemMeCheckBox = driver.findElement(By.xpath("//label[@class='form-check-label']"));
		Assert.assertTrue(RemMeCheckBox.isDisplayed());
		
		
		WebElement TwittIcon = driver.findElement(By.xpath("//img[@src=\"img/social-icons/twitter.png\"]"));
		Assert.assertTrue(TwittIcon.isDisplayed());
		
		WebElement FBIcon = driver.findElement(By.xpath("//img[@src=\"img/social-icons/facebook.png\"]"));
		Assert.assertTrue(FBIcon.isDisplayed());
			
		try {
		WebElement LIicon = driver.findElement(By.xpath("//img[@src=\"img/social-icons/linkedin.png\"]"));
		Assert.assertTrue(LIicon.isDisplayed());
		}
		catch (Exception j){
			System.out.println("Element is not present");
		}
		
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		
		
		
	}
	
	
	@Test(priority=3)
	public void TableTest() {
		driver.findElement(By.xpath("//th[@class='text-right']")).click(); 
		
		for(int i= 0; i<6;i++) {
			List<WebElement> listOfElements = driver.findElements(By.xpath("//td[@class='text-right bolder nowrap']/span"));
		System.out.println(listOfElements.get(i).getText());
	}}
	
	@Test(priority=4)
	public void CanvasChartTest() {
		driver.findElement(By.xpath("//a[@id='showExpensesChart']")).click();
		
		driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();
	}
	
	@Test(priority=5)
	public void DynamicContentTest() {
		driver.get(baseUrl1);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("manikant.p");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@id='log-in']")).click();
		
		
		WebElement FlashIcon1 = driver.findElement(By.xpath("//img[@src='img/flashSale.gif']"));
		Assert.assertTrue(FlashIcon1.isDisplayed());
		
		WebElement FlashIcon2 = driver.findElement(By.xpath("//img[@src='img/flashSale2.gif']"));
		Assert.assertTrue(FlashIcon2.isDisplayed());
		
		
	}
	
	@AfterTest
	public void browserClose() {
		
		driver.close();
	}
	
}
