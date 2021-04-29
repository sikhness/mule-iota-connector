package org.mule.extension.iota.api;

import java.util.ArrayList;
import java.util.List;

import org.iota.jota.IotaAPI;
import org.iota.jota.builder.AddressRequest;
import org.iota.jota.dto.response.GetNodeInfoResponse;
import org.iota.jota.dto.response.GetTransferResponse;
import org.iota.jota.dto.response.SendTransferResponse;
import org.iota.jota.error.ArgumentException;
import org.iota.jota.model.Bundle;
import org.iota.jota.model.Input;
import org.iota.jota.model.Transfer;
import org.iota.jota.utils.Constants;
import org.iota.jota.utils.InputValidator;
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

	public static String generateAddress(IotaAPI client, String seed, int securityLevel, int index,
			boolean addSpentAddress) {
		return client.generateNewAddresses(new AddressRequest.Builder(seed, securityLevel).amount(1).checksum(true)
				.index(index).addSpendAddresses(addSpentAddress).build()).getAddresses().get(0);
	}

	public static long getBalanceByAddress(IotaAPI client, String address) {
		return client.getBalance(address);
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

	public static int getIndexFromAddress(IotaAPI client, String seed, String address, int securityLevel) {
		int start = 0;
		int unusedAddressGapThreshold = 5;
		int currentUnusedAddressGap = 0;

		// Validate the seed
		if ((!InputValidator.isValidSeed(seed))) {
			throw new IllegalStateException(Constants.INVALID_SEED_INPUT_ERROR);
		}

		// Validate the address
		if ((!InputValidator.isAddress(address))) {
			throw new IllegalStateException(Constants.INVALID_ADDRESS_INPUT_ERROR);
		}

		for (int i = start; currentUnusedAddressGap < unusedAddressGapThreshold; i++) {
			String generatedAddress = generateAddress(client, seed, securityLevel, i, true);

			// If address found, return the index
			if (generatedAddress.equals(address)) {
				return i;
			}

			// Otherwise increment gap if address is unused
			if (!client.checkWereAddressSpentFrom(generatedAddress) && client.getBalance(generatedAddress) == 0) {
				currentUnusedAddressGap++;
			}
		}

		throw new IllegalStateException("Source address not found");
	}

	public static SendTransferResponse sendTransfer(IotaAPI client, String seed, int securityLevel, int depth,
			int minimumWeightMagnitude, String remainderAddress, boolean validateSourceBalance,
			boolean validateDestinationSpentAddress, String message, String tag, int transferValue,
			String destinationAddress, String sourceAddress, boolean validateSourceSpentAddress) {

		// If validateSourceSpentAddress is true, perform a check to see if the source
		// address is spent only if it's not a zero value transfer
		if (transferValue > 0 && validateSourceSpentAddress && client.checkWereAddressSpentFrom(sourceAddress)) {
			throw new IllegalStateException("Withdrawing from a used address.");
		}

		// Create Transaction
		Transfer transaction = new Transfer(destinationAddress, transferValue);
		transaction.setMessage(TrytesConverter.asciiToTrytes(message));
		transaction.setTag(TrytesConverter.asciiToTrytes(tag));

		// Attach Transaction to transfer
		ArrayList<Transfer> transfers = new ArrayList<Transfer>();
		transfers.add(transaction);

		// If sourceAddress provided, retrieve index of address and create Input source
		// Else if sourceAddress is provided, but it is a zero value transfer,
		// invalidate the sourceAddress (not required)
		List<Input> sourceInputList = null;
		if (sourceAddress != null && transferValue > 0) {
			sourceInputList = new ArrayList<Input>();
			sourceInputList.add(new Input(sourceAddress, transferValue,
					getIndexFromAddress(client, seed, sourceAddress, securityLevel), securityLevel));
		} else if (sourceAddress != null && transferValue <= 0) {
			sourceAddress = null;
		}

		return client.sendTransfer(seed, securityLevel, depth, minimumWeightMagnitude, transfers, sourceInputList,
				remainderAddress, validateSourceBalance, validateDestinationSpentAddress, null);
	}
}
