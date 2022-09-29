package pages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by rajeshrathod.
 */
public class Ebay_Home_Page extends BaseTest {

    @FindBy(xpath = "//a[@id='gh-la' and text()='eBay']/img[@id='gh-logo']")
    WebElement logo_Ebay;

    private Search_Panel objSearch;
    private Sort_Panel objSort;
    private Product_List obj_Product_List;
    private util.utils utils;

    public Ebay_Home_Page(){
        PageFactory.initElements(driver, this);
        objSearch = new Search_Panel();
        obj_Product_List = new Product_List();
        objSort = new Sort_Panel();
    }

    public void Verify_Ebay_Home_Page(){
        utils.verify_Element_displayed(logo_Ebay);
        Assert.assertEquals(true, driver.getTitle().toLowerCase().contains("ebay"), "Ebay Home Page Title does not contains word 'EBAY'");
    }

    public void Search_Item(String strItem){
        objSearch.Search_Item(strItem);
    }

    public void Verify_Search_Result(String strItem){
        obj_Product_List.Verify_Product_List_Title(strItem);
    }

    public void sortResultPage(int size){
        System.out.println("THIS WILL SORT & PRINT TOP "+ size +" RESULT ON PAGE AS PER GIVEN CONDITION");
        obj_Product_List.printItemsResultPage(size);
    }

    public void Sort_Search_Result(String strSortCriterion){
        objSort.Sort_Product_List(strSortCriterion);
    }

    public void Verify_Search_Result_Sorting_Order(String strSortCriterion){
        obj_Product_List.Verify_Sorting_Order(strSortCriterion);

    }
    public void Print_Expected_Details(int size){
        System.out.println("PRINTS TOP "+size+" RESULT AFTER SORTING APPIED ON PAGE");
        objSort.printSortedproduct(size);
    }
}
