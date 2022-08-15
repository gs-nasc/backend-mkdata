package io.github.gsnasc.testemkdata.controller;

import io.github.gsnasc.testemkdata.dto.*;
import io.github.gsnasc.testemkdata.model.Cliente;
import io.github.gsnasc.testemkdata.model.Telefone;
import io.github.gsnasc.testemkdata.service.ClienteService;
import io.github.gsnasc.testemkdata.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/clientes")
@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TelefoneService telefoneService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> save(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente c = clienteService.save(clienteDTO.toCliente());
        return new ResponseEntity<>(ClienteResponseDTO.toDTO(c), HttpStatus.CREATED);
    }

    @PostMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteResponseDTO> update(@Valid @RequestBody ClienteUpdateDTO clienteDTO, @PathVariable Long id) {
        Cliente c = clienteService.filterById(id).get(0);
        c = clienteDTO.toClienteWithDiferences(c);
        c = clienteService.save(c);
        return new ResponseEntity<>(ClienteResponseDTO.toDTO(c), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        clienteService.delete(clienteService.filterById(id).get(0));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ClienteResponseDTO> list(@RequestParam(value = "active", required = false) Boolean active, @RequestParam(value = "name", required = false) String name) {
        if(active != null && name == null) {
            return clienteService.filterByActive(active).stream().map(ClienteResponseDTO::toDTO).collect(Collectors.toList());
        }else if(name != null && active == null) {
            return clienteService.filterByName(name).stream().map(ClienteResponseDTO::toDTO).collect(Collectors.toList());
        }else if(name != null) {
            return clienteService.filterByNameAndActive(name, active).stream().map(ClienteResponseDTO::toDTO).collect(Collectors.toList());
        }

        return clienteService.findAll().stream().map(ClienteResponseDTO::toDTO).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = "application/json")
    public List<ClienteResponseDTO> filterById(@PathVariable Long id) {
        return clienteService.filterById(id).stream().map(ClienteResponseDTO::toDTO).collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}/telefone", produces = "application/json")
    public List<TelefoneResponseDTO> listTelefone(@PathVariable Long id) {
        Cliente c = new Cliente();
        c.setId(id);
        return telefoneService.filterByCliente(c).stream().map(TelefoneResponseDTO::toDTO).collect(Collectors.toList());
    }

    @PostMapping(value = "/{id}/telefone", produces = "application/json")
    public ResponseEntity<TelefoneResponseDTO> saveTelefone(@Valid @RequestBody TelefoneDTO telefoneDTO, @PathVariable Long id) {
        Telefone t  = telefoneDTO.toTelefone();
        Cliente c = clienteService.filterById(id).get(0);
        t.setCliente(c);

        t = telefoneService.save(t);

        return new ResponseEntity<>(TelefoneResponseDTO.toDTO(t), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/telefone/{telId}", produces = "application/json")
    public ResponseEntity<TelefoneResponseDTO> saveTelefone(@Valid @RequestBody TelefoneUpdateDTO telefoneDTO, @PathVariable Long id, @PathVariable Long telId) {
        Telefone t = telefoneService.filterById(telId).get(0);
        t = telefoneDTO.toTelefoneWithDiferences(t);
        t = telefoneService.save(t);
        return new ResponseEntity<>(TelefoneResponseDTO.toDTO(t), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}/telefone/{telId}", produces = "application/json")
    public void delete(@PathVariable Long id, @PathVariable Long telId) {
        telefoneService.delete(telefoneService.filterById(telId).get(0));
    }
}
