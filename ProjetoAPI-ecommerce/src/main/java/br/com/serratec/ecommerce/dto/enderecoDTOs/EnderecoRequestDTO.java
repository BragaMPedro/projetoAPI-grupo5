package br.com.serratec.ecommerce.dto.enderecoDTOs;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EnderecoRequestDTO {
//    @Pattern(regexp = "[0-9]{8}$", message = "CEP em formato inválido")
    @Size(max=8, message= "Opa opa, tem numeros demais aí hein")
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
