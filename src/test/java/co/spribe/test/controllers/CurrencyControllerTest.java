package co.spribe.test.controllers;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.repositiries.CurrencyRepository;
import co.spribe.test.facades.data.CurrencyDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

    public static final String CURRENCIES_PATH = "/currencies";

    public static final String USD_CODE = "USD";
    public static final String USD_NAME = "Dollar";
    public static final String UAH_CODE = "UAH";
    public static final String UAH_NAME = "Hryvnia";
    public static final String EUR_CODE = "EUR";
    public static final String EUR_NAME = "Euro";

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:alpine");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeAll
    public static void setUp() {
        postgresContainer.start();
    }

    @AfterAll
    public static void tearDown() {
        postgresContainer.stop();
    }

    @Test
    public void shouldAddCurrency() throws Exception {
        var newCurrency = new CurrencyDTO();
        newCurrency.setCode(EUR_CODE);
        newCurrency.setName(EUR_NAME);
        var jsonNewCurrency = objectMapper.writeValueAsString(newCurrency);

        var result = mockMvc.perform(post(CURRENCIES_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonNewCurrency))
                .andExpect(status().isCreated())
                .andReturn();

        var actualCurrency = objectMapper.readValue(result.getResponse().getContentAsString(), CurrencyDTO.class);
        assertThat(actualCurrency.getId()).isNotNull();
        assertThat(actualCurrency.getCode()).isEqualTo(newCurrency.getCode());
        assertThat(actualCurrency.getName()).isEqualTo(newCurrency.getName());

    }

    @Test
    public void shouldGetAllCurrencies() throws Exception {
        var uah = new Currency();
        uah.setCode(UAH_CODE);
        uah.setName(UAH_NAME);

        var usd = new Currency();
        usd.setCode(USD_CODE);
        usd.setName(USD_NAME);

        var currencies = List.of(uah, usd);
        currencyRepository.saveAll(currencies);

        var result = mockMvc.perform(get(CURRENCIES_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<CurrencyDTO> actualCurrencies = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>(){});
        assertThat(actualCurrencies.size()).isEqualTo(currencies.size());
        assertThat(actualCurrencies).extracting(CurrencyDTO::getId)
                .doesNotContainNull();
        assertThat(actualCurrencies).extracting(CurrencyDTO::getCode)
                .containsExactlyInAnyOrder(UAH_CODE, USD_CODE);
        assertThat(actualCurrencies).extracting(CurrencyDTO::getName)
                .containsExactlyInAnyOrder(UAH_NAME, USD_NAME);

    }
}