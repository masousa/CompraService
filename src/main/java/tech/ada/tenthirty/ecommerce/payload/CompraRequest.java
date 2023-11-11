package tech.ada.tenthirty.ecommerce.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema
public class CompraRequest {
    @JsonProperty("data-compra")
    @Schema(name = "data-compra", example = "21/12/23 09:50:10")
    private LocalDateTime dataCompra;

    @JsonProperty("carrinho-id")
    @Schema(example = "cb79646c-7de5-11ee-b962-0242ac120002")
    private String compraId;

    private ClienteRequest cliente;

    private EnderecoRequest endereco;

    @JsonProperty("formas-pagamento")
    private List<PagamentoRequest> pagamentoRequests;

}
