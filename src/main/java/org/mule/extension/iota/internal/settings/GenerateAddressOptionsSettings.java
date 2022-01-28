package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "generate-address-options")
@DisplayName("Generate addresses with options")
public class GenerateAddressOptionsSettings implements GenerateAddressMode {

	@Parameter
	@DisplayName("Private hex seed")
	private String privateHexSeed;

	@Parameter
	@DisplayName("Account index")
	@Example("0")
	private long accountIndex;

	@Parameter
	@DisplayName("Address range start")
	@Example("0")
	private long addressRangeStart;

	@Parameter
	@DisplayName("Address range end")
	@Example("1")
	private long addressRangeEnd;

	@Parameter
	@DisplayName("Human readable address prefix")
	@Example("atoi")
	@Optional
	private String humanReadableAddressPrefix;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}

	public long getAccountIndex() {
		return accountIndex;
	}

	public long getAddressRangeStart() {
		return addressRangeStart;
	}

	public long getAddressRangeEnd() {
		return addressRangeEnd;
	}

	public String getHumanReadableAddressPrefix() {
		return humanReadableAddressPrefix;
	}
}
