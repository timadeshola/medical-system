package com.oze.medicalsystem.resource;

import com.github.javafaker.Faker;
import com.oze.medicalsystem.core.utils.AppUtils;
import com.oze.medicalsystem.model.request.StaffRequest;
import com.oze.medicalsystem.service.StaffService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.oze.medicalsystem.core.constants.AppConstant.ResponseCode.CREATED;
import static com.oze.medicalsystem.core.constants.AppConstant.ResponseCode.OK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 5:55 PM
 */
@WebMvcTest(StaffResource.class)
class StaffResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StaffService staffService;

    private StaffRequest request;
    private Faker faker;

    @BeforeEach
    public void setup() {
        request = StaffRequest.builder().build();
        faker = new Faker();
    }

    @SneakyThrows
    @Test
    @DisplayName("Create Staff Endpoint - Test")
    void testCreateStaffEndpoint() {
        request = StaffRequest.builder()
                .name(faker.name().name())
                .registrationDate("2022-04-18")
                .build();

        String json = AppUtils.toJson(request);

        mockMvc.perform(post("/staff")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(CREATED))
                .andExpect(jsonPath("$.status").value(201));
    }

    @SneakyThrows
    @Test
    @DisplayName("Update Staff Endpoint - Test")
    void testUpdateStaffEndpoint() {
        request = StaffRequest.builder()
                .name(faker.name().name())
                .registrationDate("2022-04-18")
                .build();

        String json = AppUtils.toJson(request);

        mockMvc.perform(put("/staff/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(OK))
                .andExpect(jsonPath("$.status").value(200));
    }

    @SneakyThrows
    @Test
    @DisplayName("Fetch All Staff Endpoint - Test")
    void testFetchAllStaffEndpoint() {

        mockMvc.perform(put("/staff/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(OK))
                .andExpect(jsonPath("$.status").value(200));
    }

}
