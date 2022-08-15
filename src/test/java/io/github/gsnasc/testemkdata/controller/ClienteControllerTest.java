package io.github.gsnasc.testemkdata.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.gsnasc.testemkdata.dto.ClienteDTO;
import io.github.gsnasc.testemkdata.model.Telefone;
import io.github.gsnasc.testemkdata.repository.ClienteRepository;
import io.github.gsnasc.testemkdata.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setup() {
        clienteRepository.deleteAll();
    }

    @Test
    void save() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Teste", "PF", "51472611845", null, true);
        mockMvc.perform(post("/clientes").contentType("application/json;charset=UTF-8").content(objectMapper.writeValueAsString(clienteDTO))).andDo(print()).andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // Nome
    @Test
    void invalidName() throws Exception {
        testName(null, "O nome não pode ser vazio!");
        testName("", "O nome não pode ser vazio!");
        testName("aa", "O nome precisa ter entre 3 e 120 caracteres!");
    }

    void testName(String name, String expectedResponse) throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(name, "PF", "51472611845", null, true);
        mockMvc.perform(post("/clientes").contentType("application/json;charset=UTF-8").content(objectMapper.writeValueAsString(clienteDTO))).andExpect(status().isBadRequest()).andExpect(jsonPath("$.nome").value(expectedResponse)).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    // CPF
    @Test
    void invalidCPF() throws Exception {
        testCPF(null, "o Documento não pode ser vazio!");
        testCPF("", "o Documento não pode ser vazio!");
    }

    void testCPF(String cpf, String expectedResponse) throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO("Teste", "PF", cpf, null, true);
        mockMvc.perform(post("/clientes").contentType("application/json;charset=UTF-8").content(objectMapper.writeValueAsString(clienteDTO))).andExpect(status().isBadRequest()).andExpect(jsonPath("$.document").value(expectedResponse)).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
