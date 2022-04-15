package stepDefinition;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class SauceLabSteps {
    public WebDriver driver;
	@Given("user is on saucedemo homepage")
	public void user_is_on_saucedemo_homepage() {

		System.setProperty("webdriver.chrome.driver", "D:\\Test Automation Software - Swati\\chromedriver_win32\\chromedriver.exe");

		 driver= new ChromeDriver();
		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();
		
	}

	@Given("user logged in using correct credential")
	public void user_logged_in_using_correct_credential(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
		
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys(data.get(0).get("username"));
    	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(data.get(0).get("password"));
    	
    	driver.findElement(By.xpath("//input[@id='login-button']")).submit();
    	
    	
	}

	@Given("user adds required item to cart")
	public void user_adds_required_item_to_cart() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
	    List<WebElement> AllItems=driver.findElements(By.xpath(("//button[text()='Add to cart']")));
	    int NumberofItems=AllItems.size();
	    
	    System.out.println("Number of Items are: "+NumberofItems);
	    
	    for (int i=0;i<NumberofItems-3;i++) {
	    	
	    	driver.findElements(By.xpath("//button[text()='Add to cart']")).get(i).click();
	    }
	}

	@Given("user proceeds to checkout")
	public void user_proceeds_to_checkout() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	    driver.findElement(By.xpath("//button[@id='checkout']")).click();
	}

	@Given("user enters the following details for checkout")
	public void user_enters_the_following_details_for_checkout(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		List<Map<String,String>> data = dataTable.asMaps(String.class,String.class);
		driver.findElement(By.cssSelector("input#first-name")).sendKeys(data.get(0).get("FirstName"));
    	driver.findElement(By.cssSelector("input#last-name")).sendKeys(data.get(0).get("LastName"));
    	driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(data.get(0).get("PostalCode"));
	    
	}

	@When("user confirm checkout")
	public void user_confirm_checkout() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.xpath("//input[@id='continue']")).click();
	    driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
	}

	@SuppressWarnings("deprecation")
	@Then("user verify final confirmation messagge")
	public void user_verify_final_confirmation_messagge() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.xpath("//div[@id=\"checkout_complete_container\"]/h2")).getText();
	    Assert.assertEquals("THANK YOU FOR YOUR ORDER", driver.findElement(By.xpath("//div[@id=\"checkout_complete_container\"]/h2")).getText());
	}

	@Given("user adds one item and then remove that item to go back")
	public void user_adds_one_item_and_then_remove_that_item_to_go_back() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
		 driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		 driver.findElement(By.xpath("//button[text()='Remove']")).click();
		 
		 driver.findElement(By.cssSelector("button#continue-shopping")).click();
	    
	}

	@SuppressWarnings("deprecation")
	@Then("user verify final confirmation message")
	public void user_verify_final_confirmation_message() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//div[@id=\"checkout_complete_container\"]/h2")).getText();
	    Assert.assertEquals("THANK YOU FOR YOUR ORDER", driver.findElement(By.xpath("//div[@id=\"checkout_complete_container\"]/h2")).getText());
	}

	@Given("user sorts item low to high")
	public void user_sorts_item_low_to_high() {
	    // Write code here that turns the phrase above into concrete actions
	    Select s= new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
	    s.selectByValue("lohi");
	    
	}


}

