package com.gilmar.sistemainventario;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gilmar.sistemainventario.infrastructure.repository.ProductJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @BeforeEach
    void setup() {
        productJpaRepository.deleteAll();
    }

    @Test
    void shouldCreateProduct() throws Exception {
        String payload = """
                {
                  "name": "Power Bank 10000mAh",
                  "category": "POWER_BANK",
                  "description": "Power bank de 10000mAh",
                  "currentStock": 25,
                  "minimumStock": 5
                }
                """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Power Bank 10000mAh")))
                .andExpect(jsonPath("$.category", is("POWER_BANK")))
                .andExpect(jsonPath("$.currentStock", is(25)))
                .andExpect(jsonPath("$.minimumStock", is(5)));
    }

    @Test
    void shouldGetProductById() throws Exception {
        String payload = """
                {
                  "name": "Cable USB-C",
                  "category": "DATA_CABLE",
                  "description": "Cable de datos USB-C",
                  "currentStock": 10,
                  "minimumStock": 2
                }
                """;

        String response = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/api/v1/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())))
                .andExpect(jsonPath("$.name", is("Cable USB-C")));
    }

    @Test
    void shouldListProducts() throws Exception {
        String payload = """
                {
                  "name": "AirPods Pro",
                  "category": "AIRPODS",
                  "description": "Audífonos in-ear",
                  "currentStock": 8,
                  "minimumStock": 1
                }
                """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        String payload = """
                {
                  "name": "Smartwatch Lite",
                  "category": "SMARTWATCH",
                  "description": "Reloj inteligente",
                  "currentStock": 4,
                  "minimumStock": 1
                }
                """;

        String response = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        String updatePayload = """
                {
                  "name": "Smartwatch Lite Pro",
                  "category": "SMARTWATCH",
                  "description": "Reloj inteligente actualizado",
                  "minimumStock": 2
                }
                """;

        mockMvc.perform(put("/api/v1/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Smartwatch Lite Pro")))
                .andExpect(jsonPath("$.minimumStock", is(2)));
    }

    @Test
    void shouldAdjustStock() throws Exception {
        String payload = """
                {
                  "name": "Cargador USB",
                  "category": "CHARGER",
                  "description": "Cargador universal",
                  "currentStock": 10,
                  "minimumStock": 3
                }
                """;

        String response = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        String adjustmentPayload = """
                {
                  "delta": -4,
                  "reason": "Venta"
                }
                """;

        mockMvc.perform(patch("/api/v1/products/{id}/stock", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(adjustmentPayload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentStock", is(6)));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        String payload = """
                {
                  "name": "Audífonos Bluetooth",
                  "category": "BLUETOOTH_HEADPHONES",
                  "description": "Audífonos inalámbricos",
                  "currentStock": 7,
                  "minimumStock": 2
                }
                """;

        String response = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(delete("/api/v1/products/{id}", id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/products/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldRejectNegativeStock() throws Exception {
        String payload = """
                {
                  "name": "Power Bank Mini",
                  "category": "POWER_BANK",
                  "description": "Power bank compacto",
                  "currentStock": -1,
                  "minimumStock": 1
                }
                """;

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isBadRequest());
    }
}
