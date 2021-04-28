package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class AddressBalanceSettings {

	@Parameter
	private String address;

	public String getAddress() {
		return address;
	}
}
