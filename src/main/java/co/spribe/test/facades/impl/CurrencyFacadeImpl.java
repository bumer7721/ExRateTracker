package co.spribe.test.facades.impl;

import co.spribe.test.db.services.CurrencyService;
import co.spribe.test.facades.CurrencyFacade;
import co.spribe.test.facades.data.CurrencyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CurrencyFacadeImpl implements CurrencyFacade {

    private final ModelMapper modelMapper;
    private final CurrencyService currencyService;

    public CurrencyFacadeImpl(CurrencyService currencyService, ModelMapper modelMapper) {
        this.currencyService = currencyService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyService.findAll().stream()
                .map(c -> modelMapper.map(c, CurrencyDTO.class))
                .toList();
    }
}
