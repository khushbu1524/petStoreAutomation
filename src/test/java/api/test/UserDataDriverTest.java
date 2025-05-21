package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.dataprovider;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class UserDataDriverTest {

    @Test(priority = 1, dataProvider = "getPostData",dataProviderClass = dataprovider.class)
    public void createUser(int id,String username,String firstname,String lastname,String email,String password,String phone,int userStatus){

        System.out.println("create user started");
        User payload=new User();
        payload.setId(id);
        payload.setUsername(username);
        payload.setFirstName(firstname);
        payload.setLastName(lastname);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(phone);
        payload.setUserStatus(userStatus);
        Response response= UserEndPoints.createUser(payload);
        response.then().log().everything();
        Assert.assertEquals(response.statusCode(),200);
    }
    @Test(priority = 2, dataProvider = "getData",dataProviderClass = dataprovider.class)
    public void getuser(String username){
        Response response=UserEndPoints.getUser(username);
        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3, dataProvider = "getData",dataProviderClass = dataprovider.class)
    public void deleteUser(String uusername){
        Response response=UserEndPoints.deleteUser(uusername);
        Assert.assertEquals(response.statusCode(),200);
    }


}
