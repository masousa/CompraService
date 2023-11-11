# Compra Service
## Descrição 
 > Serviço para realização de compras do ecommerce
 

## Endpoints: 
  ### Realizar Compra
    URL: /
    Método: POST
  #### Request

``` json
{
  "cliente": {
    "id": "cb7961ec-7de5-11ee-b962-0242ac120002"
  },
  "endereco": {
    "complemento": "complemento",
    "numero": "12",
    "cep": "0000000000",
    "rua": "rua",
    "bairro": "bairro",
    "cidade": "cidade",
    "estado": "es"
  },
  "data-compra": "2023-11-09T00:42:11.623Z",
  "carrinho-id": "cb79646c-7de5-11ee-b962-0242ac120002",
  "formas-pagamento": [
    {
      "metodo": "Debito",
      "cvv": "000",
      "valor": 80.1,
      "numero-cartao": "123456xxx",
      "nome-cartao": "Joao Silva"
    }
  ]
}
```
#### Responses
  #### 201

``` json
{
  "id": 46487989
}
```

#### 400

``` json
{
  "message": "error message"
}
```