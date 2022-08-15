package io.github.gsnasc.testemkdata.service;

import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @PersistenceContext
    protected EntityManager entityManager;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public Boolean existsByDocument(String document) {
        return clienteRepository.existsByDocument(document);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public List<Cliente> filterById(Long id) {
        Cliente c = new Cliente();
        c.setId(id);
        return clienteRepository.findAll(Example.of(c));
    }

    public List<Cliente> filterByActive(Boolean active) {
        Cliente c = new Cliente();
        c.setActive(active);

        return clienteRepository.findAll(Example.of(c));
    }

    public List<Cliente> filterByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);

        Root<Cliente> cliente = cq.from(Cliente.class);

        Predicate namePredicate = cb.like(cliente.get("nome"), "%" + name + "%");

        cq.where(namePredicate);

        TypedQuery<Cliente> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    public List<Cliente> filterByNameAndActive(String name, Boolean active) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Cliente> cq = cb.createQuery(Cliente.class);

        Root<Cliente> cliente = cq.from(Cliente.class);

        Predicate namePredicate = cb.like(cliente.get("nome"), "%" + name + "%");
        Predicate activePredicate = cb.equal(cliente.get("active"), active);

        cq.where(namePredicate, activePredicate);

        TypedQuery<Cliente> query = entityManager.createQuery(cq);
        return query.getResultList();
    }


}
