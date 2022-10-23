package br.com.serratec.ecommerce.dto;

public class ItemPedidoRequestDTO {
    
    private int quantidade;

    private double preco_venda;

    private double percentual_desconto;

    private double valor_liquido;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public double getPercentual_desconto() {
        return percentual_desconto;
    }

    public void setPercentual_desconto(double percentual_desconto) {
        this.percentual_desconto = percentual_desconto;
    }

    public double getValor_liquido() {
        return valor_liquido;
    }

    public void setValor_liquido(double valor_liquido) {
        this.valor_liquido = valor_liquido;
    }

}
