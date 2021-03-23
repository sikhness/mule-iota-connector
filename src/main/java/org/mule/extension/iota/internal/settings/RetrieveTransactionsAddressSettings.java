package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;

@Alias(value = "retrieve-by-address")
public class RetrieveTransactionsAddressSettings implements RetrieveTransactionsMode {

	@Parameter
	private String address;

	public String getAddress() {
		return address;
	}
}
