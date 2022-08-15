package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.model.Telefone;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({"area", "number"})
@Value
public class TelefoneDTO {

    @NotBlank(message = "A area não pode ser vazia!")
    private String area;

    @NotBlank(message = "O número não pode ser vazio!")
    private String number;

    public Telefone toTelefone() {
        Cliente c = new Cliente();

        return new Telefone(area, number, c);
    }

}
