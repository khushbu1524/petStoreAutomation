package api.endpoints;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response= (Response) given().log().all().contentType(ContentType.JSON)
                .body(payload).accept(ContentType.JSON)
                .when().post(Routes.post_url);
        return response;
    }

    public static Response getUser(String userName){
        Response response= (Response) given().log().all().pathParams("userName",userName)
                .when().get(Routes.get_url);
        return response;
    }
    public static Response updateUser(User payload,String userName){
        Response response= (Response) given().log().all().contentType(ContentType.JSON).pathParams("userName",userName)
                .body(payload).accept(ContentType.JSON)
                .when().put(Routes.update_url);
        return response;
    }
    public static Response deleteUser(String userName){
        Response response= (Response) given().log().all().contentType(ContentType.JSON).pathParams("userName",userName)
                .when().delete(Routes.delete_url);
        return response;
    }

}
