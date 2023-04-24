package ucha.edu.pruebainditex.infrastructure.controladores;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ucha.edu.pruebainditex.application.ports.ServicePort;
import ucha.edu.pruebainditex.domain.dto.PriceDto;

import java.time.LocalDateTime;
@AllArgsConstructor
@RestController
public class PriceControllerAdapter implements PriceApi{
    private ServicePort port;
    @Override
    public ResponseEntity<PriceDto> getProductPrice(Long brandId, LocalDateTime date, Long productId) {
        PriceDto price = port.getPrice(brandId, date, productId);
        return ResponseEntity.ok(price);
    }
}
