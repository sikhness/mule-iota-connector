package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "generate-new-address")
@DisplayName("Generate new address")
public class GenerateAddressNewSettings implements GenerateAddressMode {

	@Parameter
	@DisplayName("Private hex seed")
	private String privateHexSeed;

	@Parameter
	@DisplayName("Account index")
	@Example("0")
	private long accountIndex;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}

	public long getAccountIndex() {
		return accountIndex;
	}

}
