package tech.ada.tenthirty.ecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class ClienteRequest {
    @Schema(name = "id", example = "cb7961ec-7de5-11ee-b962-0242ac120002")
    private String id;
}
