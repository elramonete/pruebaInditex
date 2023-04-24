package ucha.edu.pruebainditex.application.ports;

import ucha.edu.pruebainditex.domain.dto.PriceDto;

import java.time.LocalDateTime;
public interface ServicePort {
    PriceDto getPrice(Long brandId, LocalDateTime date, Long productId);
}
