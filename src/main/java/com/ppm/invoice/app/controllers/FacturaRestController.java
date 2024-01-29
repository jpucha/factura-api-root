package com.ppm.invoice.app.controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ppm.invoice.app.models.service.IInvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppm.invoice.app.models.entity.Cliente;
import com.ppm.invoice.app.models.entity.Factura;
import com.ppm.invoice.app.models.entity.DetalleFactura;
import com.ppm.invoice.app.models.entity.Producto;
import com.ppm.invoice.app.models.service.IClienteService;

/**
 * FacturaRestController specification.
 */
@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("invoiceApiServices/api/v1/invoice")
//@SessionAttributes("factura")
public class FacturaRestController {

	@Autowired
	private IClienteService clienteService;

	private IInvoiceService iInvoiceService;


	private final Logger log = LoggerFactory.getLogger(getClass());
	public static final String RESTYP = "restyp";
	public static final String RESTYP_SUCCESS = "SUCCESS";
	public static final String RESTYP_ERROR = "ERROR";
	public static final String RESTYP_INFO = "INFO";
	public static final String RESTYP_MES = "mes";
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping(path="/getInvoiceByIdWithClientWhithItemInvoiceWithProduct/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Factura getInvoiceByIdWithClientWhithItemInvoiceWithProduct(@PathVariable(value = "id") Long id, HttpServletResponse response) {

		Factura factura = this.iInvoiceService.fetchInvoiceByIdWithClientWhithItemInvoiceWithProduct(id); // clienteService.findFacturaById(id);

		if (factura == null) {
			response.setHeader(RESTYP, RESTYP_ERROR);
			factura = new Factura();
		}
		response.setHeader(RESTYP, RESTYP_SUCCESS);
		return factura;
	}

	@GetMapping("/form/{clienteId}")
	public Cliente crear(@PathVariable(value = "clienteId") Long clienteId, HttpServletResponse response)
		throws Exception {

		//get cliente
		Cliente cliente = clienteService.findOne(clienteId);

		if (cliente == null) {
			response.setHeader(RESTYP, RESTYP_ERROR);
			throw new Exception("Cliente no encontrado, no se puede crear una factura.");
		}
		return cliente;
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Producto> cargarProductos(@PathVariable String term) {
		return clienteService.findByNombre(term);
	}

	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", messageSource.getMessage("text.factura.form.titulo", null, locale));
			return "factura/form";
		}

		if (itemId == null || itemId.length == 0) {
			model.addAttribute("titulo", messageSource.getMessage("text.factura.form.titulo", null, locale));
			model.addAttribute("error", messageSource.getMessage("text.factura.flash.lineas.error", null, locale));
			return "factura/form";
		}

		for (int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);

			DetalleFactura linea = new DetalleFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);

			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}

		iInvoiceService.saveInvoice(factura);
		status.setComplete();

		flash.addFlashAttribute("success", messageSource.getMessage("text.factura.flash.crear.success", null, locale));

		return "redirect:/ver/" + factura.getCliente().getId();
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash, Locale locale) {

		Factura factura = iInvoiceService.findInvoiceById(id);

		if (factura != null) {
			iInvoiceService.deleteInvoice(id);
			flash.addFlashAttribute("success", messageSource.getMessage("text.factura.flash.eliminar.success", null, locale));
			return "redirect:/ver/" + factura.getCliente().getId();
		}
		flash.addFlashAttribute("error", messageSource.getMessage("text.factura.flash.db.error", null, locale));

		return "redirect:/listar";
	}

}
