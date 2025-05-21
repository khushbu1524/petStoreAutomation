package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPointsFromConfigProperties {
    static  ResourceBundle get_url() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config.properties");
        return resourceBundle;
    }

    public static Response createUser(User payload){
        String post_url=get_url().getString("post_url");
        Response response= (Response) given().log().all().contentType(ContentType.JSON)
                .body(payload).accept(ContentType.JSON)
                .when().post(post_url);
        return response;
    }

    public static Response getUser(String userName){
        String get_url=get_url().getString("get_url");
        Response response= (Response) given().log().all().pathParams("userName",userName)
                .when().get(get_url);
        return response;
    }
    public static Response updateUser(User payload,String userName){
        String update_url=get_url().getString("update_url");
        Response response= (Response) given().log().all().contentType(ContentType.JSON).pathParams("userName",userName)
                .body(payload).accept(ContentType.JSON)
                .when().put(update_url);
        return response;
    }
    public static Response deleteUser(String userName){
        String delete_url=get_url().getString("delete_url");
        Response response= (Response) given().log().all().contentType(ContentType.JSON).pathParams("userName",userName)
                .when().delete(delete_url);
        return response;
    }

}
