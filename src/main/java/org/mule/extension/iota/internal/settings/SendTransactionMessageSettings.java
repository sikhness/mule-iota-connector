package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "send-transaction-message")
@DisplayName("Send transaction message")
public class SendTransactionMessageSettings implements SendMessageMode {
	
	@Parameter
	@DisplayName("Private hex seed")
	private String privateHexSeed;

	@Parameter
	@DisplayName("Account index")
	@Example("0")
	private long accountIndex;
	
	@Parameter
	@DisplayName("Send to address")
	private String address;
	
	@Parameter
	@DisplayName("Amount to send")
	private long amount;
	
	@Parameter
	@DisplayName("Dust allowance transaction")
	@Optional(defaultValue = "False")
	private boolean dustAllowanceTransaction;

	@ParameterGroup(name = "Indexation details")
	private SendIndexationMessageSettings indexationPayload;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}

	public long getAccountIndex() {
		return accountIndex;
	}

	public String getAddress() {
		return address;
	}

	public long getAmount() {
		return amount;
	}
	
	public boolean getDustAllowanceTransaction() {
		return dustAllowanceTransaction;
	}

	public SendIndexationMessageSettings getIndexationPayload() {
		return indexationPayload;
	}
	
}
