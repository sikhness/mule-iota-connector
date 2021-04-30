package org.mule.extension.iota.api.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.iota.jota.utils.TrytesConverter;

public class Bundle {

	public String bundleHash;
	private int length;
	private String message;
	private List<Transaction> transactions;

	public Bundle(org.iota.jota.model.Bundle bundle) {
		bundleHash = bundle.getBundleHash();
		length = bundle.getLength();
		
		transactions = new ArrayList<Transaction>();
		for (org.iota.jota.model.Transaction transaction : bundle.getTransactions()) {
			transactions.add(new Transaction(transaction));
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
		// bundle
		String retrievedMessage = transactions.get(0).getSignatureFragments();

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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
