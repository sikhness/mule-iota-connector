package org.mule.extension.iota.api;

import java.util.ArrayList;

import org.iota.jota.IotaAPI;
import org.iota.jota.builder.AddressRequest;
import org.iota.jota.dto.response.GetNodeInfoResponse;
import org.iota.jota.dto.response.GetTransferResponse;
import org.iota.jota.dto.response.SendTransferResponse;
import org.iota.jota.error.ArgumentException;
import org.iota.jota.model.Bundle;
import org.iota.jota.model.Transfer;
import org.iota.jota.utils.SeedRandomGenerator;
import org.iota.jota.utils.StopWatch;
import org.iota.jota.utils.TrytesConverter;

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

	public static SendTransferResponse sendTransfer(IotaAPI client, String seed, int securityLevel, int depth,
			int minimumWeightMagnitude, String remainderAddress, boolean validateBalances, boolean validateSpentAddress,
			String message, String tag, int transferValue, String destinationAddress) {

		Transfer transaction = new Transfer(destinationAddress, transferValue);
		transaction.setMessage(TrytesConverter.asciiToTrytes(message));
		transaction.setTag(TrytesConverter.asciiToTrytes(tag));
		
		ArrayList<Transfer> transfers = new ArrayList<Transfer>();
		transfers.add(transaction);

		return client.sendTransfer(seed, securityLevel, depth, minimumWeightMagnitude,
				transfers, null, remainderAddress, validateBalances, validateSpentAddress, null);
	}
}
