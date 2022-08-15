package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.model.Telefone;
import lombok.Value;

@JsonPropertyOrder({"id", "area", "number"})
@Value
public class TelefoneResponseDTO {
    private Long id;
    private String area;
    private String number;

    public static TelefoneResponseDTO toDTO(Telefone telefone) {
        return new TelefoneResponseDTO(telefone.getId(), telefone.getArea(), telefone.getNumber());
    }
}
