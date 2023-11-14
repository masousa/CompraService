package tech.ada.tenthirty.ecommerce.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import tech.ada.tenthirty.ecommerce.exception.NotFoundException;
import tech.ada.tenthirty.ecommerce.model.Compra;
import tech.ada.tenthirty.ecommerce.model.Item;
import tech.ada.tenthirty.ecommerce.model.StatusCompra;
import tech.ada.tenthirty.ecommerce.payload.CompraRequest;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;
import tech.ada.tenthirty.ecommerce.payload.response.CompraResponse;
import tech.ada.tenthirty.ecommerce.queue.ReservarItemEstoqueProducer;
import tech.ada.tenthirty.ecommerce.queue.payload.ReservarEstoqueRequest;
import tech.ada.tenthirty.ecommerce.repository.CompraRepository;
import tech.ada.tenthirty.ecommerce.repository.ItemRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealizarCompraService {
    private final ItemRepository itemRepository;
    private final NotificarClienteService notificarClienteService;
    private final ReservarItemEstoqueProducer reservarItemEstoqueProducer;
    private final CompraRepository compraRepository;
    public final ModificarStatusCompraService modificarStatusCompraService;

    public CompraResponse realizarCompra (CompraRequest compraRequest){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Compra compra = compraRepository.findByIdentificador(compraRequest.getCompraId())
                .orElseThrow(() -> new NotFoundException(compraRequest.getCompraId(), Compra.class));
        List<ItemAdicionadoRequest> items =
                itemRepository.findByCompraId(compra.getId())
                        .stream().map(this::mapToItemAdicionadoRequest).toList();
        reservarItems(compra.getIdentificador(), items);

        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setId(compra.getIdentificador());
        compraResponse.setItens(items);

        modificarStatusCompraService.execute(compra, StatusCompra.PENDENTE);

        notificarClienteService.enviarConfirmacaoCompraCliente(compraResponse.getId());
        stopWatch.stop();
        log.info("[RealizarCompra] Executou em: {}s",stopWatch.getTotalTimeSeconds());
        return compraResponse;
    }

    private void reservarItems(String identificador, List<ItemAdicionadoRequest> items) {
        ReservarEstoqueRequest reservarEstoqueRequest = new ReservarEstoqueRequest();
        reservarEstoqueRequest.setCompraId(identificador);
        reservarEstoqueRequest.setItems(items);
        try {
            reservarItemEstoqueProducer.enviar(reservarEstoqueRequest);
        } catch (JsonProcessingException e) {
            log.error("Não foi possível enviar a mensagem ao destinatário", e);
            throw new RuntimeException(e);
        }
    }

    private ItemAdicionadoRequest mapToItemAdicionadoRequest(Item item) {
        ItemAdicionadoRequest itemAdicionadoRequest = new ItemAdicionadoRequest();
        itemAdicionadoRequest.setQuantidade(item.getQuantidadeUnidade());
        itemAdicionadoRequest.setSkuId(item.getSku());
        itemAdicionadoRequest.setValorUnitario(item.getValorUnitario().doubleValue());
        return itemAdicionadoRequest;
    }
}
