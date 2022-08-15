package io.github.gsnasc.testemkdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String area;

    @Column(length = 11)
    private String number;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Telefone(String area, String number, Cliente cliente) {
        this.area = area;
        this.number = number;
    }
}
