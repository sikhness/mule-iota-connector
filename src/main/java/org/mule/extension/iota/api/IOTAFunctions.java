package org.mule.extension.iota.api;

import org.iota.jota.IotaAPI;
import org.iota.jota.dto.response.GetNodeInfoResponse;

public final class IOTAFunctions {
	
	public static GetNodeInfoResponse getNodeInfoResponse(IotaAPI client) {
		return client.getNodeInfo();
	}
	
}
