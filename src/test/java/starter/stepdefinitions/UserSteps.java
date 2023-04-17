package starter.stepdefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.*;


public class UserSteps {


    private RequestSpecification request;
//    private Response response;

    private String baseURI = "https://fakestoreapi.com";
    private Response response;

    private JsonPath jsonPath;

    private String productId;
    private String newProductName;

    private String cartId;
    private String newCartProducts;

    private String usersId;
    private String newUsersUsername;

    private String startDate;
    private String endDate;

    private String date;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        request = RestAssured.given();
    }

    @Given("I have valid credentials")
    public void validCredentials() {
        request.contentType(ContentType.JSON);
    }

    @When("I submit a POST request to {string} with username {string} and password {string}")
    public void submitUsernamePassword(String endpoint, String username, String password) {
        request.body("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}");
        response = request.post(endpoint);
    }

    @Then("I should get a {int} response code")
    public void getResponseCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the response should contain an access token")
    public void responseAccessToken() {
        response.then().body("token", not(emptyOrNullString()));
    }

    @Given("I set GET products endpoint")
    public void getProductEndpoint() {
        response = given().baseUri(baseURI).when().get("/products");
    }

    @When("I send GET HTTP request")
    public void getHttpRequest() {
        response = response.then().extract().response();
    }

    @Then("the response status code should be {int}")
    public void responseCode(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the response should contain products")
    public void responseProducts() {
        response.then().body("", not(empty()));
    }

}
