package io.github.gsnasc.testemkdata.repository;

import io.github.gsnasc.testemkdata.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
