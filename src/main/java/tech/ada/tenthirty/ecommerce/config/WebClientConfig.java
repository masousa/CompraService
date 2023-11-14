package tech.ada.tenthirty.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tech.ada.tenthirty.ecommerce.client.EstoqueClient;

@Configuration
public class WebClientConfig {
    @Value("${negocio.estoque.url}")
    private String estoqueUrl;


    @Bean
    WebClient webClient(){
        return WebClient.builder().baseUrl(estoqueUrl)
                .build();
    }

    @Bean
    EstoqueClient estoqueClient (WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter
                        .forClient(webClient)).build();
        return httpServiceProxyFactory.createClient(EstoqueClient.class);
    }
}
