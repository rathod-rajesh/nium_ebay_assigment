package util;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.Product_Item;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class utils extends BaseTest {

    public void Click(By by){
        driver.findElement(by).click();
    }

    public void Click(WebElement objTest ){
        objTest.click();
    }

    public static void waitForPageLoad(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
    public String getPageTitle(){
        return  driver.getTitle();
    }

    public void verify_Element_displayed(By by){

        wait_explicit_till_element_Displayed(by);
        boolean result =driver.findElement(by).isDisplayed();

        Assert.assertEquals(result, true, "Element not displayed");

    }

    public void verify_Element_displayed(WebElement objTest){
        waitForPageLoad(driver);
        wait_explicit_till_element_Displayed(objTest);
        boolean result =objTest.isDisplayed();
        Assert.assertEquals(result, true, "Element not displayed");
    }

    public void wait_explicit_till_element_Displayed(By by){
        WebDriverWait waitnew=new WebDriverWait(driver,30);
        WebElement element = waitnew.until(ExpectedConditions.visibilityOfElementLocated(by));
        //  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
    }

    public void wait_explicit_till_element_Displayed(WebElement objTest){
        WebDriverWait waitnew=new WebDriverWait(driver,20);
        WebElement element = waitnew.until(ExpectedConditions.visibilityOf(objTest));
        //  .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='WebDriver']")));
    }

    public void typeText(WebElement objInput, String text) {
        objInput.clear();
        objInput.sendKeys(text);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public List get_Element_List(By by) {
        List<WebElement> lisElement = driver.findElements(by);
        return lisElement;
    }

    public void printProductDetails(ArrayList<Product_Item> list_Product,int noOfItems){
        System.out.println("---------------/ RESULTS /------------");
        for (int i = 0; i < noOfItems; i++) {
            System.out.println("PRODUCT :- " + (i+1) );
            System.out.println(list_Product.get(i).getProductTitle());
            System.out.println(list_Product.get(i).getProductPrice());
        }
        System.out.println("---------------/ END /------------");
    }

}
