package com.cliente.steps;

import com.cliente.core.entities.Cliente;
import com.cliente.core.gateways.ClienteRepositoryGateway;
import com.cliente.core.usecases.ClienteUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SalvarClienteSteps {

    @Mock
    private ClienteRepositoryGateway clienteRepositoryGateway;

    @InjectMocks
    private ClienteUseCase clienteUseCase;

    private Cliente cliente;
    private Exception exception;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um cliente válido")
    public void umClienteValido() {
        cliente = new Cliente(1L, "Nome", "email@example.com", "12345678909");
    }

    @When("o cliente é salvo")
    public void oClienteESalvo() {
        try {
            when(clienteRepositoryGateway.salvar(any(Cliente.class))).thenReturn(cliente);
            clienteUseCase.salvar(cliente);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("o cliente deve ser salvo com sucesso")
    public void oClienteDeveSerSalvoComSucesso() {
        Assertions.assertNotNull(cliente);
        Assertions.assertNull(exception);
    }
}