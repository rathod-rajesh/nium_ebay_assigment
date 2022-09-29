package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.utils;

import java.util.Comparator;

/**
 * Created by rajeshrathod.
 */
public class Product_Item extends BaseTest {
    private Search_Panel objSearch;
    private Sort_Panel objSort;
    private Product_List obj_Product_List;
    private util.utils utils;

    private WebDriver driver;
    private String strTitle;
    private Double productPrice;
  //  private Date  strPostedDate;
   // private Date  strEndDate;

    public Product_Item(WebDriver driver, String strTitle, Double productPrice){
        this.driver = driver;
        this.strTitle = strTitle;
        this.productPrice = productPrice;
        objSearch = new Search_Panel();
        obj_Product_List = new Product_List();
        objSort = new Sort_Panel();
        utils = new utils();

    }

    public String getProductTitle(){
        return strTitle;
    }

    public void setProductTitle(String tempTitle){
        strTitle = tempTitle;
    }

    public Double getProductPrice(){
        return productPrice;
    }

    public void setProductPrice(Double productPrice){
        this.productPrice = productPrice;
    }

    public static Comparator<Product_Item> proPrice_Ascending= new Comparator<Product_Item>() {
        @Override
        public int compare(Product_Item o1, Product_Item o2) {

            Double price_one = o1.getProductPrice();
            Double price_two = o2.getProductPrice();

            return price_one.compareTo(price_two);
        }
    };

    public static Comparator<Product_Item> proPrice_Descending= new Comparator<Product_Item>() {
        @Override
        public int compare(Product_Item o1, Product_Item o2) {

            Double price_one = o1.getProductPrice();
            Double price_two = o2.getProductPrice();

            return price_two.compareTo(price_one);
        }
    };

}
