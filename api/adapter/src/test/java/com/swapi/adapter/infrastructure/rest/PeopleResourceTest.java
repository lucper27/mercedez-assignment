package com.swapi.adapter.infrastructure.rest;

import com.swapi.adapter.infrastructure.client.swapi.SwapiPeopleClient;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.util.PeopleMockData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PeopleResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwapiPeopleClient swapiPeopleClient;

    private static final String BASE_URL = "/api/people";

    @Test
    void shouldReturnBadRequestForInvalidPageParam() throws Exception {
        mockMvc.perform(get(BASE_URL)
                        .param("page", "abc")
                        .param("size", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path").value(BASE_URL))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldReturnPeopleWithoutNameOrSort() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), any())).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL).param("page", "1").param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void shouldReturnPeopleSortedByNameAsc() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), any())).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "name")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"));
    }

    @Test
    void shouldReturnPeopleSortedByNameDesc() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), any())).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "name")
                        .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"));
    }

    @Test
    void shouldReturnPeopleSortedByCreatedAsc() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), any())).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "created")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].created").value("2025-07-22T16:28:46.488Z"));
    }

    @Test
    void shouldReturnPeopleSortedByCreatedDesc() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), any())).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "created")
                        .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].created").value("2025-07-22T16:28:46.488Z"));
    }

    @Test
    void shouldReturnPeopleWithNameSearch() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(anyInt(), anyInt(), eq("luke"))).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("name", "luke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"));
    }

    @Test
    void shouldReturnPeopleWithNameSearchAndSort() throws Exception {
        SwapiPeoplePaginatedResponseDTO mockedResponse = PeopleMockData.swapiPeopleResponse();

        when(swapiPeopleClient.query(eq(1), eq(1), eq(""))).thenReturn(mockedResponse);
        when(swapiPeopleClient.query(eq(1), eq(1), eq(""))).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("name", "luke")
                        .param("sort", "name")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Luke Skywalker"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }



}
