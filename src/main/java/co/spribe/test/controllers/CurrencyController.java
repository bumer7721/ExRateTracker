package co.spribe.test.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @GetMapping
    public Map<String, String> getCurrencies() {
        return Map.of("message", "hello world");
    }

}
