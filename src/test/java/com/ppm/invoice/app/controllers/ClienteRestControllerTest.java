package com.ppm.invoice.app.controllers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ppm.invoice.app.models.entity.Cliente;
import com.ppm.invoice.app.models.service.IClienteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(SpringRunner.class)
public class ClienteRestControllerTest {

    @Mock
    private IClienteService iClienteService;

    @InjectMocks
    private ClienteRestController clienteRestController;

    private List<Cliente> clienteList;

    @Before
    public void setUp() {
        clienteList = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Pepe");
        cliente1.setId(1l);
        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Maria");
        cliente2.setId(2l);
        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Juan");
        cliente3.setId(3l);
        clienteList.add(cliente1);
        clienteList.add(cliente2);
        clienteList.add(cliente3);
    }

    @Test
    public void whenCreateThenNewCliente() {
        when(iClienteService.findAll()).thenReturn(clienteList);
        Cliente cliente = new Cliente();
        cliente.setNombre("Teresa");
        cliente.setId(1l);
        BindingResult bindingResult = new BeanPropertyBindingResult(cliente, "cliente");
        ResponseEntity<?> response = clienteRestController.create(cliente, bindingResult);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void whenGethenAllList() {
        when(iClienteService.findAll()).thenReturn(clienteList);
        List<Cliente> clients = clienteRestController.index();
        assertNotNull(clients);
        assertEquals(clienteList.size(), clients.size());
    }

    @Test
    public void whenIdThenGetClienteId() {
        when(iClienteService.findById(1l)).thenReturn(clienteList.get(0));
        ResponseEntity<Cliente> response = (ResponseEntity<Cliente>) clienteRestController.show(1l);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(clienteList.get(0).getId(), response.getBody().getId());
    }

    @Test
    public void whenUpdateThenGetUpdateId() {
        when(iClienteService.findById(1l)).thenReturn(clienteList.get(0));
        Cliente cliente = new Cliente();
        cliente.setNombre("Teresa");
        BindingResult bindingResult = new BeanPropertyBindingResult(cliente, "cliente");
        ResponseEntity<Map<String, Object>> response = (ResponseEntity<Map<String, Object>>) clienteRestController.update(
            cliente, bindingResult, 1l);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals("El cliente ha sido actualizado con éxito!",
            response.getBody().get("mensaje"));
    }

    @Test
    public void whenDeleteThenDeleteId() {

        ResponseEntity<Map<String, Object>> response = (ResponseEntity<Map<String, Object>>) clienteRestController.delete(
            1l);
        assertNotNull(response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals("El cliente eliminado con éxito!", response.getBody().get("mensaje"));
    }
}
