package co.za.invoice.register.web.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@XmlRootElement(name = "invoice")
public class InvoiceResponse {

	@XmlElement(name = "id")
	@JsonProperty(value = "id")
	private Long id;

	@XmlElement(name = "client")
	@JsonProperty(value = "client")
	private String client;

	@XmlElement(name = "vatRate")
	@JsonProperty(value = "vatRate")
	private Long vatRate;

	@XmlElement(name = "createdDate")
	@JsonProperty(value = "createdDate")
	private Date createdDate;

	@XmlElement(name = "lineItems")
	@JsonProperty(value = "lineItems")
	private List<LineItem> lineItems;

	@XmlElement(name = "subTotal")
	@JsonProperty(value = "subTotal")
	private BigDecimal subTotal;

	@XmlElement(name = "vatAmount")
	@JsonProperty(value = "vatAmount")
	private BigDecimal vatAmount;

	@XmlElement(name = "total")
	@JsonProperty(value = "total")
	private BigDecimal total;

	@XmlElement(name = "invoiceMetadata")
	@JsonProperty(value = "invoiceMetadata")
	private InvoiceMetadata invoiceMetadata;

	public InvoiceResponse() {
	}

	@SuppressWarnings("squid:S00107")
	public InvoiceResponse(final Long id, final String client, final Long vatRate, final Date createdDate, final List<LineItem> lineItems, final BigDecimal subTotal,
			final BigDecimal vatAmount, final BigDecimal total) {
		this.id = id;
		this.client = client;
		this.vatRate = vatRate;
		this.createdDate = createdDate;
		this.lineItems = lineItems;
		this.subTotal = subTotal;
		this.vatAmount = vatAmount;
		this.total = total;

	}

	public InvoiceResponse(final String code, final String status, final String message) {
		this.invoiceMetadata = new InvoiceMetadata(code, status, message);
	}

	@Override
	public String toString() {
		return "InvoiceResponse{" + "id=" + id + ", client='" + client + '\''
				+ ", vatRate=" + vatRate + ", createdDate=" + createdDate
				+ ", lineItems=" + lineItems + ", subTotal=" + subTotal
				+ ", vatAmount=" + vatAmount + ", total=" + total
				+ ", invoiceMetadata=" + invoiceMetadata + '}';
	}
}
