package co.za.invoice.register.web.rest;

import co.za.invoice.register.web.rest.vo.InvoiceRequest;
import co.za.invoice.register.web.rest.vo.InvoiceResponse;
import co.za.invoice.register.web.rest.vo.InvoiceResponses;
import co.za.invoice.register.services.InvoiceService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "invoices/")
public class InvoiceRestController {

	private InvoiceService invoiceService;

	public InvoiceRestController(final InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	@RequestMapping(method = GET, produces = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE})
	public InvoiceResponses viewAllInvoices() {
		return invoiceService.getAllInvoices();
	}

	@RequestMapping(value = "/{invoiceId}", method = GET, produces = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE})
	public InvoiceResponse viewInvoice(@PathVariable final String invoiceId) {
		return invoiceService.getInvoice(new Long(invoiceId));
	}

	@RequestMapping(method = POST, produces = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE},
			consumes = {APPLICATION_XML_VALUE, APPLICATION_JSON_VALUE})
	public InvoiceResponse addInvoice(@RequestBody final InvoiceRequest invoiceRequest) {
		return invoiceService.addInvoice(invoiceRequest);
	}

}
