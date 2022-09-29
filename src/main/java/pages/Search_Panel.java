package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import util.utils;

/**
 * Created by rajeshrathod.
 */
public class Search_Panel extends BaseTest {
    private utils utils = new utils();

    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElement input_Search;

    @FindBy(xpath = "//input[@id='gh-btn' and @value='Search']")
    WebElement button_Search;

    private Search_Panel objSearch;
    private Sort_Panel objSort;
    private Product_List obj_Product_List;

    public Search_Panel(){
        PageFactory.initElements(driver,this);
        obj_Product_List = new Product_List();
    }

    public void Search_Item(String strItem){
        utils.verify_Element_displayed(input_Search);
        utils.typeText(input_Search, strItem);
        utils.verify_Element_displayed(button_Search);
        utils.Click(button_Search);
    }

}
