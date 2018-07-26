package co.za.invoice.register.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("properties")
@Getter
@Setter
public class InvoiceConfigurationProperties {

	private String vat;
}
