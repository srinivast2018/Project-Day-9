package com.ibm.groceriespages;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class PageProducts {
	
	//Locating no.of products
	@FindAll(@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr"))
	List<WebElement> productRows;
	
	//Locating action button
	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr[1]/td[9]/descendant::button")
	WebElement actionEle;
	
	//Locating delete web element
	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr[1]/td[9]/descendant::a[2]")
	WebElement deleteEle;
	
	//Locating confirm button
	//@FindBy(xpath="//button[@className='confirm']")
	@FindBy(xpath="//button[text()='Yes, delete it!']")
	WebElement confirmEle;
	By confirmButton=By.xpath("//div[@class='sa-confirm-button-container']/descendant::button");
	
	
	//Locating Edit web element
	@FindBy(xpath="//table[@id='dataTableExample2']/tbody/tr[1]/td[9]/descendant::a[1]")
	WebElement editEle;
			
	@FindBy(xpath="//select[@name='dataTableExample2_length']")
	WebElement entryEle;
	
	
	WebDriverWait wait;
	WebDriver driver;
	
	public PageProducts(WebDriver driver,WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		this.wait=wait;
	}
	
	//Method to delete the product
	public String deleteProduct() throws InterruptedException
	
	{
		//Verifying if number of products greater than zero	
		if(productRows.size()>0)
		{
			System.out.println("The list of products are dipslayed");
			Thread.sleep(10000);
			//To click on action button
			actionEle.click();
			//To click on Delete button
			deleteEle.click();
			Thread.sleep(10000);
			
			wait.until(ExpectedConditions.presenceOfElementLocated(confirmButton));	
			
			//To click on confirm button
			confirmEle.click();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		return driver.getPageSource();
	}
	
	//Method to clikcon on edit button to edit product
	public void selectProduct()
	{
	if(productRows.size()>0)
		{
		//to click on on action web element
		actionEle.click();
		
		//to click on Edit link
		editEle.click();
			
		}
	
	
	}
	
	public String selectEntry()
	{
		WebElement entryElt=driver.findElement(By.xpath("//select[@name='dataTableExample2_length']"));
		//Select selectEntry=new Select(entryEle);
		Select selectEntry=new Select(entryElt);
		selectEntry.selectByVisibleText("All");
		return driver.getPageSource();
	}
	
}
