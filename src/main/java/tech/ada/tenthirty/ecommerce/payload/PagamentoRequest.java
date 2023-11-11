package tech.ada.tenthirty.ecommerce.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class PagamentoRequest {

    @Schema(example = "Debito")
    private String metodo;
    @JsonProperty("numero-cartao")
    @Schema(example = "123456xxx")
    private String numeroCartao;
    @JsonProperty("nome-cartao")
    @Schema(example = "Joao Silva")
    private String nomeCartao;
    @Schema(example = "000")
    private String cvv;
    @Schema(example = "80.10")
    private double valor;
}
