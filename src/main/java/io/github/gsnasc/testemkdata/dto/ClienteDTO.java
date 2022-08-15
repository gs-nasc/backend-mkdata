package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.validation.UniqueDocument;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@JsonPropertyOrder({"nome", "tipo", "document", "ie", "active"})
@Value
public class ClienteDTO {

    @NotBlank(message = "O nome não pode ser vazio!")
    @Size(min = 3, max = 120, message = "O nome precisa ter entre {min} e {max} caracteres!")
    private String nome;

    @NotBlank(message = "o Tipo não pode ser vazio!")
    private String tipo;

    @UniqueDocument
    @NotBlank(message = "o Documento não pode ser vazio!")
    private String document;

    private String ie;

    private Boolean active;

    public Cliente toCliente() {
        return new Cliente(nome, tipo, document, ie, active);
    }
}
