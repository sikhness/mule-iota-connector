package org.mule.extension.iota.internal.settings;

import org.mule.extension.iota.api.types.SecurityLevel;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class AddressSettings {

	@Parameter
	@DisplayName("Seed")
	private String seed;

	@Parameter
	@DisplayName("Security level")
	@Optional(defaultValue = "2")
	@OfValues(SecurityLevel.class)
	private String securityLevel;

	public String getSeed() {
		return seed;
	}

	public int getSecurityLevel() {
		return Integer.parseInt(securityLevel);
	}

}
