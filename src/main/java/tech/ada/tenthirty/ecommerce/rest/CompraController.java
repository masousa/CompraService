package tech.ada.tenthirty.ecommerce.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.tenthirty.ecommerce.payload.CompraRequest;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;
import tech.ada.tenthirty.ecommerce.payload.response.CompraResponse;
import tech.ada.tenthirty.ecommerce.services.RealizarCompraService;

@RestController
@RequestMapping(name = "/compra")
@RequiredArgsConstructor
public class CompraController {

    private final RealizarCompraService realizarCompraService;
    @Operation(summary = "Realizar a compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "compra realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao realizar a compra"),
    })
    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public CompraResponse realizarCompra(@RequestBody CompraRequest compraRequest){

        return realizarCompraService.realizarCompra(compraRequest);
    }


    @Operation(summary = "Adicionar Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item Adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar um item"),
    })
    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CompraResponse adicionarItem(@RequestBody ItemAdicionadoRequest itemAdicionadoRequest){
        return new CompraResponse();
    }

}
