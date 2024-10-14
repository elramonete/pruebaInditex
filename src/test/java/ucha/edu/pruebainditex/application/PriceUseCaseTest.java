package ucha.edu.pruebainditex.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ucha.edu.pruebainditex.domain.ports.RepositoryPort;
import ucha.edu.pruebainditex.domain.dto.PriceDto;
import ucha.edu.pruebainditex.domain.exceptions.EntityNotFoundException;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Brand;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Price;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


    @Mock
    private RepositoryPort port;

    @InjectMocks
    private PriceUseCase priceUseCase;

    private Price price;

    @BeforeEach
    public void setUp() {
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
        price = Price.builder()
                .endDate(endDate)
                .price(new BigDecimal(25))
                .startDate(startDate)
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
        List<Price> prices = new ArrayList<>();
        prices.add(price);

        when(port.getPrice(1L, LocalDateTime.of(2023, 4, 15, 0, 0), 1L)).thenReturn(prices);

        PriceDto expectedPriceDto = PriceDto.builder()
                .brandName(price.getBrand().getBrandName())
                .productId(price.getProductId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price( new DecimalFormat("0.00#").format(price.getPrice())+" "+price.getCurrency())
                .priceList(price.getPriceList())
                .build();

        PriceDto actualPriceDto = priceUseCase.getPrice(1L, LocalDateTime.of(2023, 4, 15, 0, 0), 1L);

        assertEquals(expectedPriceDto, actualPriceDto);
    }

    @Test
     void testGetPriceWithNonExistingPrice() {
        List<Price> prices = new ArrayList<>();

        when(port.getPrice(1L, LocalDateTime.of(2023, 5, 1, 0, 0), 1L)).thenReturn(prices);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDate = LocalDateTime.of(2023, 5, 1, 0, 0).format(formatter);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> priceUseCase.getPrice(1L, LocalDateTime.of(2023, 5, 1, 0, 0), 1L));

        assertEquals("la entidad no se encuentra en base de datos, productid:1 brandId:1 date:" + formattedDate,
                exception.getMessage());
    }
}