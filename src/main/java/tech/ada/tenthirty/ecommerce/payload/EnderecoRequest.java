package tech.ada.tenthirty.ecommerce.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class EnderecoRequest {
    @Schema(example = "complemento")
    private String complemento;
    @Schema(example = "12")
    private String numero;
    @Schema(example = "0000000000")
    private String cep;
    @Schema(example = "rua")
    private String rua;
    @Schema(example = "bairro")
    private String bairro;
    @Schema(example = "cidade")
    private String cidade;
    @Schema(example = "es")
    private String estado;

}
