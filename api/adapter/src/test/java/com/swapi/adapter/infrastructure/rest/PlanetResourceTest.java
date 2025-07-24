package com.swapi.adapter.infrastructure.rest;

import com.swapi.adapter.infrastructure.client.swapi.SwapiPlanetClient;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import com.swapi.adapter.util.PlanetMockData;
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
public class PlanetResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SwapiPlanetClient swapiPlanetClient;

    private static final String BASE_URL = "/api/planets";

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
    void shouldReturnPlanetsWithoutNameOrSort() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), any())).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void shouldReturnPlanetsSortedByNameAsc() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), eq(""))).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "name")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"));
    }

    @Test
    void shouldReturnPlanetsSortedByNameDesc() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), eq(""))).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "name")
                        .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"));
    }

    @Test
    void shouldReturnPlanetsSortedByCreatedAsc() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), eq(""))).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "created")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].created").value("2025-07-22T16:28:46.488Z"));
    }

    @Test
    void shouldReturnPlanetsSortedByCreatedDesc() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), eq(""))).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "created")
                        .param("direction", "desc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].created").value("2025-07-22T16:28:46.488Z"));
    }

    @Test
    void shouldReturnPlanetsWithNameSearch() throws Exception {
        when(swapiPlanetClient.query(anyInt(), anyInt(), eq("tatooine"))).thenReturn(PlanetMockData.swapiPlanetResponse());

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("name", "tatooine"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"));
    }

    @Test
    void shouldReturnPlanetsWithNameSearchAndSort() throws Exception {
        SwapiPlanetPaginatedResponseDTO mockedResponse = PlanetMockData.swapiPlanetResponse();

        when(swapiPlanetClient.query(eq(1), eq(1), eq(""))).thenReturn(mockedResponse);
        when(swapiPlanetClient.query(eq(1), eq(1), eq("tatooine"))).thenReturn(mockedResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("page", "1")
                        .param("size", "5")
                        .param("name", "tatooine")
                        .param("sort", "name")
                        .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Tatooine"))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.totalElements").value(1));
    }

}
