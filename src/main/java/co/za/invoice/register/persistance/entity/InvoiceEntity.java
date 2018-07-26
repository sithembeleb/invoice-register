package co.za.invoice.register.persistance.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {

	private static final String NAME = "invoice";
	private static final String SEQUENCE_NAME = NAME + "_seq";

	@Id
	@GeneratedValue(generator = SEQUENCE_NAME, strategy = SEQUENCE)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
	private Long id;

	private String client;
	private Long vatRate;
	private BigDecimal subTotal;
	private BigDecimal vatAmount;
	private BigDecimal total;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<LineItemEntity> lineItemEntities;

	public InvoiceEntity() {
	}

	public InvoiceEntity(final String client, final Long vatRate,
			List<LineItemEntity> lineItemEntities) {
		this.client = client;
		this.vatRate = vatRate;
		this.lineItemEntities = lineItemEntities;
		setSubTotal();
		setVatAmount();
		setTotal();
	}

	public Long getId() {
		return id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(final String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(final Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	private void setTotal() {
		this.total = getVatAmount().add(getSubTotal());
	}

	public BigDecimal getTotal() {
		return total;
	}

	private void setVatAmount() {
		this.vatAmount = getSubTotal().multiply(new BigDecimal(getVatRate()).movePointLeft(2)).setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVatAmount() {
		return vatAmount;
	}

	private void setSubTotal() {
		this.subTotal = this.lineItemEntities.isEmpty() ? BigDecimal.ZERO
				: getLineItemEntities().stream().map(LineItemEntity::getLineItemTotal).reduce(BigDecimal::add).get().setScale(2, RoundingMode.HALF_UP);
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<LineItemEntity> getLineItemEntities() {
		return lineItemEntities;
	}

	public void setLineItemEntities(final List<LineItemEntity> lineItemEntities) {
		this.lineItemEntities = lineItemEntities;
	}

	@Override
	public String toString() {
		return "InvoiceEntity{" + "id=" + id + ", client='" + client + '\''
				+ ", vatRate=" + vatRate + ", createdDate=" + createdDate
				+ ", lineItemEntities=" + lineItemEntities + '}';
	}
}
