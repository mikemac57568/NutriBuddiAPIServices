package com.Temple.NutriBuddi.UserManagement.bdd.steps;

import com.Temple.NutriBuddi.UserManagement.bdd.SpringFeatureTest;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.experimental.results.ResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Base64;


public class UserSteps extends SpringFeatureTest{
    @Autowired
    private MockMvc mockMvc;
    private String userAuthorization;
    private MockHttpServletRequestBuilder request;
    private ResultActions response;

    //TODO: Add and check tokenization
    @Given("^Mike is authorized$")
    public void mikeIsAuthorized() throws Throwable {
        userAuthorization = "Basic " + Base64.getEncoder().encodeToString(("user" + ":" + "default").getBytes());
        request = MockMvcRequestBuilders.get("user/all").header("Authorization", userAuthorization);
//        assertTrue(request != null);
    }

    @When("^he makes the call to /user/all$")
    public void heMakesTheCallToUserAll() throws Throwable {
        response = mockMvc.perform(request);
        response.andExpect(status().isAccepted());
//        assertTrue(result.andReturn() == ResultMatchers.isSuccessful());
    }



    @Then("^a response should be retrieved with status (\\d+) of type json$")
    public void aResponseShouldBeRetrievedWithStatusOfTypeJson(int arg0) throws Throwable {
        response.andExpect(status().is2xxSuccessful());
//        assertTrue(result.andReturn() == ResultMatchers.isSuccessful());

    }

    @And("^of type json$")
    public void ofTypeJson() throws Throwable {
        response.andExpect(content().contentType(MediaType.APPLICATION_JSON));
//        assertTrue(result.andReturn() == ResultMatchers.isSuccessful());
    }

    @And("^is populated with user names$")
    public void isPopulatedWithUserNames() throws Throwable {
//        ResultActions result = response.andExpect(jsonPath("",));

        throw new PendingException();
    }


}
