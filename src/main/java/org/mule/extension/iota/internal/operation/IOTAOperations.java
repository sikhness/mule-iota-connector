package org.mule.extension.iota.internal.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.util.ArrayList;
import java.util.List;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.extension.iota.api.types.Address;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.Message;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;
import org.mule.extension.iota.internal.config.IOTAConfiguration;
import org.mule.extension.iota.internal.connection.IOTAConnection;
import org.mule.extension.iota.internal.settings.FindAddressSettings;
import org.mule.extension.iota.internal.settings.FindMessageByIdSettings;
import org.mule.extension.iota.internal.settings.FindMessageByIndex;
import org.mule.extension.iota.internal.settings.FindMessageSettings;
import org.mule.extension.iota.internal.settings.GenerateAddressNewSettings;
import org.mule.extension.iota.internal.settings.GenerateAddressOptionsSettings;
import org.mule.extension.iota.internal.settings.GenerateAddressSettings;
import org.mule.extension.iota.internal.settings.SendIndexationMessageSettings;
import org.mule.extension.iota.internal.settings.SendMessageSettings;
import org.mule.extension.iota.internal.settings.SendTransactionMessageSettings;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class IOTAOperations {

	/**
	 * Example of an operation that uses the configuration and a connection instance
	 * to perform some action.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("Retrieve node information")
	@Alias("retrieve-node-info")
	public RetrieveNodeInfo retrieveNodeInfo(@Config IOTAConfiguration configuration,
			@Connection IOTAConnection connection) {
		return IOTAFunctions.getNodeInfo(connection.getIotaClient());
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Generate seed")
	@Alias("generate-seed")
	public GenerateSeed generateSeed(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection) {
		return IOTAFunctions.generateSeed();
	}
	
	@MediaType(value = ANY, strict = false)
	@DisplayName("Convert private mnemonic to hex seed")
	@Alias("mnemonic-to-hex-seed")
	public String mnemonicToHexSeed(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			String privateMnemonicSeed) {
		return IOTAFunctions.mnemonicToHexSeed(connection.getIotaClient(), privateMnemonicSeed);
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Generate address")
	@Alias("generate-address")
	public List<Address> generateAddress(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			@ParameterGroup(name = "Address Settings") GenerateAddressSettings addressMode) {

		if (addressMode.getGenerateMode() instanceof GenerateAddressNewSettings) {
			GenerateAddressNewSettings generateAddressNewSettings = (GenerateAddressNewSettings) addressMode
					.getGenerateMode();
			List<Address> returnList = new ArrayList<Address>();

			returnList.add(IOTAFunctions.generateNewAddress(connection.getIotaClient(),
					generateAddressNewSettings.getPrivateHexSeed(), generateAddressNewSettings.getAccountIndex()));

			return returnList;
		} else {
			GenerateAddressOptionsSettings generateAddressOptionsSettings = (GenerateAddressOptionsSettings) addressMode
					.getGenerateMode();

			return IOTAFunctions.generateAddress(connection.getIotaClient(),
					generateAddressOptionsSettings.getPrivateHexSeed(),
					generateAddressOptionsSettings.getAccountIndex(),
					generateAddressOptionsSettings.getAddressRangeStart(),
					generateAddressOptionsSettings.getNumberOfAddresses(),
					generateAddressOptionsSettings.getHumanReadableAddressPrefix());
		}
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Find address details")
	@Alias("find-address")
	public Address findAddress(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			@ParameterGroup(name = "Address Settings") FindAddressSettings findAddress) {
		return IOTAFunctions.findAddress(connection.getIotaClient(), findAddress.getPrivateHexSeed(),
				findAddress.getAddress(), findAddress.getAccountIndex(), findAddress.getAddressRangeStart(),
				findAddress.getAddressRangeEnd());
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Find message details")
	@Alias("find-message")
	public List<Message> findMessage(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			@ParameterGroup(name = "Message Settings") FindMessageSettings findMode) {

		if (findMode.getFindMode() instanceof FindMessageByIdSettings) {
			List<Message> returnList = new ArrayList<Message>();
			FindMessageByIdSettings findMessageByIdSettings = (FindMessageByIdSettings) findMode.getFindMode();

			returnList
					.add(IOTAFunctions.findMessage(connection.getIotaClient(), findMessageByIdSettings.getMessageId()));
			return returnList;
		} else if (findMode.getFindMode() instanceof FindMessageByIndex) {
			FindMessageByIndex findMessageByIndex = (FindMessageByIndex) findMode.getFindMode();

			return IOTAFunctions.findMessageByIndex(connection.getIotaClient(), findMessageByIndex.getIndex(),
					findMessageByIndex.getMaxResults());
		} else
			return null;
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Send message")
	@Alias("send-message")
	public Message sendMessage(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			@ParameterGroup(name = "Send Settings") SendMessageSettings sendMode,
			@Optional(defaultValue = "True") @DisplayName("Wait for confirmation") boolean waitForConfirmation) {

		if (sendMode.getMessageMode() instanceof SendIndexationMessageSettings) {
			SendIndexationMessageSettings sendIndexationMessageSettings = (SendIndexationMessageSettings) sendMode
					.getMessageMode();
			return IOTAFunctions.sendIndexationMessage(connection.getIotaClient(),
					sendIndexationMessageSettings.getIndex(), sendIndexationMessageSettings.getMessageContent(),
					waitForConfirmation);
		} else if (sendMode.getMessageMode() instanceof SendTransactionMessageSettings) {
			SendTransactionMessageSettings sendTransactionMessageSettings = (SendTransactionMessageSettings) sendMode
					.getMessageMode();
			return IOTAFunctions.sendTransactionMessage(connection.getIotaClient(),
					sendTransactionMessageSettings.getPrivateHexSeed(),
					sendTransactionMessageSettings.getAccountIndex(),
					sendTransactionMessageSettings.getInputAddressRangeStart(),
					sendTransactionMessageSettings.getInputAddressRangeEnd(),
					sendTransactionMessageSettings.getAddressOutputs(),
					sendTransactionMessageSettings.getIndexationPayload().getIndex(),
					sendTransactionMessageSettings.getIndexationPayload().getMessageContent(), waitForConfirmation);
		} else
			return null;
	}
}
