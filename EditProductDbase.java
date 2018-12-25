package com.ibm.groceries;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.groceriespages.EditProductsPage;
import com.ibm.groceriespages.GroceriesUserPage;
import com.ibm.groceriespages.PageDashboard;
import com.ibm.groceriespages.PageLogin;
import com.ibm.groceriespages.PageProducts;
import com.ibm.initialization.WebDriverLaunch;
import com.ibm.utilities.DatabaseConnection;
import com.ibm.utilities.GetScreenshot;

import java.sql.Connection;

public class EditProductDbase extends WebDriverLaunch {

	@Test(priority = 1, testName = "EditProductDbase", groups = "low")

	public void editProductDbase() throws IOException, InterruptedException, SQLException {
		String url = data.get("url");
		String userName = data.get("username");
		String password = data.get("password");
		String expMessage = data.get("expectedEditProductMessage");
		String product = data.get("productName");
		String newtitleProduct = data.get("titleNameNew");
		String newtagdescProduct = data.get("tagDescriptionNew");
		String newkeywordProduct = data.get("keywordNew");
		String newmodelProduct = data.get("modelNameNew");
		String newhsnProduct = data.get("hsnNew");
		String productNotUpdatedMsg = data.get("productNotUpdated");
		String proudctFoundMessage = data.get("userPageMsg");
		String productstablename = data.get("tableProducts");

		String modifiedMsg = data.get("modifiedProductConsole");
		// Launching the web site for atozgroceries
		driver.get(url);
		GetScreenshot screen = new GetScreenshot();

		PageLogin login = new PageLogin(driver, wait);
		// To enter email address and password and clickon login button
		login.enterEmailAddress(userName);
		login.enterPassword(password);
		login.clickOnLogin();
		screen.takeScreenshot(driver);
		Assert.assertTrue(driver.findElement(By.partialLinkText("Logout")).isDisplayed());

		PageDashboard dashboard = new PageDashboard(driver, wait);
		// To click on Catalog
		dashboard.clickOnCatalog();
		// To click on Products
		dashboard.clickOnProducts();

		screen.takeScreenshot(driver);
		PageProducts selProduct = new PageProducts(driver, wait);
		// Calling method to select the product to edit
		selProduct.selectProduct();
		screen.takeScreenshot(driver);
		// TO wait for the text box product name to be displayed.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pro_name")));

		EditProductsPage editPage = new EditProductsPage(driver, wait);

		// Calling method to edit the product details
		String pageSource = editPage.editProductDbase(newtitleProduct, newtagdescProduct, newkeywordProduct,
				newmodelProduct, newhsnProduct);
		screen.takeScreenshot(driver);

		// Verifying if modified product is present
		Assert.assertTrue(pageSource.contains(product));

		// Getting the details of modified product from database
		if (pageSource.contains(expMessage)) {
			System.out.println(expMessage);
			DatabaseConnection conn = new DatabaseConnection();
			Statement st = conn.connectDatabase();
			// ResultSet rs=st.executeQuery("select *from as_products where name=\"Badam-p01\"");
			ResultSet rs = st.executeQuery("select *from " + productstablename + " where name=" + "'" + product + "'");
			System.out.println(modifiedMsg);
			while (rs.next()) {
				System.out.println(rs.getString("meta_title"));
				Assert.assertEquals(rs.getString("meta_title"), newtitleProduct);
				System.out.println(rs.getString("meta_description"));
				Assert.assertEquals(rs.getString("meta_description"), newtagdescProduct);
				System.out.println(rs.getString("meta_keyword"));
				Assert.assertEquals(rs.getString("meta_keyword"), newkeywordProduct);
				System.out.println(rs.getString("model"));
				Assert.assertEquals(rs.getString("model"), newmodelProduct);
				System.out.println(rs.getString("hsn"));
				Assert.assertEquals(rs.getString("hsn"), newhsnProduct);

			}
			rs.close();
		}

	}

}
