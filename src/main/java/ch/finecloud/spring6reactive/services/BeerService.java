package ch.finecloud.spring6reactive.services;

import ch.finecloud.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface BeerService {
    Flux<BeerDTO> listBeers();

    Mono<BeerDTO> getBeerById(Integer beerId);

    Mono<BeerDTO> saveNewBeer(BeerDTO beerDTO);

    Mono<BeerDTO> updateBeer(Integer beerId, BeerDTO beerDTO);

    Mono<BeerDTO> patchBeer(Integer beerId, BeerDTO beerDTO);

    Optional<Object> deleteBeerById(Integer beerId);
}
