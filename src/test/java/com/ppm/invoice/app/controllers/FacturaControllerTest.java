package com.ppm.invoice.app.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.ppm.invoice.app.models.entity.Cliente;
import com.ppm.invoice.app.models.entity.Factura;
import com.ppm.invoice.app.models.entity.Producto;
import com.ppm.invoice.app.models.service.IClienteService;
import com.ppm.invoice.app.models.service.IInvoiceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

@RunWith(SpringRunner.class)
public class FacturaControllerTest {

    @Mock
    private IClienteService iClienteService;

    @Mock
    private IInvoiceService iInvoiceService;

    @Mock
    private Model model;

    @Mock
    private MessageSource  messageSource;

    @MockBean
    private SessionStatus sessionStatus;

    @MockBean
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private FacturaRestController facturaController;

    @InjectMocks
    private ClienteRestController clienteController;

    private List<Cliente> clienteList;

    @Before
    public void setUp() {

        clienteList= new ArrayList<>();
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
    public void whenCreateThenCreateFactura() throws Exception {
        when(iClienteService.findOne(1l)).thenReturn(clienteList.get(0));
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        RedirectAttributesModelMap redirectAttributes = (RedirectAttributesModelMap) request.getSession().getAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
        Locale testLocale = Locale.US;
        Map<String, Object> modelAttributes = new HashMap<>();
        Cliente response =  facturaController.crear(1l,any(HttpServletResponse.class));
        assertNotNull(response);
        //assertEquals(response, "factura/form");
    }

    @Test
    public void whenVerThenVerFactura() throws Exception {
        Factura factura = new Factura();
        factura.setId(1l);
        factura.setDescripcion("factura1");

        when(iInvoiceService.fetchInvoiceByIdWithClientWhithItemInvoiceWithProduct(1l)).thenReturn(
            factura);
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Locale testLocale = Locale.US;
        HttpServletResponse responseMock = mock(HttpServletResponse.class);
        Cliente response =  clienteController.ver(1l,responseMock);
        assertNull(response);
    }

    @Test
    public void whenNameClientThenProducts() {
        List<Producto> productos = new ArrayList<>();
        Producto producto = new Producto();
        producto.setId(1l);
        Producto producto2 = new Producto();
        producto.setId(2l);
        Producto producto3 = new Producto();
        producto.setId(3l);
        productos.add(producto);
        productos.add(producto2);
        productos.add(producto3);
        when(iClienteService.findByNombre("Pepe")).thenReturn(productos);
        List<Producto> productosResponse = facturaController.cargarProductos("Pepe");
        assertNotNull(productosResponse);
        assertEquals(productos.size(), productosResponse.size());
    }

    @Test
    public void whenGuardarThenGuardaFactura() {
        Factura factura = new Factura();
        factura.setId(1l);
        factura.setDescripcion("factura1");
        factura.setCliente(clienteList.get(0));
        BindingResult bindingResult = new BeanPropertyBindingResult(factura, "factura");
        List<Long> longList = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        Long[] longArray = longList.toArray(new Long[0]);
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Integer[] cantidad = integerList.toArray(new Integer[0]);
        when(iClienteService.findOne(1l)).thenReturn(clienteList.get(0));
        Locale testLocale = Locale.US;
        when(messageSource.getMessage("text.factura.flash.crear.success", null, testLocale)).thenReturn("Invoice created successfully!");
        String response =  facturaController.guardar(factura,bindingResult,model,longArray,cantidad,redirectAttributes,sessionStatus,testLocale);
        assertNotNull(response);
        assertEquals(response, "redirect:/ver/" + factura.getCliente().getId());
    }

    @Test
    public void whenEliminarThenEliminarFactura() {
        Factura factura = new Factura();
        factura.setId(1l);
        factura.setDescripcion("factura1");
        factura.setCliente(clienteList.get(0));
        when(iInvoiceService.findInvoiceById(1l)).thenReturn(factura);
        Locale testLocale = Locale.US;
        when(messageSource.getMessage("text.factura.flash.db.error", null, testLocale)).thenReturn("The invoice doesn't exist in the database");
        String response =  facturaController.eliminar(1l,redirectAttributes,testLocale);
        assertNotNull(response);
        assertEquals(response, "redirect:/ver/" + factura.getCliente().getId());
    }
}
