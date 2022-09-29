package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import util.utils;

import java.util.ArrayList;

/**
 * Created by rajeshrathod.
 */
public class Sort_Panel extends BaseTest {
    private Search_Panel objSearch;
    private Sort_Panel objSort;
    private Product_List obj_Product_List;
    private utils utils = new utils();
    ArrayList<Product_Item> list_Product;


    By dashboard_Sort = By.xpath("//button[@aria-label='Sort selector. Best Match selected.']");
    By list_Sort_Option = By.xpath("//a[@class='fake-menu-button__item']//span[contains(.,'highest')]");


    public Sort_Panel(){
        PageFactory.initElements(driver, this);
         obj_Product_List = new Product_List();

    }


    public void Sort_Product_List(String sortCriterion){
        utils.waitForPageLoad(driver);
        utils.verify_Element_displayed(dashboard_Sort);
        utils.Click(dashboard_Sort);
        utils.Click(list_Sort_Option);
        list_Product = obj_Product_List.Fetch_Product_List();
    }
    public void printSortedproduct(int size){
        utils.printProductDetails(list_Product,size);
    }

}
