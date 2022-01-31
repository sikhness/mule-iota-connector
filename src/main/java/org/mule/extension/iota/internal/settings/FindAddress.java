package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "find-address")
public class FindAddress {

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
	@DisplayName("Find gap limit")
	@Example("10")
	private long findGapLimit;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}
	
	public String getAddress() {
		return address;
	}

	public long getAccountIndex() {
		return accountIndex;
	}
	
	public long getFindGapLimit() {
		return findGapLimit;
	}

}
