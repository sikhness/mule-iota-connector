package org.mule.extension.iota.api.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Bundle {

	public String bundleHash;
	private int length;
	private String message;
	private List<Transaction> transactions;

	public Bundle(org.iota.jota.model.Bundle bundle) {
		bundleHash = bundle.getBundleHash();
		length = bundle.getLength();

		// Remove non-printable characters as per the POSIX standard except for \n (line
		// feed), \t (tab), \r (carriage return)
		message = bundle.getMessage().replaceAll("[^\\n\\r\\t\\p{Print}]", "");

		transactions = new ArrayList<Transaction>();
		for (org.iota.jota.model.Transaction transaction : bundle.getTransactions()) {
			transactions.add(new Transaction(transaction));
		}
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
