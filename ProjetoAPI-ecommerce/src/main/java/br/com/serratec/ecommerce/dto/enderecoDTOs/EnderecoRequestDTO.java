package br.com.serratec.ecommerce.dto.enderecoDTOs;

public class EnderecoRequestDTO {
    private String cep;
    private Integer numero;
    private String complemento;
    
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
