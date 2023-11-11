package tech.ada.tenthirty.ecommerce.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificarClienteService {

    @Async
    public void enviarConfirmacaoCompraCliente(String compraId){
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        stopWatch.stop();
        log.info("[NotificarCliente] Executou em: {}s",stopWatch.getTotalTimeSeconds());
    }
}
