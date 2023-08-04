package com.retail.rewards.IntegrationTests;


import com.retail.rewards.RewardsApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RewardsApplication.class)
@AutoConfigureMockMvc
public class RewardsControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenCustomerReturnRewardsAndThenStatus200()
            throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/customers/1/rewards")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
