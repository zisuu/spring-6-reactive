package ch.finecloud.spring6reactive.mappers;

import ch.finecloud.spring6reactive.domain.Beer;
import ch.finecloud.spring6reactive.model.BeerDTO;
import org.mapstruct.Mapper;


@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO beerDTO);

    BeerDTO beerToBeerDto(Beer beer);
}
