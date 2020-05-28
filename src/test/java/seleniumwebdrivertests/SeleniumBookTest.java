package seleniumwebdrivertests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumBookTest {
	@Test
	public void testBookShort() {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		WebDriver chromed = new ChromeDriver();
		chromed.get("http://localhost:8080/WhereIGo/Login.jsp");
		chromed.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Traveler"); //sends the username to the login form
		chromed.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Traveler");
		chromed.findElement(By.xpath("/html/body/form/div/div/div/input[2]")).click();
		chromed.findElement(By.xpath("//*[@id=\"booktrav\"]")).click();
		chromed.findElement(By.xpath("/html/body/form/div/div/div[1]/div/div[1]/div[3]/a")).click();
		WebElement depCityConf = chromed.findElement(By.xpath("/html/body/div/div/div[10]"));
		String confCity = depCityConf.getText();
		chromed.findElement(By.xpath("/html/body/div/div/div[15]/a")).click();
		chromed.findElement(By.xpath("/html/body/form/div/div[2]/div[4]/input[1]")).click();
		WebElement depCity = chromed.findElement(By.id("depCity"));
		String actualCity = depCity.getText();
		chromed.findElement(By.xpath("/html/body/form/div/div/div[1]/table/tbody/tr/td[7]/a")).click();
		assertEquals(confCity, actualCity);
		chromed.close();
	}
}
