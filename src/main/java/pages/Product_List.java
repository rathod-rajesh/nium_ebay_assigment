package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import util.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rajeshrathod.
 */
public class Product_List extends BaseTest {
    private utils utils = new utils();
    String tempTitle;
    String temp;
    Double  tempPrice;
    ArrayList<Product_Item> sortedList;


    By Search_Result_Product = By.xpath("//div[@id='srp-river-results']//li[contains(@class,'s-item__pl-on-bottom')]");


    public Product_List(){
        PageFactory.initElements(driver, this);
    }

    public ArrayList<Product_Item> Sort_Product_List(ArrayList<Product_Item> list_temp_Product,String strCriterion, String strOrder){
       switch (strCriterion.toLowerCase()){
           case "title":
               if (strOrder.equalsIgnoreCase("ascending")){
               }else if(strOrder.equalsIgnoreCase("descending")){
               }
               break;
           case "price":
               if (strOrder.equalsIgnoreCase("ascending")){
                   Collections.sort(list_temp_Product, Product_Item.proPrice_Ascending);
               }else if(strOrder.equalsIgnoreCase("descending")){
                   Collections.sort(list_temp_Product, Product_Item.proPrice_Descending);
               }
               break;
           default:
       }
        return  list_temp_Product;
    }


    public ArrayList<Product_Item> Fetch_Product_List(){
        utils.waitForPageLoad(driver);
        utils.wait_explicit_till_element_Displayed(Search_Result_Product);
        ArrayList<Product_Item> list_Product = new ArrayList<Product_Item>();
        List<WebElement> list_Search_Result = driver.findElements(Search_Result_Product);
        for (int i=1; i < list_Search_Result.size(); i++){
            tempTitle = driver.findElement(By.xpath("//div[@id='srp-river-results']//li[contains(@class,'s-item__pl-on-bottom')]["+i+"]//div[contains(@class,'s-item__title')]/span[@role='heading']")).getText();
            temp = driver.findElement(By.xpath("//div[@id='srp-river-results']//li[contains(@class,'s-item__pl-on-bottom')]["+i+"]//div[contains(@class,'s-item__detail--primary')]/span[@class='s-item__price']")).getText();            System.out.println(list_Search_Result.get(i).findElement(By.xpath("//div[contains(@class, 's-item__detail--primary')]/span[@class='s-item__price']")).getText());
            temp = temp.replace("$", "");
            temp = temp.replace(",", "");
            if(temp.contains("to")){
                int a = temp.indexOf("to");
                temp=temp.substring(0,a-2);
            }
            tempPrice = Double.valueOf(temp);
            list_Product.add(new Product_Item(driver,tempTitle, tempPrice));
        }
        return  list_Product;
    }

    public void Verify_Product_List_Title(String strCriterion){
        utils.waitForPageLoad(driver);
        ArrayList<Product_Item> list_Product = Fetch_Product_List();
        String tempTitle;
        String tempLog;
        boolean result = true;

        for(Product_Item temp : list_Product){
            tempTitle = temp.getProductTitle();
            tempLog = "Product title does not contain expected search criterion. Actual product title is "+tempTitle+" and expected criterion was "+strCriterion;
            Assert.assertEquals(true,tempTitle.toLowerCase().contains(strCriterion.toLowerCase()), tempLog);
        }
    }

    public void Verify_Sorting_Order(String strSortCriterion){
        utils.waitForPageLoad(driver);
        ArrayList<Product_Item> tempDisplayedList = Fetch_Product_List();
        ArrayList<Product_Item> expectedList;
        switch (strSortCriterion.toLowerCase()){
            case "highest price":
                expectedList = Sort_Product_List(tempDisplayedList,"price", "descending");
                break;
            case "lowest price":
                expectedList = Sort_Product_List(tempDisplayedList,"price", "ascending");
                break;
            default:
                expectedList = Sort_Product_List(tempDisplayedList,"price", "ascending");
        }
        sortedList = expectedList;
        compare_ArrayList(tempDisplayedList, expectedList);
    }

    public void compare_ArrayList(ArrayList<Product_Item> arrActual, ArrayList<Product_Item> arrExpected){
        boolean result = true;
        if (arrActual.isEmpty()|| arrExpected.isEmpty() ){
           Assert.assertEquals(true, false, "Both or any one arraylist is empty");
        }
        for (int i=0; i<arrActual.size(); i++ ){
            if (!arrActual.get(i).equals(arrExpected.get(i))){
                result = false;
                break;
            }
        }
      Assert.assertEquals(true, result, "Both array list are matching.");
    }

    public void printItemsResultPage(int size){
        utils.printProductDetails(sortedList,size);
    }
}
