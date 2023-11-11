package tech.ada.tenthirty.ecommerce.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import tech.ada.tenthirty.ecommerce.payload.CompraRequest;
import tech.ada.tenthirty.ecommerce.payload.response.CompraResponse;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RealizarCompraService {
    private final NotificarClienteService notificarClienteService;

    public CompraResponse realizarCompra (CompraRequest compraRequest){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        CompraResponse compraResponse = new CompraResponse();
        compraResponse.setId(UUID.randomUUID().toString());

        notificarClienteService.enviarConfirmacaoCompraCliente(compraResponse.getId());
        stopWatch.stop();
        log.info("[RealizarCompra] Executou em: {}s",stopWatch.getTotalTimeSeconds());
        return compraResponse;
    }
}
