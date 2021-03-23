package org.mule.extension.iota.internal.settings;

import org.mule.extension.iota.api.types.SecurityLevel;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.values.OfValues;

@Alias(value = "retrieve-by-seed")
public class RetrieveTransactionsSeedSettings implements RetrieveTransactionsMode {

	@Parameter
	private String seed;
	
	@Parameter
	@DisplayName("Security level")
	@Optional(defaultValue = "2")
	@OfValues(SecurityLevel.class)
	private String securityLevel;
	
	@Parameter
	@Example("0")
	private int startIndex;
	
	@Parameter
	@Example("10")
	private int endIndex;

	public String getSeed() {
		return seed;
	}
	
	public int getSecurityLevel() {
		return Integer.parseInt(securityLevel);
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public int getEndIndex() {
		return endIndex;
	}
}
