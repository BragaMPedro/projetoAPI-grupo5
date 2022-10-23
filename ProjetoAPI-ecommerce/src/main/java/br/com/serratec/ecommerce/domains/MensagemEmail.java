package br.com.serratec.ecommerce.domains;

import java.util.List;

public class MensagemEmail {
    
    private String assunto;
    private String mensagem;
    private String remetente;
    private List<String> destinatario;
    
    public MensagemEmail(String assunto, String mensagem, String remetente, List<String> destinatario) {
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public String getMensagem() {
        return mensagem;
    }
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getRemetente() {
        return remetente;
    }
    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
    public List<String> getDestinatario() {
        return destinatario;
    }
    public void setDestinatario(List<String> destinatario) {
        this.destinatario = destinatario;
    }
}
