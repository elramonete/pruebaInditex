package ucha.edu.pruebainditex.infrastructure.repositories.adapters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ucha.edu.pruebainditex.infrastructure.repositories.PriceRepository;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Brand;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PriceRepositoryAdapterTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    private Price price1;
    private Price price2;

    @BeforeEach
    public void setUp() {
        LocalDateTime startDate1 = LocalDateTime.of(2023, 4, 1, 0, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
        LocalDateTime startDate2 = LocalDateTime.of(2023, 5, 1, 0, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2023, 5, 31, 23, 59, 59);

        price1 = Price.builder()
                .endDate(endDate1)
                .price(new BigDecimal(25))
                .startDate(startDate1)
                .brand(new Brand(1L,"ZARA"))
                .productId(35455L)
                .currency("EUR")
                .id(1L)
                .priceList(2)
                .priority(0)
                .build();
        price2 = Price.builder()
                .endDate(endDate2)
                .price(new BigDecimal(35))
                .startDate(startDate2)
                .brand(new Brand(1L,"ZARA"))
                .productId(35455L)
                .currency("EUR")
                .id(1L)
                .priceList(2)
                .priority(0)
                .build();
    }

    @Test
     void testGetPriceWithExistingPrice() {
        List<Price> expectedPrices = new ArrayList<>();
        expectedPrices.add(price1);
        expectedPrices.add(price2);

        when(repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                1L, LocalDateTime.of(2023, 4, 15, 0, 0), LocalDateTime.of(2023, 4, 15, 0, 0), 1L))
                .thenReturn(expectedPrices);

        List<Price> actualPrices = priceRepositoryAdapter.getPrice(1L, LocalDateTime.of(2023, 4, 15, 0, 0), 1L);

        assertEquals(expectedPrices, actualPrices);
    }


}