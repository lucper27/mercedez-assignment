package com.swapi.adapter.infrastructure.client;

import com.swapi.adapter.application.exception.PeopleClientException;
import com.swapi.adapter.infrastructure.client.swapi.SwapiPeopleClientImpl;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPeoplePaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiSearchPeopleResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SwapiPeopleClientImpl.class})
@TestPropertySource(properties = "swapi.base-url=http://invalid.url")
public class PeopleClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @SpyBean
    private SwapiPeopleClientImpl swapiPeopleClient;

    @Test
    void shouldThrowExceptionWhenGetAllPaginatedFailsToConnect() {
        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenThrow(new RuntimeException("connection error"));

        assertThrows(PeopleClientException.class, () -> swapiPeopleClient.getAllPaginated(1, 5));
    }

    @Test
    void shouldThrowExceptionWhenGetAllPaginatedReturnsNull() {
        when(restTemplate.getForObject(anyString(), eq(SwapiPeoplePaginatedResponseDTO.class)))
                .thenReturn(null);

        PeopleClientException ex = assertThrows(PeopleClientException.class, () -> swapiPeopleClient.getAllPaginated(1, 5));
        assertTrue(ex.getMessage().contains("null response"));
    }

    @Test
    void shouldThrowExceptionWhenSearchByNameFailsToConnect() {
        when(restTemplate.getForObject(anyString(), eq(SwapiSearchPeopleResponseDTO.class)))
                .thenThrow(new RuntimeException("connection error"));

        assertThrows(PeopleClientException.class, () -> swapiPeopleClient.searchByName("luke"));
    }

    @Test
    void shouldReturnEmptyListWhenSearchByNameReturnsNull() {
        when(restTemplate.getForObject(anyString(), eq(SwapiSearchPeopleResponseDTO.class)))
                .thenReturn(null);

        assertDoesNotThrow(() -> {
            var result = swapiPeopleClient.searchByName("luke");
            assertNotNull(result);
            assertTrue(result.isEmpty());
        });
    }
}
