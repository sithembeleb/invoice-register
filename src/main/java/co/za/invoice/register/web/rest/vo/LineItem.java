package co.za.invoice.register.web.rest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "lineItems")
@Getter
public class LineItem {

	@XmlElement(name = "quantity", required = true)
	@JsonProperty(value = "quantity", required = true)
	private Long quantity;

	@XmlElement(name = "description", required = true)
	@JsonProperty(value = "description", required = true)
	private String description;

	@XmlElement(name = "unitPrice", required = true)
	@JsonProperty(value = "unitPrice", required = true)
	private BigDecimal unitPrice;

	@XmlElement(name = "lineItemTotal")
	@JsonProperty(value = "lineItemTotal")
	private BigDecimal lineItemTotal;

	public LineItem(final Long quantity, final String description, final BigDecimal unitPrice, final BigDecimal lineItemTotal) {
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
		this.lineItemTotal = lineItemTotal;
	}

	public LineItem() {
	}

	@Override
	public String toString() {
		return "LineItem{" + "quantity=" + quantity + ", description='"
				+ description + '\'' + ", unitPrice=" + unitPrice
				+ ", lineItemTotal=" + lineItemTotal + '}';
	}
}
