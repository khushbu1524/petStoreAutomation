package api.test;

import api.endpoints.UserEndPoints;
import api.listeners.TestListener;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.factory.MyTest;

import static org.hamcrest.number.OrderingComparison.lessThan;

public class UserTests extends TestListener {
    Faker faker;
    User userpayload;
    Logger log;

    @BeforeClass
    public void setup(){
        faker=new Faker();
        userpayload=new User();
        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setUsername(faker.name().username());
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        userpayload.setPassword(faker.internet().password());
        userpayload.setPhone(faker.phoneNumber().cellPhone());
        log = LogManager.getLogger(MyTest.class);

    }
    @Test(priority = 0)
    public void createUser() throws InterruptedException {
        log.info("createuser started");
        Response response= UserEndPoints.createUser(userpayload);
        response.then().log().everything();
        Assert.assertEquals(response.statusCode(),200);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createUser.json"))
                .time(lessThan(2000L));      ;
        Thread.sleep(5000);
        log.info("user created");

    }
    @Test(priority = 1)
    public void getUserByName(){
        Response response= UserEndPoints.getUser(this.userpayload.getUsername());
        response.then().log().everything();
        Assert.assertEquals(response.statusCode(),200);
        JsonPath jsonPath=new JsonPath(response.asString());
        String  username=jsonPath.get("username");
        System.out.println(username);
        String name=response.jsonPath().getString("username");
        System.out.println(name);
    }
    @Test(priority = 2)
    public void updateUser(){
        userpayload.setFirstName(faker.name().firstName());
        userpayload.setLastName(faker.name().lastName());
        userpayload.setEmail(faker.internet().safeEmailAddress());
        Response response= UserEndPoints.updateUser(userpayload,this.userpayload.getUsername());
        response.then().log().everything();
        Assert.assertEquals(response.statusCode(),200);
    }

@Test(priority = 3)
public void deleteUserByName(){
    Response response= UserEndPoints.deleteUser(this.userpayload.getUsername());
    response.then().log().everything();
    Assert.assertEquals(response.statusCode(),200);
}
}
