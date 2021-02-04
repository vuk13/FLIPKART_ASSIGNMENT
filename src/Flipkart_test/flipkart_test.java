package Flipkart_test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import flipkart_automation.FlipKartPage;

public class flipkart_test {
	public static void main(String[] args) throws Exception {
		
		FlipKartPage flipkart = new FlipKartPage();
		flipkart.initialize();
		flipkart.navigateToPage();
		flipkart.searchTheItem().sendKeys("Iphone");
		performAction(flipkart.getDriver());
		Thread.sleep(2000);
		flipkart.fetchProductList();
	}
	
	public static void performAction(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
	}
}
