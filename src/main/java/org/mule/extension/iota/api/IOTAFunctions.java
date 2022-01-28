package org.mule.extension.iota.api;

import java.util.ArrayList;
import java.util.List;

import org.iota.client.Client;
import org.iota.client.GetAddressesBuilder;
import org.mule.extension.iota.api.types.Address;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;

public final class IOTAFunctions {

	public static RetrieveNodeInfo getNodeInfo(Client iotaClient) {
		return new RetrieveNodeInfo(iotaClient);
	}

	public static GenerateSeed generateSeed() {
		return new GenerateSeed();
	}

	public static List<Address> generateAddress(Client iotaClient, String privateHexSeed, long accountIndex,
			long addressRangeStart, long addressRangeEnd, String humanReadableAddressPrefix) {

		String[] addresses;

		if (humanReadableAddressPrefix != null && !humanReadableAddressPrefix.trim().isEmpty())
			addresses = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(addressRangeStart, addressRangeEnd).withBech32Hrp(humanReadableAddressPrefix).finish();
		else
			addresses = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(addressRangeStart, addressRangeEnd).finish();

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
		for (addressRangeIndex = 1; addressBalance != 0; addressRangeIndex++) {
			newAddress = iotaClient.getAddresses(privateHexSeed).withAccountIndex(accountIndex)
					.withRange(addressRangeIndex - 1, addressRangeIndex).finish()[0];
			addressBalance = iotaClient.getAddressBalance(newAddress).balance();
		}

		return new Address(newAddress, accountIndex, addressRangeIndex - 2, addressBalance);
	}
}
