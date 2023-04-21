package ucha.edu.pruebainditex.infrastructure.repositories.adapters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ucha.edu.pruebainditex.application.ports.RepositoryPort;
import ucha.edu.pruebainditex.infrastructure.repositories.PriceRepository;
import ucha.edu.pruebainditex.infrastructure.repositories.entities.Price;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@Repository
public class PriceAdapter implements RepositoryPort {

    PriceRepository repository;
    public List<Price> getPrice(Long brandId, LocalDateTime date, Long productId){
        List<Price> prices = repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(brandId,date,date,productId);
        return prices;
    }
}
