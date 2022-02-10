package org.mule.extension.iota.api;

import java.util.ArrayList;
import java.util.List;

import org.iota.client.Client;
import org.mule.extension.iota.api.types.Address;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.Message;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;
import org.mule.extension.iota.internal.settings.AddressOutputSettings;

public final class IOTAFunctions {

	public static RetrieveNodeInfo getNodeInfo(Client iotaClient) {
		return new RetrieveNodeInfo(iotaClient);
	}

	public static GenerateSeed generateSeed() {
		return new GenerateSeed();
	}

	public static List<Address> generateAddress(Client iotaClient, String privateHexSeed, long accountIndex,
			long addressRangeStart, long numberOfAddresses, String humanReadableAddressPrefix) {

		String[] addresses;

		if (humanReadableAddressPrefix != null && !humanReadableAddressPrefix.trim().isEmpty())
			addresses = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(addressRangeStart, numberOfAddresses).withBech32Hrp(humanReadableAddressPrefix).finish();
		else
			addresses = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(addressRangeStart, numberOfAddresses).finish();

		List<Address> returnAddressList = new ArrayList<Address>();

		long addressIndexCount = addressRangeStart;
		for (String address : addresses) {
			org.iota.client.BalanceAddressResponse addressBalanceResponse = iotaClient.getAddressBalance(address);
			returnAddressList.add(new Address(address, accountIndex, addressIndexCount,
					addressBalanceResponse.balance(), addressBalanceResponse.dustAllowed()));
			addressIndexCount++;
		}

		return returnAddressList;
	}

	public static Address generateNewAddress(Client iotaClient, String privateHexSeed, long accountIndex) {
		int addressRangeIndex;
		long addressBalance = -1;
		boolean addressDustAllowed = false;
		String newAddress = new String();

		// Go through the address space to find the first address where the balance is 0
		for (addressRangeIndex = 0; addressBalance != 0; addressRangeIndex++) {
			newAddress = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(0 + addressRangeIndex, 1 + addressRangeIndex).finish()[0];
			org.iota.client.BalanceAddressResponse addressBalanceResponse = iotaClient.getAddressBalance(newAddress);
			addressBalance = addressBalanceResponse.balance();
			addressDustAllowed = addressBalanceResponse.dustAllowed();
		}

		return new Address(newAddress, accountIndex, addressRangeIndex - 1, addressBalance, addressDustAllowed);
	}

	public static Address findAddress(Client iotaClient, String privateHexSeed, String address, long accountIndex,
			long addressRangeStart, long addressRangeEnd) {

		long addressIndex = org.iota.client.Util.searchAddress(privateHexSeed, getNodeInfo(iotaClient).getBech32Hrp(),
				accountIndex, addressRangeStart, addressRangeEnd, org.iota.client.Address.try_from_bech32(address))
				.index();
		org.iota.client.BalanceAddressResponse addressBalanceResponse = iotaClient.getAddressBalance(address);

		return new Address(address, accountIndex, addressIndex, addressBalanceResponse.balance(),
				addressBalanceResponse.dustAllowed());
	}

	public static boolean isHex(String hexValue) {
		return (hexValue.matches("^[0-9a-fA-F]+$") && hexValue.length() == 64);
	}

	public static List<Message> findMessageByIndex(Client iotaClient, String index, int maxResults) {
		List<Message> messages = new ArrayList<Message>();
		org.iota.client.MessageId[] messageIds = iotaClient.getMessage().indexString(index);

		if (messageIds.length > 0)
			for (int count = 0; count < maxResults; count++)
				messages.add(IOTAFunctions.findMessage(iotaClient, messageIds[count].toString()));

		return messages;
	}

	public static Message findMessage(Client iotaClient, String messageId) {

		// Check to see if valid hexadecimal id provided
		if (isHex(messageId)) {
			org.iota.client.Message message = iotaClient.getMessage()
					.data(org.iota.client.MessageId.fromString(messageId));
			org.iota.client.MessageMetadata messageMetadata = iotaClient.getMessage()
					.metadata(org.iota.client.MessageId.fromString(messageId));
			org.iota.client.MilestoneResponse milestoneMessage;

			// Find referenced milestone message if it exists
			if (messageMetadata.referencedByMilestoneIndex() > 0) {
				milestoneMessage = iotaClient.getMilestone(messageMetadata.referencedByMilestoneIndex());

				return new Message(message, messageMetadata, milestoneMessage,
						IOTAFunctions.getNodeInfo(iotaClient).getBech32Hrp());
			}

			return new Message(message, messageMetadata, null, IOTAFunctions.getNodeInfo(iotaClient).getBech32Hrp());
		} else
			throw new IllegalArgumentException(
					"Invalid messageId specified. Ensure it is 64 characters in length with proper hexadecimal format.");
	}

	public static Message sendIndexationMessage(Client iotaClient, String index, String content,
			boolean waitForConfirmation) {

		org.iota.client.Message message = iotaClient.message().withIndexString(index).withDataString(content).finish();

		// Check to see if message to be returned after it is confirmed on the tangle
		if (waitForConfirmation)
			iotaClient.retryUntilIncluded(message.id(), 0, 0);

		return IOTAFunctions.findMessage(iotaClient, message.id().toString());
	}

	public static Message sendTransactionMessage(Client iotaClient, String privateHexSeed, long accountIndex,
			long inputAddressRangeStart, long inputAddressRangeEnd, List<AddressOutputSettings> addressOutputs,
			String index, String messageContent, boolean waitForConfirmation) {

		// Build messageBuilder with initial settings
		org.iota.client.ClientMessageBuilder messageBuilder = iotaClient.message().withSeed(privateHexSeed)
				.withAccountIndex(accountIndex).withInputRange(inputAddressRangeStart, inputAddressRangeEnd);
		org.iota.client.Message message;

		// Loop through each address output
		for (AddressOutputSettings addressOutput : addressOutputs) {
			// Check to see if output is a dust allowance output or normal and create
			// appropriate output builder
			if (addressOutput.getDustAllowanceOutput())
				messageBuilder = messageBuilder.withDustAllowanceOutput(addressOutput.getAddress(),
						addressOutput.getAmount());
			else
				messageBuilder = messageBuilder.withOutput(addressOutput.getAddress(), addressOutput.getAmount());

			// Check to see if output has an index provided
			if (!(index == null || index.isEmpty()))
				messageBuilder = messageBuilder.withIndexString(index).withDataString(messageContent);
		}

		// Send transaction message to tangle
		message = messageBuilder.finish();

		// Check to see if message to be returned after it is confirmed on the tangle
		if (waitForConfirmation)
			iotaClient.retryUntilIncluded(message.id(), 0, 0);

		return IOTAFunctions.findMessage(iotaClient, message.id().toString());
	}
}
