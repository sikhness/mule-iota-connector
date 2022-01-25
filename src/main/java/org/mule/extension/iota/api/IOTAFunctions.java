package org.mule.extension.iota.api;

import java.util.ArrayList;
import java.util.List;

import org.iota.client.Client;
import org.iota.client.GetAddressesBuilder;
import org.mule.extension.iota.api.types.GenerateAddress;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;

public final class IOTAFunctions {

	public static RetrieveNodeInfo getNodeInfo(Client iotaClient) {
		return new RetrieveNodeInfo(iotaClient);
	}

	public static GenerateSeed generateSeed() {
		return new GenerateSeed();
	}

	public static List<GenerateAddress> generateAddress(Client iotaClient, String publicSeed, int accountIndex,
			int addressRangeStart, int addressRangeEnd, String humanReadableAddressPrefix) {
		String[] addresses = new GetAddressesBuilder(publicSeed).withClient(iotaClient).withAccountIndex(accountIndex)
				.withRange(addressRangeStart, addressRangeEnd).withBech32Hrp(humanReadableAddressPrefix).finish();
		List<GenerateAddress> returnAddressList = new ArrayList<GenerateAddress>();

		for (String address : addresses) {
			returnAddressList.add(new GenerateAddress(address));
		}

		return returnAddressList;
	}
}
