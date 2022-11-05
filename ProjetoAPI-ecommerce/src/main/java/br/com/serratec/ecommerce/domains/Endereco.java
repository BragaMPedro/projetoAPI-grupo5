package br.com.serratec.ecommerce.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name= "endereco")
public class Endereco {
    
    @Id
    @Column(name="id_endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false)
    @NotNull(message = "Campo CEP deve ser preenchido")
    private String cep;
    
    @Column(length = 50, nullable = false)
    @NotNull(message = "Campo rua deve ser preenchido")
    private String rua;

    @Column(length = 40, nullable = false)
    @NotNull(message = "Campo Bairro deve ser preenchido")
    private String bairro;
    
    @Column(length = 40)
    private String cidade;

    @Column(length = 6, nullable = false)
    @DecimalMax(value = "1000000", message = "Opa, chegou na Entidade endereço o seu erro")
    @NotNull(message = "Campo Numero deve ser preenchido")
    private Integer numero;
    
    @Column(length = 50)
    private String complemento;
    
    @Column(length = 2)
    @Pattern(regexp = "[a-zA-Z]{2}$", message = "Campo espera 2 caracteres alfabéticos")
    private String uf;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public void viaCepEnderecoUniter(ViaCep viaCep){
       
        if(this.rua == null){
            this.rua = viaCep.getLogradouro();
        }
        if(this.bairro == null){
            this.bairro = viaCep.getBairro();
        }
        if(this.cidade == null){
            this.cidade = viaCep.getLocalidade();
        }
        if(this.uf == null){
            this.uf = viaCep.getUf(); 
        }  
      }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
