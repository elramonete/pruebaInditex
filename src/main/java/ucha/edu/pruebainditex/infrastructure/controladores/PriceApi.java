package ucha.edu.pruebainditex.infrastructure.repositories;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import ucha.edu.pruebainditex.domain.entities.Price;

import java.time.LocalDateTime;

public interface PriceApi {


    @Tag(name = "prices", description = "Operaciones de consulta de precios")
    public interface PricesApi {
        @Operation(summary = "Obtener el precio de un producto para una fecha determinada")
        @ApiResponse(responseCode = "200", description = "Precio del producto encontrado", content = @Content(schema = @Schema(implementation = Price.class)))
        @ApiResponse(responseCode = "404", description = "Precio del producto no encontrado")
        ResponseEntity<Price> getProductPrice(@Parameter(description = "Identificador de la cadena de la marca", example = "1") Long brandId,
                                              @Parameter(description = "Fecha y hora de consulta del precio", example = "2020-06-14T10:00:00") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                              @Parameter(description = "Identificador del producto", example = "35455") Long productId);
    }
}
