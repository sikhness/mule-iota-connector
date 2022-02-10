package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class AddressOutputSettings {

	@Parameter
	@DisplayName("Send to address")
	private String address;

	@Parameter
	@DisplayName("Amount to send")
	private long amount;

	@Parameter
	@DisplayName("Dust allowance output")
	@Optional(defaultValue = "False")
	private boolean dustAllowanceOutput;

	public String getAddress() {
		return address;
	}

	public long getAmount() {
		return amount;
	}

	public boolean getDustAllowanceOutput() {
		return dustAllowanceOutput;
	}
}
