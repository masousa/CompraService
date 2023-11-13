package tech.ada.tenthirty.ecommerce.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.ada.tenthirty.ecommerce.model.Compra;
import tech.ada.tenthirty.ecommerce.model.Item;
import tech.ada.tenthirty.ecommerce.repository.CompraRepository;
import tech.ada.tenthirty.ecommerce.repository.ItemRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateValorTotalCompraService {
    private final CompraRepository compraRepository;
    private final ItemRepository itemRepository;

    @Async
    public void execute(Compra compra){
        log.info("[UpdateValorTotalCompraService] Atualizando valor total da compra");
        double valorTotal = itemRepository.findByCompraId(compra.getId())
                .stream().mapToDouble(item ->
                        item.getValorUnitario().multiply(
                                BigDecimal.valueOf(item.getQuantidadeUnidade()))
                                .doubleValue())
                .sum();
        log.info("[UpdateValorTotalCompraService] Valor total da compra {} é {}",
                compra.getId(), valorTotal);

        compra.setValorTotal(BigDecimal.valueOf(valorTotal));
        compraRepository.save(compra);

    }
}
