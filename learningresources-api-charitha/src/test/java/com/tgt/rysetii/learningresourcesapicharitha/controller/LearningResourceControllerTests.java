package com.tgt.rysetii.learningresourcesapicharitha.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgt.rysetii.learningresourcesapicharitha.controllers.LearningResourceController;
import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResource;
import com.tgt.rysetii.learningresourcesapicharitha.entity.LearningResourceStatus;
import com.tgt.rysetii.learningresourcesapicharitha.service.LearningResourceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LearningResourceController.class)
public class LearningResourceControllerTests {

    @MockBean()
    LearningResourceService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTheAvailableLearningResources() throws Exception {
        List<LearningResource> learningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(10, "Test Resource 1", 399.0, 599.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource2 = new LearningResource(11, "Test Resource 2", 499.0, 799.0, LearningResourceStatus.PLANNING, LocalDate.now(), LocalDate.now().plusMonths(7), LocalDate.now().plusYears(2));
        learningResources.add(learningResource1);
        learningResources.add(learningResource2);

        String expectedResponse = objectMapper.writeValueAsString(learningResources);

        when(service.getLearningResources()).thenReturn(learningResources);

        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/learningresources/v1/details"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void saveTheGivenLearningResources() throws Exception {
        List<LearningResource> inputLearningResources = new ArrayList<>();
        LearningResource learningResource1 = new LearningResource(10, "Test Resource 1", 399.0, 599.0, LearningResourceStatus.LIVE, LocalDate.now(), LocalDate.now().plusMonths(6), LocalDate.now().plusYears(3));
        LearningResource learningResource2 = new LearningResource(11, "Test Resource 2", 499.0, 799.0, LearningResourceStatus.PLANNING, LocalDate.now(), LocalDate.now().plusMonths(7), LocalDate.now().plusYears(2));
        inputLearningResources.add(learningResource1);
        inputLearningResources.add(learningResource2);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/learningresources/v1/details/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputLearningResources)))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteTheRequestedLearningResource() throws Exception {
        int inputLearningResourceId = 12;

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/learningresources/v1/details/" + inputLearningResourceId))
                .andExpect(status().isOk());
    }
}


