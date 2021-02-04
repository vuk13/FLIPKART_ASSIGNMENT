package flipkart_automation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import writeExcel.WriteExcel;

public class FlipKartPage{

	private By searchBox = By.name("q");
	private By listofProduct = By.xpath("(//div[starts-with(@class,'_13oc-S')]//div[@data-id])");
	private By amountElement = By.xpath("//div[starts-with(@class,'_13oc-S')]//div[@data-id]//div[starts-with(text(),'₹')]");
	WriteExcel obj = new WriteExcel();
	WebDriver driver;

	public void initialize()
	{
		System.setProperty("webdriver.gecko.driver", "./softwares/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	public void navigateToPage()
	{
		driver.navigate().to("https://www.flipkart.com/");
		Actions actions = new Actions(driver);
		Action action = actions.sendKeys(Keys.ESCAPE).build();
		action.perform();
	}

	public WebElement searchTheItem()
	{
		return getDriver().findElement(searchBox);
	}

	public WebDriver getDriver()
	{
		return driver;
	}

	public void fetchProductList() throws Exception
	{
		System.out.println("Device name, Price, Ratings");
		int count = 1;
		List<WebElement> list = getDriver().findElements(amountElement);
		if(list.size()!=0)
		{
			for(int i=1; i<list.size() -1; i++)
			{
				WebElement element = list.get(i);
				String amount = element.getText();
				int amont = Integer.parseInt(amount.replaceAll("[^0-9]","").toString());
				if(amont <= 40000)
				{
					String title = getDriver().findElement(By.xpath("(//div[starts-with(@class,'_13oc-S')]//div[@data-id]//a[@title])["+i+"]")).getText();
					String price = getDriver().findElement(By.xpath("(//div[starts-with(@class,'_13oc-S')]//div[@data-id]//div[@class]//a[@class][3]//div[@class][1]//div[@class][1])["+i+"]")).getText();
					String ratings = getDriver().findElement(By.xpath("(//div[starts-with(@class,'_13oc-S')]//div[@data-id]//div[@class]//div[starts-with(@class,'gUuXy- _2D5lwg')]//div[starts-with(@class,'_3LWZlK')])["+i+"]")).getText();
					System.out.println(title+" , "+price+" , "+ratings);
					count++;
					obj.writeExcel("Sheet1", title, i-1, 0);
					obj.writeExcel("Sheet1", price, i-1, 1);
					obj.writeExcel("Sheet1", ratings, i-1, 2);
				}
				if(count >= 10)
					break;
			}
		}
	}
}



