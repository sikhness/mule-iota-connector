package org.mule.extension.iota.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.extension.iota.internal.settings.AddressSettings;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.iota.jota.dto.response.GetNodeInfoResponse;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class IOTAOperations {

	/**
	 * Example of an operation that uses the configuration and a connection instance
	 * to perform some action.
	 */
	@MediaType(value = ANY, strict = false)
	@DisplayName("Retrieve node information")
	public GetNodeInfoResponse retrieveNodeInfo(@Config IOTAConfiguration configuration,
			@Connection IOTAConnection connection) {
		return IOTAFunctions.getNodeInfoResponse(connection.getClient());
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Generate seed")
	public String generateSeed() {
		return IOTAFunctions.generateSeed();
	}

	@MediaType(value = ANY, strict = false)
	@DisplayName("Generate seed address")
	public String generateAddress(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection,
			@ParameterGroup(name = "Address Settings") AddressSettings addressSettings) {
		return IOTAFunctions.generateAddress(connection.getClient(), addressSettings.getSeed(),
				Integer.parseInt(addressSettings.getSecurityLevel()));
	}
}
