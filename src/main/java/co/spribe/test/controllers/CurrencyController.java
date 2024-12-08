package co.spribe.test.controllers;

import co.spribe.test.facades.CurrencyFacade;
import co.spribe.test.facades.data.CurrencyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    public CurrencyController(final CurrencyFacade currencyFacade) {
        this.currencyFacade = currencyFacade;
    }

    @PostMapping
    public ResponseEntity<CurrencyDTO> addCurrency(@RequestBody CurrencyDTO currencyDTO) {
        CurrencyDTO savedCurrency = currencyFacade.addCurrency(currencyDTO);
        return new ResponseEntity<>(savedCurrency, HttpStatus.CREATED);
    }

    @GetMapping
    public List<CurrencyDTO> getCurrencies() {
        return currencyFacade.getAllCurrencies();
    }

}
