package io.github.gsnasc.testemkdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String nome;

    @Column(length = 2)
    private String tipo;

    @Column(length = 14, unique = true)
    private String document;

    @Column(length = 20)
    private String ie;

    @Column(name = "created_time")
    @CreationTimestamp
    private LocalDate createdTime;

    @Column()
    private Boolean active;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    public Cliente(String nome, String tipo, String document, String ie, Boolean active) {
        this.nome = nome;
        this.tipo = tipo;
        this.document = document;
        this.ie = ie;
        this.active = active;
        this.telefones = new ArrayList<>();
    }

    public Cliente(String nome, String tipo, String document, String ie, Boolean active, List<Telefone> telefones) {
        this.nome = nome;
        this.tipo = tipo;
        this.document = document;
        this.ie = ie;
        this.active = active;
        this.telefones = telefones;
    }
}
