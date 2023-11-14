package tech.ada.tenthirty.ecommerce.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.ada.tenthirty.ecommerce.client.EstoqueClient;
import tech.ada.tenthirty.ecommerce.client.payload.ItemResponse;
import tech.ada.tenthirty.ecommerce.exception.NotFoundException;
import tech.ada.tenthirty.ecommerce.exception.QuantidadeIndisponivelException;
import tech.ada.tenthirty.ecommerce.model.Compra;
import tech.ada.tenthirty.ecommerce.model.Item;
import tech.ada.tenthirty.ecommerce.model.StatusCompra;
import tech.ada.tenthirty.ecommerce.payload.ItemAdicionadoRequest;
import tech.ada.tenthirty.ecommerce.payload.response.CompraResponse;
import tech.ada.tenthirty.ecommerce.repository.CompraRepository;
import tech.ada.tenthirty.ecommerce.repository.ItemRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdicionarProdutosServiceTest {

    @InjectMocks
    private AdicionarProdutosService adicionarProdutosService;

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UpdateValorTotalCompraService updateValorTotalCompraService;

    @Mock
    private EstoqueClient estoqueClient;

    @BeforeEach
    public void setup(){
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setQuantidade(50);
        when(estoqueClient.consultarEstoqueProduto(anyString()))
                .thenReturn(itemResponse);
    }

    @Test
    void shouldSaveANewCompra(){
        CompraResponse compraResponse = adicionarProdutosService
                .execute(getItemAdicionado("123",12.4));
        ArgumentCaptor<Compra> compraArgumentCaptor = ArgumentCaptor.forClass(Compra.class);
        verify(compraRepository, atLeast(1))
                .save(compraArgumentCaptor.capture());

        verify(itemRepository, times(1)).save(any(Item.class));

        assertEquals(compraResponse.getId(), compraArgumentCaptor.getValue().getIdentificador());
    }

    @Test
    void shouldUpdateAnExistingCompra(){
        Compra compraSample = getCompraSample();
        Mockito.when(compraRepository.findByIdentificador(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(compraSample));
        ItemAdicionadoRequest itemAdicionado = getItemAdicionado("123", 12.4);
        itemAdicionado.setIdCompra(compraSample.getIdentificador());
        CompraResponse compraResponse = adicionarProdutosService
                .execute(itemAdicionado);


        verify(itemRepository, times(1)).save(any(Item.class));

        verify(compraRepository, never())
                .save(any(Compra.class));

        assertEquals(compraResponse.getId(), compraSample.getIdentificador());
    }

    @Test
    void shouldRaiseAnExcetionWithCompraIdNotFound(){
        Mockito.when(compraRepository.findByIdentificador(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        ItemAdicionadoRequest itemAdicionado = getItemAdicionado("123", 12.4);
        itemAdicionado.setIdCompra("Any");

          assertThrows(NotFoundException.class,() -> adicionarProdutosService
                .execute(itemAdicionado));

    }

    @Test
     void shouldRaiseAnErrorWithStockQuantityIsLessThanRequired(){

        Compra compraSample = getCompraSample();
        Mockito.when(compraRepository.findByIdentificador(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(compraSample));
        ItemAdicionadoRequest itemAdicionado = getItemAdicionado("123", 12.4);
        itemAdicionado.setQuantidade(51);
        itemAdicionado.setIdCompra(compraSample.getIdentificador());
        assertThrows(QuantidadeIndisponivelException.class, ()-> adicionarProdutosService
                .execute(itemAdicionado));

    }

    private Compra getCompraSample() {
        Compra compra = new Compra();
        compra.setStatusCompra(StatusCompra.PENDENTE);
        compra.setDataCompra(LocalDateTime.now());
        compra.setValorTotal(BigDecimal.TEN);
        compra.setIdentificador(UUID.randomUUID().toString());
        compra.setId(1L);
        return compra;
    }

    private ItemAdicionadoRequest getItemAdicionado(String sku, double value){
        ItemAdicionadoRequest itemAdicionadoRequest = new ItemAdicionadoRequest();
        itemAdicionadoRequest.setValorUnitario(value);
        itemAdicionadoRequest.setSkuId(sku);
        itemAdicionadoRequest.setQuantidade(1);
        return itemAdicionadoRequest;
    }
}
