import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BigBinaryAssignment {
	private WebDriver driver;
	
	@BeforeTest																			//Pre condition to be executed before all the cases
	public void beforeTest() {
		try {
			System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver.exe");  //setting the path for selenium webdriver
	        driver = new ChromeDriver();  													 //instantiating ne chrome driver (note:- we can use IE,safari,mozilla etc)
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);				 //Implicit wait of 10 SEC
	        driver.manage().window().maximize();											 //Maximizing the window
	        driver.get("https://staging.aceinvoice.com/sign_in");							// Navigating to the URL
		}
		catch(Exception E) {
			System.out.println("Something went wrong while instantiating the browser");
		}	
	}
	
	@Test																				//Test case scenario
    public void test() throws Exception {
		try {
			Random Rand = new Random();														//Raandom number genrator
			int val = Rand.nextInt(10000);													//can genrate upto 10000 random numbers, this can be changed based on necessity
			System.out.println(val);
			driver.findElement(By.xpath("//*[@href='/sign_up']")).click();					//Clicking the SignUp link
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//*[@id='new_user']//input[@class='form-control']")).sendKeys("abc+"+val+"@abc.com");		//Entering Random emailID
	        driver.findElement(By.xpath("//*[@id='new_user']//input[@name='get_started']")).click();								//Clicking on Get Started
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//*[@id='new_user']//input[@name='password']")).sendKeys("abc123");						//Entering the Password
	        driver.findElement(By.xpath("//*[@id='new_user']//input[@name='password_confirmation']")).sendKeys("abc123");			//Reentering the password
	        driver.findElement(By.xpath("//input[@type='submit']")).click();														//Clicking on Continue buttons
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//input[@class='form-control'][@name='user[first_name]']")).sendKeys("abc");				//First name
	        driver.findElement(By.xpath("//input[@class='form-control'][@name='user[last_name]']")).sendKeys("abc");				//Last name
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//input[@class='btn btn-primary'][@type='submit']")).click();								//Clicking on Continue buttons
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//input[@class='form-control'][@name='name']")).sendKeys("bb");							//Organization name	
	        driver.findElement(By.xpath("//input[@class='form-control'][@name='email']")).sendKeys("bb@bigbinary.com");				//Organization email
	        driver.findElement(By.xpath("//input[@value='Continue'][@type='submit']")).click();										//Clicking on Continue buttons
	        Thread.sleep(5000L);
	        driver.findElement(By.linkText("Skip this step")).click();																//Skip the step link
	        Thread.sleep(5000L);
	        driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();													//Continue to APP link
	        Thread.sleep(5000L);
	        
	        System.out.println(driver.findElement(By.xpath("//*[@id='team-members']//td[3]")).getText());
	        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='team-members']//td[2]/a")).getText(),"abc abc");				//Asserting the name
	        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='team-members']//td[3]")).getText(),"abc+"+val+"@abc.com");	//Asserting the EmailID
		}
		catch(Exception E){
			System.out.println("Something went wrong in test");
		}
		
    }
	
	@AfterTest									//Post completion of all test cases
	public void afterTest() {
		try {
			 driver.close();						//Closing the Browser We can use driver.quit() also	
		}
		catch(Exception E) {
			System.out.println("Something went wrong while closing browser");
		}
	}
}
