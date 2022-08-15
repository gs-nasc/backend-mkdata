package io.github.gsnasc.testemkdata.repository;

import io.github.gsnasc.testemkdata.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Boolean existsByDocument(String document);

}
