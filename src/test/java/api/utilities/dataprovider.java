package api.utilities;

import org.testng.annotations.DataProvider;

public class dataprovider {

    @DataProvider(name = "getPostData")
    public static Object[][]getPostData(){
        Object[][] data={
                {100,"username0","first0","last0","email0","pass0","1000",0},
                {101,"username1","first1","last1","email1","pass1","1001",0},
                {102,"username2","first2","last2","email2","pass2","1002",0}
        };
        return data;
    }

    @DataProvider(name = "getData")
    public static Object[][]getData(){
        Object[][] data={
                {"username0"},
                {"username1"},
                {"username2"}
        };
        return data;
    }
}
