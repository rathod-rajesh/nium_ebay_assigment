import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;
import util.utils.*;

public class EbaySearch extends BaseTest {
    BaseTest base;
    Ebay_Home_Page ebay_home_page;

    public EbaySearch(){
        super();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        base = new BaseTest();
        ebay_home_page = new Ebay_Home_Page();
    }

    @Parameters({"product","sorting","resultsCount"})
    @Test(description = "Check HomePage")
    public void homepage(String product, String sorting, int resultsCount){
        ebay_home_page.Search_Item(product);
        ebay_home_page.Verify_Search_Result(product);
        ebay_home_page.Verify_Search_Result_Sorting_Order(sorting);
        ebay_home_page.sortResultPage(resultsCount);
        ebay_home_page.Sort_Search_Result(sorting);
        ebay_home_page.Print_Expected_Details(resultsCount);
    }

}
