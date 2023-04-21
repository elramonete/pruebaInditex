package ucha.edu.pruebainditex.infrastructure.ports;

import ucha.edu.pruebainditex.domain.entities.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ServicePort {
    Optional<Price> getPrice(Long brandId, LocalDateTime date, Long productId, Integer priceList);
}
