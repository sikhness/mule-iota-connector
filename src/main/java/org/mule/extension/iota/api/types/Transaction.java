package org.mule.extension.iota.api.types;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.iota.jota.error.ArgumentException;
import org.iota.jota.utils.Checksum;
import org.iota.jota.utils.TrytesConverter;

public class Transaction {

	private String hash;
	private String signatureFragments;
	private String address;
	private long value;
	private String obsoleteTag;
	private long timestamp;
	private long currentIndex;
	private long lastIndex;
	private String bundle;
	private String trunkTransaction;
	private String branchTransaction;
	private boolean isTailTransaction;
	private String nonce;
	private Boolean persistence;
	private long attachmentTimestamp;
	private String tag;
	private long attachmentTimestampLowerBound;
	private long attachmentTimestampUpperBound;

	public Transaction(org.iota.jota.model.Transaction transaction) {
		hash = transaction.getHash();
		signatureFragments = transaction.getSignatureFragments();

		// Add checksum to address
		address = Checksum.addChecksum(transaction.getAddress());

		value = transaction.getValue();
		obsoleteTag = transaction.getObsoleteTag();
		timestamp = transaction.getTimestamp();
		currentIndex = transaction.getCurrentIndex();
		lastIndex = transaction.getLastIndex();
		bundle = transaction.getBundle();
		trunkTransaction = transaction.getTrunkTransaction();
		branchTransaction = transaction.getBranchTransaction();
		isTailTransaction = transaction.isTailTransaction();
		nonce = transaction.getNonce();
		persistence = transaction.getPersistence();
		attachmentTimestamp = transaction.getAttachmentTimestamp();

		// Convert tag Trytes to Ascii by checking for and then creating an even number
		// character length. Furthermore, tag is cleaned by removing non-printable
		// characters as per the POSIX standard except for \n (line
		// feed), \t (tab), \r (carriage return)
		tag = transaction.getTag();
		if (tag.length() % 2 != 0) {
			tag = tag + "9";
		}
		tag = TrytesConverter.trytesToAscii(tag).replaceAll("[^\\n\\r\\t\\p{Print}]", "");

		attachmentTimestampLowerBound = transaction.getAttachmentTimestampLowerBound();
		attachmentTimestampUpperBound = transaction.getAttachmentTimestampUpperBound();
	}

	public String getHash() {
		return hash;
	}

	public String getSignatureFragments() {
		return signatureFragments;
	}

	public String getAddress() {
		return address;
	}

	public long getValue() {
		return value;
	}

	public String getObsoleteTag() {
		return obsoleteTag;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getCurrentIndex() {
		return currentIndex;
	}

	public long getLastIndex() {
		return lastIndex;
	}

	public String getBundle() {
		return bundle;
	}

	public String getTrunkTransaction() {
		return trunkTransaction;
	}

	public String getBranchTransaction() {
		return branchTransaction;
	}

	public boolean getIsTailTransaction() {
		return isTailTransaction;
	}

	public String getNonce() {
		return nonce;
	}

	public Boolean getPersistence() {
		return persistence;
	}

	public long getAttachmentTimestamp() {
		return attachmentTimestamp;
	}

	public String getTag() {
		return tag;
	}

	public long getAttachmentTimestampLowerBound() {
		return attachmentTimestampLowerBound;
	}

	public long getAttachmentTimestampUpperBound() {
		return attachmentTimestampUpperBound;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
