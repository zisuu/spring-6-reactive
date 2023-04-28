package ch.finecloud.spring6reactive.mappers;

import ch.finecloud.spring6reactive.domain.Customer;
import ch.finecloud.spring6reactive.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO customerToCustomerDto(Customer customer);
}
