package tech.ada.tenthirty.ecommerce.exception;

public class QuantidadeIndisponivelException extends RuntimeException{
    public QuantidadeIndisponivelException(){
        super("Quantidade indisponível");
    }

    public QuantidadeIndisponivelException(String produto){
        super(String.format("Produto %s possui quantidade no estoque inferior à requisitada ", produto));
    }
}
