package co.za.invoice.register.web.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "invoice")
@Getter
public class InvoiceRequest {

	@XmlElement(name = "client", required = true)
	@JsonProperty(value = "client", required = true)
	private String client;

	@XmlElement(name = "vatRate", required = true)
	@JsonProperty(value = "vatRate", required = true)
	private Long vatRate;

	@XmlElement(name = "lineItems", required = true)
	@JsonProperty(value = "lineItems", required = true)
	List<LineItem> lineItems;

	@Override
	public String toString() {
		return "InvoiceRequest{" + "client='" + client + '\'' + ", vatRate="
				+ vatRate + ", lineItems=" + lineItems + '}';
	}
}
