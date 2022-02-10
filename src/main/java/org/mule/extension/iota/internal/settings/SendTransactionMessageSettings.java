package org.mule.extension.iota.internal.settings;

import java.util.List;

import org.mule.runtime.extension.api.annotation.Alias;
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
	@DisplayName("Input address range start")
	@Example("0")
	private long inputAddressRangeStart;

	@Parameter
	@DisplayName("Input address range end")
	@Example("0")
	private long inputAddressRangeEnd;

	@Parameter
	@DisplayName("Send to address outputs")
	private List<AddressOutputSettings> addressOutputs;

	@ParameterGroup(name = "Indexation details")
	private SendIndexationMessageSettings indexationPayload;

	public String getPrivateHexSeed() {
		return privateHexSeed;
	}

	public long getAccountIndex() {
		return accountIndex;
	}

	public long getInputAddressRangeStart() {
		return inputAddressRangeStart;
	}

	public long getInputAddressRangeEnd() {
		return inputAddressRangeEnd;
	}

	public List<AddressOutputSettings> getAddressOutputs() {
		return addressOutputs;
	}

	public SendIndexationMessageSettings getIndexationPayload() {
		return indexationPayload;
	}

}
