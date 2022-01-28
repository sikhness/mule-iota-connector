package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

public class GenerateAddressSettings {

	@Parameter
	@DisplayName("Public seed")
	private String publicSeed;

	@Parameter
	@DisplayName("Account index")
	@Example("0")
	private int accountIndex;

	@Parameter
	@DisplayName("Address range start")
	@Example("0")
	private int addressRangeStart;

	@Parameter
	@DisplayName("Address range end")
	@Example("1")
	private int addressRangeEnd;

	@Parameter
	@DisplayName("Human readable address prefix")
	@Example("atoi")
	@Optional
	private String humanReadableAddressPrefix;

	public String getPublicSeed() {
		return publicSeed;
	}

	public int getAccountIndex() {
		return accountIndex;
	}

	public int getAddressRangeStart() {
		return addressRangeStart;
	}

	public int getAddressRangeEnd() {
		return addressRangeEnd;
	}

	public String getHumanReadableAddressPrefix() {
		return humanReadableAddressPrefix;
	}
}
