package co.za.invoice.register.persistance.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "lineItem")
public class LineItemEntity {

	private static final String NAME = "line_item";
	private static final String SEQUENCE_NAME = NAME + "_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	private Long id;

	private Long quantity;
	private String description;
	private BigDecimal unitPrice;
	private BigDecimal lineItemTotal;

	public LineItemEntity() {
	}

	public LineItemEntity(final Long quantity, final String description,
			BigDecimal unitPrice) {
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
		setLineItemTotal();
	}

	public Long getQuantity() {
		return quantity;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	private void setLineItemTotal() {
		this.lineItemTotal = unitPrice.multiply(new BigDecimal(quantity)).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getLineItemTotal() {
		return lineItemTotal;
	}

	@Override
	public String toString() {
		return "LineItemEntity{" + "id=" + id + ", quantity=" + quantity
				+ ", description='" + description + '\'' + ", unitPrice="
				+ unitPrice + '}';
	}
}
