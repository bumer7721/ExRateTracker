package co.spribe.test.facades.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.services.CurrencyService;
import co.spribe.test.facades.data.CurrencyDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyFacadeImplTest {

    public static final long ID = 1L;
    public static final String USD_CODE = "USD";
    public static final String USD_NAME = "Dollar";

    @Mock
    private CurrencyService currencyService;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private Currency currency;

    private CurrencyDTO currencyDTO;

    @InjectMocks
    private CurrencyFacadeImpl unit;

    @BeforeEach
    void setUp() {
        currencyDTO = new CurrencyDTO();
        currencyDTO.setId(ID);
        currencyDTO.setCode(USD_CODE);
        currencyDTO.setName(USD_NAME);

        when(modelMapper.map(currency, CurrencyDTO.class)).thenReturn(currencyDTO);
    }

    @Test
    public void addCurrency() {
        when(modelMapper.map(currencyDTO, Currency.class)).thenReturn(currency);
        when(currencyService.save(currency)).thenReturn(currency);

        var addedCurrency = unit.addCurrency(currencyDTO);

        verify(currencyService).save(currency);
        assertThat(addedCurrency).isEqualTo(currencyDTO);
    }

    @Test
    public void getAllCurrencies() {
        var currencies = List.of(currency);
        when(currencyService.findAll()).thenReturn(currencies);

        var allCurrencies = unit.getAllCurrencies();

        verify(currencyService).findAll();
        assertThat(allCurrencies).hasSize(currencies.size())
                .containsExactly(currencyDTO);
    }
}