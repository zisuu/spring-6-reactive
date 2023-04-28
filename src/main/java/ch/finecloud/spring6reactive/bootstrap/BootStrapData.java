package ch.finecloud.spring6reactive.bootstrap;

import ch.finecloud.spring6reactive.domain.Beer;
import ch.finecloud.spring6reactive.domain.Customer;
import ch.finecloud.spring6reactive.repositories.BeerRepository;
import ch.finecloud.spring6reactive.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Component
public class BootStrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();

        loadCustomerData();

        beerRepository.count().subscribe(count -> {
            System.out.println("Beer Count: " + count);
        });

        customerRepository.count().subscribe(count -> {
            System.out.println("Customer Count is: " + count);
        });
    }

    private void loadBeerData() {
        beerRepository.count().subscribe(count -> {
            if (count == 0) {
                Beer beer1 = Beer.builder()
                        .beerName("Moudi Bier")
                        .beerStyle("NEIPA")
                        .upc("123456")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(100)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer2 = Beer.builder()
                        .beerName("Moudi Bier")
                        .beerStyle("PILSNER")
                        .upc("9567")
                        .price(new BigDecimal("10.50"))
                        .quantityOnHand(100)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();

                Beer beer3 = Beer.builder()
                        .beerName("Moudi Bier")
                        .beerStyle("ALE")
                        .upc("45452")
                        .price(new BigDecimal("9.99"))
                        .quantityOnHand(100)
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build();
                beerRepository.save(beer1).subscribe();
                beerRepository.save(beer2).subscribe();
                beerRepository.save(beer3).subscribe();
            }
        });
    }


    private void loadCustomerData() {
        customerRepository.count().subscribe(count -> {
            if(count == 0){
                customerRepository.save(Customer.builder()
                                .customerName("Customer 1")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 2")
                                .build())
                        .subscribe();

                customerRepository.save(Customer.builder()
                                .customerName("Customer 3")
                                .build())
                        .subscribe();
            }
        });
    }
}
