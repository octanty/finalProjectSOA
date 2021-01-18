package org.acme.getting.started;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.HttpHeaders.CONTENT_TYPE;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.HttpHeaders.ACCEPT;
import static javax.ws.rs.core.Response.Status.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;


@QuarkusTest
public class AccountResourceTest {

    private static final String DEFAULT_NAME = "User1";
    private static final String DEFAULT_UPDATE_NAME = "Paul";
    private static final String DEFAULT_PASSWORD = "pass1234#";
    private static final double SAVING_AMOUNT = 90.00;
    private static final double SAVING_INTEREST = 50.00;
    private static final boolean SAVING_CAPITALIZATION = true;
    private static final boolean SAVING_DEPOSIT = true;
    private static final String ACCOUNT_NOTE = "Update Saving";

    private static String userId;
    private static final String SAVING_ID = "20";
    private static final int NB_HEROES = 951;

    @Test
    void shouldAddUser() {
        User user = new User();
        user.username = DEFAULT_NAME;
        user.password = DEFAULT_PASSWORD;
        String location =
                given()
                .body(user)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .post("/api/finance")
                .then()
                .statusCode(200)
                .extract().header("Location");

        given()
                .pathParam("name", DEFAULT_NAME)
                .when()
                .get("/api/finance/account/{name}")
                .then()
                .statusCode(200)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body("name", Is.is(DEFAULT_NAME));
    }

    @Test
    void CannotAddUser() {
        User user = new User();
        user.username = DEFAULT_UPDATE_NAME;
        user.password = DEFAULT_PASSWORD;

                given()
                        .body(user)
                        .header(CONTENT_TYPE, APPLICATION_JSON)
                        .header(ACCEPT, APPLICATION_JSON)
                        .when()
                        .post("/api/finance")
                        .then()
                        .statusCode(500);
    }


 /*   @Test
    void cannotUpdateAccount() {
        Account account = new Account();
        // account.saving.id = Long.valueOf(SAVING_ID);
        account.saving.capitalization = SAVING_CAPITALIZATION;
        account.saving.deposit = SAVING_DEPOSIT;
        account.saving.interest = BigDecimal.valueOf(SAVING_INTEREST);
        account.note = ACCOUNT_NOTE;

        given()
                .body(account)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .header(ACCEPT, APPLICATION_JSON)
                .when()
                .put("/api/finance/saveChanges/Paul")
                .then()
                .statusCode(500);

    } */

}
