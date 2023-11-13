package tech.ada.tenthirty.ecommerce.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import tech.ada.tenthirty.ecommerce.exception.NotFoundException;
import tech.ada.tenthirty.ecommerce.model.Compra;
import tech.ada.tenthirty.ecommerce.model.Item;
import tech.ada.tenthirty.ecommerce.model.StatusCompra;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;
import tech.ada.tenthirty.ecommerce.payload.response.CompraResponse;
import tech.ada.tenthirty.ecommerce.repository.CompraRepository;
import tech.ada.tenthirty.ecommerce.repository.ItemRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdicionarProdutosService {

    private final CompraRepository compraRepository;
    private final ItemRepository itemRepository;
    private final UpdateValorTotalCompraService updateValorTotalCompraService;
    public CompraResponse execute(ItemAdicionadoRequest itemAdicionadoRequest){
        Compra compra = new Compra();
        if(ObjectUtils.isEmpty(itemAdicionadoRequest.getIdCompra())){

            compra.setIdentificador(UUID.randomUUID().toString());
            compra.setDataCompra(LocalDateTime.now());
            compra.setStatusCompra(StatusCompra.INICIAL);
            compra.setValorTotal(BigDecimal.ZERO);
            compraRepository.save(compra);
        }else{
            compra = compraRepository.findByIdentificador(itemAdicionadoRequest.getIdCompra())
                    .orElseThrow(() -> new NotFoundException(itemAdicionadoRequest.getIdCompra()
                            , Compra.class));
        }
        Item item = new Item();
        item.setCompra(compra);
        item.setIdentificador(UUID.randomUUID().toString());
        item.setSku(itemAdicionadoRequest.getSkuId());
        item.setValorUnitario(BigDecimal.valueOf(itemAdicionadoRequest.getValorUnitario()));
        item.setQuantidadeUnidade(itemAdicionadoRequest.getQuantidade());
        itemRepository.save(item);
        updateValorTotalCompraService.execute(compra);

        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setId(compra.getIdentificador());
        compraResponse.setItens(List.of(itemAdicionadoRequest));
        return compraResponse;
    }
}
