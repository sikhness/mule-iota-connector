package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "find-address")
public class FindAddressSettings {

	@Parameter
	@DisplayName("Private hex seed")
	private String privateHexSeed;

	@Parameter
	@DisplayName("Address to search")
	private String address;
	
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
	@Example("10")
	private long addressRangeEnd;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}
	
	public String getAddress() {
		return address;
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

}
