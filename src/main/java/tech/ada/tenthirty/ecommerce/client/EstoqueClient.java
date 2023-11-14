package tech.ada.tenthirty.ecommerce.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import tech.ada.tenthirty.ecommerce.client.payload.ItemResponse;

public interface EstoqueClient {

    @GetExchange(value = "/query/{sku}")
    ItemResponse consultarEstoqueProduto(@PathVariable(value = "sku") String sku);

}
