package ucha.edu.pruebainditex.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ucha.edu.pruebainditex.application.ports.RepositoryPort;
import ucha.edu.pruebainditex.application.ports.ServicePort;
import ucha.edu.pruebainditex.domain.dto.PriceDto;
import ucha.edu.pruebainditex.infrastructure.exceptions.EntityNotFoundException;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Price;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
@Service
public class PriceUseCase implements ServicePort {


        private RepositoryPort port;



        public PriceDto getPrice(Long brandId, LocalDateTime date, Long productId) {
            PriceDto priceDto;
            List<Price> prices = port.getPrice(brandId, date, productId);
            if(prices.isEmpty()){
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String formattedDate = date.format(formatter);
                throw new EntityNotFoundException("la entidad no se encuentra en base de datos, productid:" + productId + " brandId:" + brandId + " date:" + formattedDate );
            }else{
                Price price=prices.get(0);
                priceDto=PriceDto.builder()
                        .endDate(price.getEndDate())
                        .price( new DecimalFormat("0.00#").format(price.getPrice())+" "+price.getCurrency())
                        .startDate(price.getStartDate())
                        .brandName(price.getBrand().getBrandName())
                        .productId(price.getProductId())
                        .priceList(price.getPriceList())
                        .build();
            }
            return priceDto;


        }

}
