package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "generate-address-with-options")
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
	@DisplayName("Number of addresses to generate")
	@Example("1")
	private long numberOfAddresses;

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

	public long getNumberOfAddresses() {
		return numberOfAddresses;
	}

	public String getHumanReadableAddressPrefix() {
		return humanReadableAddressPrefix;
	}
}
