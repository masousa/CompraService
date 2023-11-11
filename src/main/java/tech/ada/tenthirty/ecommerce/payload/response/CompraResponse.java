package tech.ada.tenthirty.ecommerce.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;

import java.util.List;

@Data
@Schema
public class CompraResponse {
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002")
    private String id;
    private List<ItemAdicionadoRequest> itens;
}
