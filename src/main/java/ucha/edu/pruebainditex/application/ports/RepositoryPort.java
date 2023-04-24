package ucha.edu.pruebainditex.application.ports;

import ucha.edu.pruebainditex.infrastructure.repositories.entities.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface RepositoryPort {

    List<Price> getPrice(Long brandId, LocalDateTime date, Long productId);
}
