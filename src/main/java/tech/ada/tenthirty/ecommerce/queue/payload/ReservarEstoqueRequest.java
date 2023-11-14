package tech.ada.tenthirty.ecommerce.queue.payload;

import lombok.Data;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservarEstoqueRequest {

    private String compraId;
    private LocalDateTime dataCompra;
    private List<ItemAdicionadoRequest> items;
}
