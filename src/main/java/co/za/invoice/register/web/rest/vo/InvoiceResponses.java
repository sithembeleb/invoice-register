package co.za.invoice.register.web.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Setter
@XmlRootElement(name = "invoices")
public class InvoiceResponses {

	@XmlElement(name = "invoice")
	@JsonProperty(value = "invoice")
	private List<InvoiceResponse> invoice;
}
