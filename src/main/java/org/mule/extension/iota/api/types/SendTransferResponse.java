package org.mule.extension.iota.api.types;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SendTransferResponse {

	private Boolean[] successfully;
	private Transaction inputTransaction;
	private Transaction outputTransaction;
	private Transaction zeroValueTransaction;
	private Transaction remainderTransaction;

	public SendTransferResponse(org.iota.jota.dto.response.SendTransferResponse transferResponse) {
		successfully = transferResponse.getSuccessfully();

		// If the first transaction in the IOTA SendTransferResponse object is a zero
		// value transfer, then set that into zeroValueTransaction and all others as
		// null, otherwise it corresponds to the inputTransaction and the
		// zeroValueTransfer is the 3rd transaction in the IOTA SendTransferResponse as per the
		// behaviour of the Bundling logic in the IOTA client libraries
		if (transferResponse.getTransactions().get(0).getValue() == 0) {
			zeroValueTransaction = new Transaction(transferResponse.getTransactions().get(0));
		} else {
			inputTransaction = new Transaction(transferResponse.getTransactions().get(0));
			outputTransaction = new Transaction(transferResponse.getTransactions().get(1));
			zeroValueTransaction = new Transaction(transferResponse.getTransactions().get(2));
			if (transferResponse.getTransactions().size() > 3)
				remainderTransaction = new Transaction(transferResponse.getTransactions().get(3));
		}
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

	public Boolean[] getSuccessfully() {
		return successfully;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
