package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.validation.UniqueDocument;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonPropertyOrder({"nome", "tipo", "document", "ie", "active"})
@Value
public class ClienteUpdateDTO {

    @Size(min = 3, max = 40, message = "O nome precisa ter entre {min} e {max} caracteres!")
    private String nome;

    private String tipo;

    private String document;

    private String ie;

    private Boolean active;

    public Cliente toClienteWithDiferences(Cliente cliente) {
        if(nome != null) cliente.setNome(nome);
        if(tipo != null) cliente.setTipo(tipo);
        if(document != null) cliente.setDocument(document);
        if(ie != null) cliente.setIe(ie);
        if(active != null) cliente.setActive(active);

        return cliente;
    }
}
