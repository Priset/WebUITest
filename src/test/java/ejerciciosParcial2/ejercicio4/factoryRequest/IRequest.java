package ejerciciosParcial2.ejercicio4.factoryRequest;
import io.restassured.response.Response;

public interface IRequest {
    Response send (RequestInfo requestInfo);
    Response sendWithToken(RequestInfo requestInfo);
}
