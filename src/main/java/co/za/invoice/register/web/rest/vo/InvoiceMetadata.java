package co.za.invoice.register.web.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
public class InvoiceMetadata {

	@XmlElement(name = "code")
	@JsonProperty(value = "code")
	private String code;

	@XmlElement(name = "status")
	@JsonProperty(value = "status")
	private String status;

	@XmlElement(name = "message")
	@JsonProperty(value = "message")
	private String message;

}
