package com.tokyo;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestScript {
	  public  String url = "https://int80web1a.dev.tokyo2020.canopy-cloud.com/ ";
	    public String driverPath = "C:\\Users\\a631020\\Downloads\\";
	    public WebDriver driver ; 
	    private boolean blnNextStep;
	    Function fun = new Function();
	    File src = null;
		public static String link = null;
	    
	    public String idActual;
	    public String pwdActual;
	
	    @Parameters({"Browser"})
		@Test
		public void TC_ValidateLogin(String browser) {
	    	try {
			//Browser Launch Function(browser)
	    	/*
	    	 * Reading test data by using for loop
	    	 * for(int i = 1;i<3;i++){
	    	 * idActual = USERNAME;
	    	 * pwdActual = PASSWORD;
	    	 * blnNextUp = true;
	    	 * if(blnNextUp){
	    	 * 	blnNextStep = LOGIN()
	    	 * }
	    	 * if(blNextStep){
	    	 * 	blnNextStep = Logout
	    	 * }
	    	 *
	    	 * 
	    	 * }
	    	 */
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}finally {
	    		driver.close();
	    	}	
		}
}
