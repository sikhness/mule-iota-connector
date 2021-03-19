package org.mule.extension.iota.api;

import org.iota.jota.IotaAPI;
import org.iota.jota.builder.AddressRequest;
import org.iota.jota.dto.response.GetNodeInfoResponse;
import org.iota.jota.utils.SeedRandomGenerator;

public final class IOTAFunctions {

	public static GetNodeInfoResponse getNodeInfoResponse(IotaAPI client) {
		return client.getNodeInfo();
	}

	public static String generateSeed() {
		return SeedRandomGenerator.generateNewSeed();
	}

	public static String generateAddress(IotaAPI client, String seed, int securityLevel) {
		return client
				.generateNewAddresses(new AddressRequest.Builder(seed, securityLevel).amount(1).checksum(true).build())
				.getAddresses().get(0);
	}
}
