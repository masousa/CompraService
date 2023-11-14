package tech.ada.tenthirty.ecommerce.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tech.ada.tenthirty.ecommerce.model.Compra;
import tech.ada.tenthirty.ecommerce.model.StatusCompra;
import tech.ada.tenthirty.ecommerce.repository.CompraRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ModificarStatusCompraService {
    private final CompraRepository compraRepository;

    @Async
    public void execute(Compra compra, StatusCompra statusCompra){
        compra.setStatusCompra(statusCompra);
        compraRepository.save(compra);
    }
}
