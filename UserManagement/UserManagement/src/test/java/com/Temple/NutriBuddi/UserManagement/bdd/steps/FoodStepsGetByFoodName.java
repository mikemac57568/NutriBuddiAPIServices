package com.Temple.NutriBuddi.UserManagement.bdd.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Base64;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

public class FoodStepsGetByFoodName {
    @Autowired
    private MockMvc mockMvc;
    private ResultActions response;

    @When("^Demogorgon is hungry and makes a request$")
    public void demogorgonIsHungryAndMakesARequest() throws Throwable {
        String userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        response = mockMvc.perform(get("http://localhost:8080/food/getEatsByFoodName")
                .header("Authorization", userAuthorization)
                .param("foodName", "eggos")
                .accept(MediaType.APPLICATION_JSON));
    }

    @Then("^it gets back a success and goes hunting$")
    public void itGetsBackASuccessAndGoesHunting() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
