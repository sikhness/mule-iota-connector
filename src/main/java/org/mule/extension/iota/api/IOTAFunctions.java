package org.mule.extension.iota.api;

import org.iota.client.Client;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;

public final class IOTAFunctions {

	public static RetrieveNodeInfo getNodeInfo(Client iotaClient) {
		return new RetrieveNodeInfo(iotaClient);
	}
}
