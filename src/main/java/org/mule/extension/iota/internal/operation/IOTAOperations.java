package org.mule.extension.iota.internal.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.extension.iota.api.types.RetrieveNodeInfo;
import org.mule.extension.iota.internal.config.IOTAConfiguration;
import org.mule.extension.iota.internal.connection.IOTAConnection;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;

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
	@Alias("retrieve-node-info")
	public RetrieveNodeInfo retrieveNodeInfo(@Config IOTAConfiguration configuration,
			@Connection IOTAConnection connection) {
		return IOTAFunctions.getNodeInfo(connection.getIotaClient());
	}
}
