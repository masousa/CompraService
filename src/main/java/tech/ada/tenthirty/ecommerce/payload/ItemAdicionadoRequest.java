package tech.ada.tenthirty.ecommerce.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ItemAdicionadoRequest {
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002", defaultValue = "")
    @JsonProperty("id")
    private String idCompra;
    @Schema(example = "9877")
    private String skuId;
    @Schema(example = "5,79")
    private double valorUnitario;
    @Schema(example = "10")
    private int quantidade;
}
