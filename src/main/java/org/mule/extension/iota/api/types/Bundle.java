package org.mule.extension.iota.api.types;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.iota.jota.utils.TrytesConverter;

public class Bundle {

	public String bundleHash;
	private int length;
	private String message;
	private Transaction inputTransaction;
	private Transaction outputTransaction;
	private Transaction zeroValueTransaction;
	private Transaction remainderTransaction;

	public Bundle(org.iota.jota.model.Bundle bundle) {
		bundleHash = bundle.getBundleHash();
		length = bundle.getLength();

		// If the first transaction in the IOTA Bundle object is a zero value transfer,
		// then set that into zeroValueTransaction and all others as null, otherwise it
		// corresponds to the inputTransaction and the zeroValueTransfer is the 3rd
		// transaction in the IOTA Bundle as per the behaviour of the Bundling logic in
		// the IOTA client libraries
		if (bundle.getTransactions().get(0).getValue() == 0) {
			zeroValueTransaction = new Transaction(bundle.getTransactions().get(0));
		} else {
			inputTransaction = new Transaction(bundle.getTransactions().get(0));
			outputTransaction = new Transaction(bundle.getTransactions().get(1));
			zeroValueTransaction = new Transaction(bundle.getTransactions().get(2));
			if (bundle.getTransactions().size() > 3)
				remainderTransaction = new Transaction(bundle.getTransactions().get(3));
		}

		message = retrieveBundleMessage();
	}

	/**
	 * Helper method that retrieves the message attached to the first transaction in
	 * the bundle (this is the message that is set by the user). This prevents
	 * getting jumbled messages from other transactions in the bundle. The function
	 * also removes non-printable characters as per the POSIX standard except for \n
	 * (line feed), \t (tab), \r (carriage return)
	 * 
	 * @return Message of the bundle with the valid (non jumbled) message
	 */
	private String retrieveBundleMessage() {
		// Retrieve the message (via SignatureFragments) of the first transaction in the
		// bundle (either the inputTransaction if its a value transfer, or
		// zeroValueTransaction if it's a zero value transfer)
		String retrievedMessage;
		if (inputTransaction == null)
			retrievedMessage = zeroValueTransaction.getSignatureFragments();
		else
			retrievedMessage = inputTransaction.getSignatureFragments();

		// If retrieved message is of odd length add a blank Tryte (9) to make it even
		if (retrievedMessage.length() % 2 != 0) {
			retrievedMessage = retrievedMessage + "9";
		}

		// Convert Trytes to Ascii and remove non-printable characters
		return TrytesConverter.trytesToAscii(retrievedMessage).replaceAll("[^\\n\\r\\t\\p{Print}]", "");
	}

	public String getBundleHash() {
		return bundleHash;
	}

	public int getLength() {
		return length;
	}

	public String getMessage() {
		return message;
	}

	public Transaction getInputTransaction() {
		return inputTransaction;
	}

	public Transaction getOutputTransaction() {
		return outputTransaction;
	}

	public Transaction getZeroValueTransaction() {
		return zeroValueTransaction;
	}

	public Transaction getRemainderTransaction() {
		return remainderTransaction;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
