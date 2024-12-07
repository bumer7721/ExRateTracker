package co.spribe.test.db.services.impl;

import co.spribe.test.db.entities.Currency;
import co.spribe.test.db.repositiries.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceImplTest {

    @Mock
    private CurrencyRepository currencyRepository;

    @Mock
    private Currency currency;

    @InjectMocks
    private CurrencyServiceImpl unit;

    @Test
    public void shouldSave() {
        when(currencyRepository.save(currency)).thenReturn(currency);

        var savedCurrency = unit.save(currency);

        verify(currencyRepository).save(currency);
        assertThat(savedCurrency).isEqualTo(currency);
    }

    @Test
    public void shouldFindAll() {
        var currencies = List.of(currency);
        when(currencyRepository.findAll()).thenReturn(currencies);

        var allCurrencies = unit.findAll();

        verify(currencyRepository).findAll();
        assertThat(allCurrencies).hasSize(currencies.size())
                .containsExactly(currency);
    }

}