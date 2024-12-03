package co.spribe.test.controllers;

import co.spribe.test.facades.CurrencyFacade;
import co.spribe.test.facades.data.CurrencyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    public CurrencyController(final CurrencyFacade currencyFacade) {
        this.currencyFacade = currencyFacade;
    }

    @GetMapping
    public List<CurrencyDTO> getCurrencies() {
        return currencyFacade.getAllCurrencies();
    }

}
