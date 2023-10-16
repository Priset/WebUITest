package ejerciciosParcial2.ejercicio4.factoryRequest;

import ejerciciosParcial2.ejercicio4.configuration.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestPUT implements IRequest {
    @Override
    public Response send(RequestInfo requestInfo) {
        Response response=given()
                .auth()
                .preemptive()
                .basic(Configuration.user, Configuration.password)
                .body(requestInfo.getBody())
                .log()
                .all().
                when()
                .put(requestInfo.getUrl());
        response.then().log().all();
        return response;
    }

    @Override
    public Response sendWithToken(RequestInfo requestInfo) {
        Response response = given()
                .header("Token", Configuration.token) // Add the token
                .body(requestInfo.getBody())
                .log()
                .all()
                .when()
                .put(requestInfo.getUrl());

        response.then().log().all();
        return response;    }
}
