package org.mule.extension.iota.api;

import org.iota.jota.IotaAPI;
import org.iota.jota.builder.AddressRequest;
import org.iota.jota.dto.response.GetNodeInfoResponse;
import org.iota.jota.dto.response.GetTransferResponse;
import org.iota.jota.error.ArgumentException;
import org.iota.jota.model.Bundle;
import org.iota.jota.utils.SeedRandomGenerator;
import org.iota.jota.utils.StopWatch;

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

	public static GetTransferResponse getTransfersByAddress(IotaAPI client, String[] addresses)
			throws ArgumentException {
		StopWatch stopWatch = new StopWatch();

		Bundle[] bundles = client.bundlesFromAddresses(true, addresses);
		return GetTransferResponse.create(bundles, stopWatch.getElapsedTimeMili());
	}

	public static GetTransferResponse getTransfersBySeed(IotaAPI client, String seed, int securityLevel, int startIndex,
			int endIndex) throws ArgumentException {
		return client.getTransfers(seed, securityLevel, startIndex, endIndex, true);
	}
}
