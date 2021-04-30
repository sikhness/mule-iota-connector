package org.mule.extension.iota.api.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SendTransferResponse {
	
	private List<Transaction> transactions;
	private Boolean[] successfully;
	
	public SendTransferResponse(org.iota.jota.dto.response.SendTransferResponse transferResponse) {
		successfully = transferResponse.getSuccessfully();
		
		transactions = new ArrayList<Transaction>();
		for (org.iota.jota.model.Transaction transaction : transferResponse.getTransactions()) {
			transactions.add(new Transaction(transaction));
		}
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}
	
	public Boolean[] getSuccessfully() {
		return successfully;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
