package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {

    @Step("Создать пользователя")
    public static Response createUser(String email, String password, String name) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(data.TestData.API_BASE_URL)
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                        email, password, name))
                .when()
                .post(data.TestData.REGISTER_ENDPOINT);
    }

    @Step("Удалить пользователя")
    public static Response deleteUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .header("Content-type", "application/json")
                .baseUri(data.TestData.API_BASE_URL)
                .when()
                .delete(data.TestData.DELETE_USER_ENDPOINT);
    }

    @Step("Авторизовать пользователя")
    public static Response loginUser(String email, String password) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(data.TestData.API_BASE_URL)
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password))
                .when()
                .post(data.TestData.LOGIN_ENDPOINT);
    }

    @Step("Получить accessToken после авторизации")
    public static String getAccessToken(Response loginResponse) {
        return loginResponse.then().extract().path("accessToken");
    }
}