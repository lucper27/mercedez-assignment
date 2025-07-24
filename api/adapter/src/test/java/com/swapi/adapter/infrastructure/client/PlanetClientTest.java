package com.swapi.adapter.infrastructure.client;

import com.swapi.adapter.application.exception.PlanetClientException;
import com.swapi.adapter.infrastructure.client.swapi.SwapiPlanetClientImpl;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetPaginatedResponseDTO;
import com.swapi.adapter.infrastructure.client.swapi.dto.SwapiPlanetSearchResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {SwapiPlanetClientImpl.class})
@TestPropertySource(properties = "swapi.base-url=http://invalid.url")
public class PlanetClientTest {

    @MockBean
    private RestTemplate restTemplate;

    @SpyBean
    private SwapiPlanetClientImpl swapiPlanetClient;

    @Test
    void shouldThrowExceptionWhenGetAllPaginatedFailsToConnect() {
        when(restTemplate.getForObject(anyString(), eq(SwapiPlanetPaginatedResponseDTO.class)))
                .thenThrow(new RuntimeException("connection error"));

        assertThrows(PlanetClientException.class, () -> swapiPlanetClient.getAllPaginated(1, 5));
    }

    @Test
    void shouldThrowExceptionWhenGetAllPaginatedReturnsNull() {
        when(restTemplate.getForObject(anyString(), eq(SwapiPlanetPaginatedResponseDTO.class)))
                .thenReturn(null);

        PlanetClientException ex = assertThrows(PlanetClientException.class, () -> swapiPlanetClient.getAllPaginated(1, 5));
        assertTrue(ex.getMessage().contains("null response"));
    }

    @Test
    void shouldThrowExceptionWhenSearchByNameFailsToConnect() {
        when(restTemplate.getForObject(anyString(), eq(SwapiPlanetSearchResponseDTO.class)))
                .thenThrow(new RuntimeException("connection error"));

        assertThrows(PlanetClientException.class, () -> swapiPlanetClient.searchByName("tatooine"));
    }

    @Test
    void shouldReturnEmptyListWhenSearchByNameReturnsNull() {
        when(restTemplate.getForObject(anyString(), eq(SwapiPlanetSearchResponseDTO.class)))
                .thenReturn(null);

        assertDoesNotThrow(() -> {
            var result = swapiPlanetClient.searchByName("tatooine");
            assertNotNull(result);
            assertTrue(result.isEmpty());
        });
    }

}
