package Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AAMLoginTest {

	public WebDriver driver;
	int a;

	public int isPresent() {
		a = driver.findElements(By.xpath("//*[@id='adobeid_signin']/fieldset[1]/span/label")).size();
		a += driver.findElements(By.xpath("//*[@id='adobeid_signin']/fieldset[2]/span/label")).size();
		return a;
	}

	@BeforeMethod
	public void setUp() {

		// please change the path before run the script
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\selenium\\Desktop\\Selenium\\Software and Drivers\\geckodriver-v0.18.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		// please change the path before run the script
		String url = "file:///C:/Users/selenium/Desktop/Selenium/AAM-login-page.html";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@AfterMethod

	public void tearDown() {

		driver.close();
	}

	@Test
	public void aamLogingTest1() {

		driver.findElement(By.xpath("//*[@id='adobeid_username']")).sendKeys("matt@");
		driver.findElement(By.xpath("//*[@id='adobeid_password']")).sendKeys("not");

		driver.findElement(By.xpath("//*[@id='sign_in']")).click();

		String expectedResultForUValidation = "Enter a valid email address";
		String expectedResultForPValidation = "The password is too short";

		String actualResultForUValidation = driver
				.findElement(By.xpath("//*[@id='adobeid_signin']/fieldset[1]/span/label")).getText();
		String actualResultForPValidation = driver
				.findElement(By.xpath("//*[@id='adobeid_signin']/fieldset[2]/span/label")).getText();

		if (expectedResultForUValidation.equals(actualResultForUValidation)
				&& expectedResultForPValidation.equals(actualResultForPValidation)) {

			System.out.println("Test Passed");
		} else {

			System.out.println("Test Failed");
			throw new RuntimeException();
		}

	}

	@Test
	public void aamLogingTest2() {

		driver.findElement(By.xpath("//*[@id='adobeid_username']")).clear();
		driver.findElement(By.xpath("//*[@id='adobeid_username']")).sendKeys("matt@adobe.com");
		driver.findElement(By.xpath("//*[@id='adobeid_password']")).clear();
		driver.findElement(By.xpath("//*[@id='adobeid_password']")).sendKeys("notnotnot");
		driver.findElement(By.xpath("//*[@id='sign_in']")).click();

		// To validate that “Enter a valid email address” and “The password is too
		// short” disappeared
		int b = isPresent();

		if (b == 0) {
			System.out.println("Test Passed");

		} else {

			System.out.println("Test Failed");
			throw new RuntimeException();
		}

	}

}
