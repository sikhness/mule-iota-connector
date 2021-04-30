package org.mule.extension.iota.api.types;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GetTransferResponse {

	private Bundle[] transferBundle;

	public GetTransferResponse(org.iota.jota.dto.response.GetTransferResponse transferResponse) {
		
		List<Bundle> listTransferBundle = new ArrayList<Bundle>(); 
		
		for (org.iota.jota.model.Bundle bundle : transferResponse.getTransfers() ) {
			listTransferBundle.add(new Bundle(bundle));
		}
		
		this.transferBundle = listTransferBundle.toArray(new Bundle[listTransferBundle.size()]);
	}

	public Bundle[] getTransfers() {
		return transferBundle;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
