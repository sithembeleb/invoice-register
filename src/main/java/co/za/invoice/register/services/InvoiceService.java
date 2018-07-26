package co.za.invoice.register.services;

import co.za.invoice.register.persistance.entity.InvoiceEntity;
import co.za.invoice.register.persistance.entity.LineItemEntity;
import co.za.invoice.register.persistance.repository.InvoiceRepository;
import co.za.invoice.register.web.rest.vo.InvoiceRequest;
import co.za.invoice.register.web.rest.vo.InvoiceResponse;
import co.za.invoice.register.web.rest.vo.InvoiceResponses;
import co.za.invoice.register.web.rest.vo.LineItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class InvoiceService {

	private static final Logger log = LoggerFactory
			.getLogger(InvoiceService.class);

	private InvoiceRepository invoiceRepository;

	@Autowired
	public InvoiceService(InvoiceRepository invoiceRepository) {
		this.invoiceRepository = invoiceRepository;
	}

	public InvoiceResponses getAllInvoices() {
		List<InvoiceResponse> allInvoices = invoiceRepository.findAll().stream()
				.map(invoiceResponses -> mapInvoiceEntityToInvoiceRequest(invoiceResponses))
				.collect(Collectors.toList());
		InvoiceResponses invoiceResponses = new InvoiceResponses();

		try {
			if (allInvoices.isEmpty()) {
				throw new ValidationException("No Invoices found");
			}
		} catch (ValidationException e) {
			allInvoices.add(new InvoiceResponse(BAD_REQUEST.toString(), BAD_REQUEST.getReasonPhrase(), e.getMessage()));
			invoiceResponses.setInvoices(allInvoices);
			return invoiceResponses;
		}
		invoiceResponses.setInvoices(allInvoices);
		return invoiceResponses;
	}

	public InvoiceResponse getInvoice(final Long id) {
		Optional<InvoiceEntity> invoice = invoiceRepository.findById(id);
		log.info("Getting Invoice for id {}, found = {} ", id,
				invoice.isPresent());

		try {
			if (!invoice.isPresent()) {
				throw new ValidationException(format("No Records found for invoiceId {%s}", id));
			}
		} catch (ValidationException e) {
			return new InvoiceResponse(BAD_REQUEST.toString(), BAD_REQUEST.getReasonPhrase(), e.getMessage());
		}
		return mapInvoiceEntityToInvoiceRequest(invoice.get());
	}

	public InvoiceResponse addInvoice(final InvoiceRequest invoice) {
		log.info("Adding invoice [{}]", invoice);
		try {
			if (null == invoice.getClient() || null == invoice.getVatRate() || null == invoice.getLineItems()) {
				throw new ValidationException("All required fields must be populated *Client, *VatRate and *lineItems");
			}
		} catch (ValidationException e) {
			return new InvoiceResponse(BAD_REQUEST.toString(), BAD_REQUEST.getReasonPhrase(), e.getMessage());
		}

		return mapInvoiceEntityToInvoiceRequest(invoiceRepository.save(mapInvoiceRequestToInvoiceEntity(invoice)));
	}

	private InvoiceEntity mapInvoiceRequestToInvoiceEntity(final InvoiceRequest invoiceRequest) {
		log.info("Mapping InvoiceRequest [{}] to Entity ", invoiceRequest.toString());
		List<LineItemEntity> lineItemEntities = invoiceRequest.getLineItems().stream()
				.map(lineItem -> new LineItemEntity(lineItem.getQuantity(), lineItem.getDescription(), lineItem.getUnitPrice()))
				.collect(Collectors.toList());
		return new InvoiceEntity(invoiceRequest.getClient(), invoiceRequest.getVatRate(), lineItemEntities);
	}

	private InvoiceResponse mapInvoiceEntityToInvoiceRequest(final InvoiceEntity invoiceEntity) {
		log.info("Mapping InvoiceEntity [{}] to Response", invoiceEntity.toString());
		InvoiceResponse invoiceResponse = new InvoiceResponse(invoiceEntity.getId(), invoiceEntity.getClient(), invoiceEntity.getVatRate(), invoiceEntity.getCreatedDate(),
				new ArrayList<>(), invoiceEntity.getSubTotal(), invoiceEntity.getVatAmount(), invoiceEntity.getTotal());
		List<LineItem> lineItemEntities = invoiceEntity.getLineItemEntities().stream()
				.map(lineItem -> new LineItem(lineItem.getQuantity(), lineItem.getDescription(), lineItem.getUnitPrice(), lineItem.getLineItemTotal())).collect(Collectors.toList());
		invoiceResponse.setLineItems(lineItemEntities);
		log.info("Mapped InvoiceEntity to Response [{}]", invoiceResponse.toString());
		return invoiceResponse;
	}

}
