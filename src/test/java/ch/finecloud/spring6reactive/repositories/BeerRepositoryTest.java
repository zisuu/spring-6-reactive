package ch.finecloud.spring6reactive.repositories;

import ch.finecloud.spring6reactive.config.DatabaseConfig;
import ch.finecloud.spring6reactive.domain.Beer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(getTestBeer()));
    }

    @Test
    void saveNewBeer() {
        beerRepository.save(getTestBeer())
                .subscribe(beer ->
                        System.out.println("Saved Beer: " + beer.toString()));
    }

    Beer getTestBeer() {
        return Beer.builder()
                .beerName("Test Beer")
                .beerStyle("Test Style")
                .upc("123456789012")
                .quantityOnHand(100)
                .price(new BigDecimal("12.99"))
                .build();
    }
}