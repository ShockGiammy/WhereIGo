package seleniumwebdrivertests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumGianMarco {
	
	@Test
    public void test() {
        // declaration and instantiation of objects/variables
    	System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
    	
        String baseUrl = "http://localhost:8080/WhereIGo/Login.jsp";
        driver.get(baseUrl);
        driver.manage().window().maximize();
        
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Renter"); //sends the username to the login form
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Renter");
        driver.findElement(By.xpath("/html/body/form/div/div/div/input[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"create\"]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[1]/div[1]/input")).sendKeys("London");
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[1]/div[2]/input")).sendKeys("Old Street");
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/form/div[3]/div[1]/textarea")).sendKeys("A little description");
        driver.findElement(By.xpath("/html/body/div[3]/div/div[3]/a[2]")).click();
        driver.get("http://localhost:8080/WhereIGo/RentRenter");
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[5]")).click();
        
        driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Traveler"); //sends the username to the login form
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Traveler");
        driver.findElement(By.xpath("/html/body/form/div/div/div/input[2]")).click();        
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[3]")).click();
        driver.findElement(By.xpath("//*[@id=\"contact\"]")).click();
        driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div[2]/a")).click();
        String openChat = driver.findElement(By.xpath("/html/body/div/h3")).getText();
        assertEquals("Renter", openChat);
     
        driver.close();
       
    }
}
