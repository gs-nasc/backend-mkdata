package io.github.gsnasc.testemkdata.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.model.Telefone;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@JsonPropertyOrder({"area", "number", "cliente"})
@Value
public class TelefoneUpdateDTO {

    private String area;
    private String number;

    public Telefone toTelefoneWithDiferences(Telefone telefone) {
        if(area != null) telefone.setArea(area);
        if(number != null) telefone.setNumber(number);

        return telefone;
    }

}
