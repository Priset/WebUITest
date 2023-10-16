package ejerciciosParcial2.ejercicio4.factoryRequest;

import ejerciciosParcial2.ejercicio4.configuration.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest {
    @Override
    public Response send(RequestInfo requestInfo) {
        Response response = given()
                .auth()
                .preemptive()
                .basic(Configuration.user, Configuration.password)
                .log()
                .all().
                when()
                .delete(requestInfo.getUrl());
        response.then().log().all();
        return response;
    }

    @Override
    public Response sendWithToken(RequestInfo requestInfo) {
        Response response = given()
                .header("Token", Configuration.token) // Add the token
                .log()
                .all()
                .when()
                .delete(requestInfo.getUrl());

        response.then().log().all();
        return response;
    }
}
