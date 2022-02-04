package org.mule.extension.iota.api;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.iota.client.Client;
import org.mule.extension.iota.api.types.Address;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.Message;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;

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
			long addressBalance = iotaClient.getAddressBalance(address).balance();
			returnAddressList.add(new Address(address, accountIndex, addressIndexCount, addressBalance));
			addressIndexCount++;
		}

		return returnAddressList;
	}

	public static Address generateNewAddress(Client iotaClient, String privateHexSeed, long accountIndex) {
		int addressRangeIndex;
		long addressBalance = -1;
		String newAddress = new String();

		// Go through the address space to find the first address where the balance is 0
		for (addressRangeIndex = 0; addressBalance != 0; addressRangeIndex++) {
			newAddress = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(0 + addressRangeIndex, 1 + addressRangeIndex).finish()[0];
			addressBalance = iotaClient.getAddressBalance(newAddress).balance();
		}

		return new Address(newAddress, accountIndex, addressRangeIndex - 1, addressBalance);
	}

	public static Address findAddress(Client iotaClient, String privateHexSeed, String address, long accountIndex,
			long findGapLimit) {

		// Go through seed at given accountIndex to find address. Stop when found or
		// searchGapLimit has been reached
		for (long currentGap = 0; currentGap <= findGapLimit; currentGap++) {
			String currentAddress = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(0 + currentGap, 1 + currentGap).finish()[0];

			if (currentAddress.equals(address)) {
				long balance = iotaClient.getAddressBalance(currentAddress).balance();
				return new Address(currentAddress, accountIndex, currentGap, balance);
			}
		}

		throw new NoSuchElementException(
				"Address " + address + " not found in the seed within the gap limit of " + findGapLimit);
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
}
