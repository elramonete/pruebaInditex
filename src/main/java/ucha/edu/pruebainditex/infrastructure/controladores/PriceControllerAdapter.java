package ucha.edu.pruebainditex.infrastructure.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ucha.edu.pruebainditex.domain.entities.Price;

import java.time.LocalDateTime;
@RestController
public class PriceApiImpl implements PriceApi{

    @Override
    public ResponseEntity<Price> getProductPrice(Long brandId, LocalDateTime date, Long productId) {
        return ResponseEntity.ok(new Price());
    }
}
