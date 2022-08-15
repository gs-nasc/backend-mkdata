package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.model.Telefone;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({"id", "nome", "tipo", "document", "ie", "createdTime", "active"})
@Value
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String tipo;
    private String document;
    private String ie;
    private LocalDate createdTime;
    private Boolean active;
    public static ClienteResponseDTO toDTO(Cliente cliente) {
        return new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getTipo(), cliente.getDocument(), cliente.getIe(), cliente.getCreatedTime(), cliente.getActive());
    }

}
