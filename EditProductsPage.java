package com.ibm.groceriespages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.GetScreenshot;

public class EditProductsPage {
	
	//To located product name 
	@FindBy(xpath = "//input[@id='pro_name']")
	WebElement nameEle;

	// To locate Data link
	@FindBy(xpath = "//a[text()='Data']")
	WebElement dataEle;

	// To locate model text box
	@FindBy(xpath = "//input[@id='model']")
	WebElement modelEle;

	// To locate save button
	@FindBy(xpath = "//button[@title='Save']")
	WebElement saveEle;
	
	//To locate Meta Title textbox
	@FindBy(xpath="//input[@id='meta_title']")
	WebElement titleEle;
	
	//To locate Meta Tag description text are field
	@FindBy(xpath="//textarea[@id='meta_tag_desc']")
	WebElement tagDescriptionEle;
	
	//To locate Meta Tag Keywords
	@FindBy(xpath="//textarea[@id='meta_tag_keyword']")
	WebElement keywordEle;

	//To locate HSN textbox
	@FindBy(xpath="//input[@id='hsn']")
	WebElement hsnEle;
	
	
	WebDriverWait wait;
	WebDriver driver;

	public EditProductsPage(WebDriver driver, WebDriverWait wait) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = wait;
	}

	public String editProduct(String productNewname, String modelNewname) {
		// To enter the new name for product
		nameEle.clear();
		nameEle.sendKeys(productNewname);

		// To click on Data link
		dataEle.click();

		// To enter the new name for model
		modelEle.clear();
		modelEle.sendKeys(modelNewname);

		// To click on Save button
		saveEle.click();
		return driver.getPageSource();
	}
	
	public String editProductDbase(String titleNew,String tagdescNew,String keywordNew,String modelNewname,String hsnNew) throws IOException, InterruptedException
	{
		// To enter the new name for product
		//	nameEle.clear();
		//	nameEle.sendKeys(productNewname);
		GetScreenshot screen = new GetScreenshot();
		
		titleEle.clear();
		titleEle.sendKeys(titleNew);
		
		tagDescriptionEle.clear();
		tagDescriptionEle.sendKeys(tagdescNew);
		
		keywordEle.clear();
		keywordEle.sendKeys(keywordNew);
		
		screen.takeScreenshot(driver);
		
		// To click on Data link
		dataEle.click();

		// To enter the new name for model
		modelEle.clear();
		modelEle.sendKeys(modelNewname);

		hsnEle.clear();
		hsnEle.sendKeys(hsnNew);
		
		screen.takeScreenshot(driver);
		// To click on Save button
		saveEle.click();
				
		return driver.getPageSource();
		
	}
	
	
}
