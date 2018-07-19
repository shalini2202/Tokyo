package com.tokyo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login {
	
	    public  String url = "https://int80web1a.dev.tokyo2020.canopy-cloud.com/ ";
	    public String driverPath = "C:\\Users\\a631020\\Downloads\\";
	    public WebDriver driver ; 
	    
	    Function fun = new Function();
	    File src = null;
		public static String link = null;
	
	    
  //Launching Browser 
    @Parameters({"Browser"})
    @BeforeTest
    public void launchingBrowser(String browser) {
    	System.out.println("select browser of your choice");
    	
    	if(browser.equalsIgnoreCase("chrome")) {
    		System.out.println("launching chrome browser"); 
    	   	 
            System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
    	}
    	
    	else if(browser.equalsIgnoreCase("firefox")) {
    		System.out.println("launching firefox browser"); 
   	   	 
            System.setProperty("webdriver.firefox.marionette", driverPath+"geckodriver-v0.19.1-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(url);
    	}
        
        else if(browser.equalsIgnoreCase("ie")){
        	System.out.println("launching internet explorer browser"); 
      	   	 
            System.setProperty("webdriver.ie.driver", driverPath+"IEDriverServer_x64_3.8.0\\IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();	
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);  
			capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP,true);
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);    
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,url);
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);        	
		//	driver=new RemoteWebDriver(new URL(url),capabilities);
			driver=new InternetExplorerDriver(capabilities);
			driver.get(url); 
        }
    	
    	else {
    		System.out.println("browser not found");
    	}
  
    }
    
    
    //Goto Login Page
    @BeforeMethod
    public void loginPage() throws InterruptedException, IOException {
    	
	    Thread.sleep(2000);
	    List<WebElement> siteSelectionActual = driver.findElements(By.xpath(fun.getObjectRepositoryValue("siteSelection", "xpath")));
	    if(!siteSelectionActual.isEmpty()) {	
	    	
	      driver.findElement(By.xpath(fun.getObjectRepositoryValue("siteSelection", "xpath"))).click();
	      
	      Thread.sleep(2000);
	      driver.findElement(By.xpath(fun.getObjectRepositoryValue("paralympicEnter", "xpath"))).click();
	      
    }
    
    }
    
      
     //Login Successfully
    @Test(priority = 1)
    public void loginSuccessfully() throws InterruptedException, IOException {	
    	
			  Thread.sleep(5000);
			  driver.findElement(By.xpath(fun.getObjectRepositoryValue("idRepository", "xpath"))).sendKeys(fun.getTestData("idData"));
			  driver.findElement(By.xpath(fun.getObjectRepositoryValue("pwdRepository", "xpath"))).sendKeys(fun.getTestData("pwdData"));
    	 
    	      Thread.sleep(2000);
    	      driver.findElement(By.xpath(fun.getObjectRepositoryValue("signIn", "xpath"))).click();
	    	  
	    	  System.out.println("Login Successfully");
	    	  
	    	  Thread.sleep(2000);
	    	  link = "C:/Users/a631020/eclipse-workspace/Tokyo/src/com.Result/ScreenShot/Login_Successfully.png";
	  		  //Take Screenshot
	  		  src = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  		  FileHandler.copy(src, new File(link));
	  		  
	  		 //Write First Verification Details In Result.xlsx
	  		 Object[] detailsData1 = {"TC01", "Login_Successfully: Verify that user is  able to logged in by providing valid username and valid password.", "Pass", link} ;
	  		 fun.writeInResult(detailsData1);
    
    }
     
    
    //Wrong Password
    @Test(priority = 2)
    public void loginWrongPassword() throws InterruptedException, IOException {
    	
		    Thread.sleep(2000);
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("idRepository", "xpath"))).sendKeys(fun.getTestData("idData"));
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("pwdRepository", "xpath"))).sendKeys(fun.getTestData("pwdData1"));
    	
    	     Thread.sleep(2000);
    	     driver.findElement(By.xpath(fun.getObjectRepositoryValue("signIn", "xpath"))).click();
    		 
    		 System.out.println("Wrong Password"); 
    		 
    		 Thread.sleep(5000);
    		 link = "C:/Users/a631020/eclipse-workspace/Tokyo/src/com.Result/ScreenShot/Wrong_Password.png";
     		  //Take Screenshot
     		  src = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     		  FileHandler.copy(src, new File(link));
     		  
     		 //Write Second Verification Details In Result.xlsx
     		  Object[] detailsData1 = {"TC02", "Wrong_Password: Verify that user is not able to logged in by providing valid username and invalid password.", "Fail", link} ;
     		 fun.writeInResult(detailsData1);
 
    }
    
    
    //Wrong UserName
    @Test(priority = 3)
    public void loginWrongUserName() throws InterruptedException, IOException {
    	
		    Thread.sleep(5000);
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("idRepository", "xpath"))).clear();
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("idRepository", "xpath"))).sendKeys(fun.getTestData("idData1"));
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("pwdRepository", "xpath"))).clear();	
		    driver.findElement(By.xpath(fun.getObjectRepositoryValue("pwdRepository", "xpath"))).sendKeys(fun.getTestData("pwdData"));
		   
		   Thread.sleep(2000);
		   driver.findElement(By.xpath(fun.getObjectRepositoryValue("signIn", "xpath"))).click();
		    	  
		   System.out.println("Wrong UserName");
		    	  
		   Thread.sleep(5000);
		   link = "C:/Users/a631020/eclipse-workspace/Tokyo/src/com.Result/ScreenShot/Wrong_UserName.png";
		  //Take Screenshot
		  src = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileHandler.copy(src, new File(link));
		 		  
		  //Write Third Verification Details In Result.xlsx
		  Object[] detailsData1 = {"TC03", "Wrong_UserName: Verify that user is not able to logged in by providing invalid username and valid password.", "Fail", link} ;
		  fun.writeInResult(detailsData1);
     
    }
    
    
    //Logout
    @AfterMethod
    public void logout() throws InterruptedException, IOException {
    	
    Thread.sleep(2000);
    List<WebElement> atpActual = driver.findElements(By.xpath(fun.getObjectRepositoryValue("atp", "xpath")));
    if(!atpActual.isEmpty()){
    	
    driver.findElement(By.xpath(fun.getObjectRepositoryValue("atpparalympic", "xpath"))).click();
    driver.findElement(By.xpath(fun.getObjectRepositoryValue("logout", "xpath"))).click();
    
    }
    
    else {
    
    	System.out.println("Continue");
    }
    
    Thread.sleep(2000);
    
    }
    
    
   //close browser
    @AfterTest
    public void closeBrowser() {
   	 System.out.println("closing browser");
   	 
   	 driver.close();
   	 
    }
    

}
