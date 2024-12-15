package co.spribe.test.facades.mapping;

import co.spribe.test.db.entities.ExchangeRate;
import co.spribe.test.facades.data.ExchangeRateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappingConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        var exchangeRateToDtoPropertyMapper = modelMapper.createTypeMap(ExchangeRate.class, ExchangeRateDTO.class);
        exchangeRateToDtoPropertyMapper.addMapping((er) -> er.getQuote().getCode(), ExchangeRateDTO::setQuote);

        return modelMapper;
    }
}
