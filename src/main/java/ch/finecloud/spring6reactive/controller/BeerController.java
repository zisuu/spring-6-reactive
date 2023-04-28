package ch.finecloud.spring6reactive.controller;

import ch.finecloud.spring6reactive.model.BeerDTO;
import ch.finecloud.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_PATH = "/api/v2/beers";
    public static final String BEER_PATH_BY_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @DeleteMapping(BEER_PATH_BY_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable Integer beerId){
        return beerService.deleteBeerById(beerId)
                .thenReturn(ResponseEntity
                        .noContent().build());
    }

    @PatchMapping(BEER_PATH_BY_ID)
    Mono<ResponseEntity<Void>> patchExistingBeer(@PathVariable Integer beerId,
                                                 @Validated @RequestBody BeerDTO beerDTO){
        return beerService.patchBeer(beerId, beerDTO)
                .map(savedDto -> ResponseEntity.noContent().build());
    }

    @PutMapping(BEER_PATH_BY_ID)
    Mono<ResponseEntity<Void>> updateExistingBeer(@PathVariable("beerId") Integer beerId,
                                                  @Validated @RequestBody BeerDTO beerDTO){
        return beerService.updateBeer(beerId, beerDTO)
                .map(savedDto -> ResponseEntity.noContent().build());
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> createNewBeer(@Validated @RequestBody BeerDTO beerDTO) {
        return beerService.saveNewBeer(beerDTO)
                .map(savedDto -> ResponseEntity.created(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/" + BEER_PATH + "/" + savedDto.getId())
                                .build().toUri())
                        .build());
    }

    @GetMapping(BEER_PATH_BY_ID)
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        return beerService.listBeers();
    }
}
