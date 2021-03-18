package org.mule.extension.iota.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.iota.jota.dto.response.GetNodeInfoResponse;

/**
 * This class is a container for operations, every public method in this class
 * will be taken as an extension operation.
 */
public class IOTAOperations {

	@MediaType(value = ANY, strict = false)
	@DisplayName("Retrive Node Information")
	public GetNodeInfoResponse retrieveNodeInfo(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection) {
		return IOTAFunctions.getNodeInfoResponse(connection.getClient());
	}
	
	/**
	 * Example of an operation that uses the configuration and a connection instance
	 * to perform some action.
	 */
	@MediaType(value = ANY, strict = false)
	public String retrieveInfo(@Config IOTAConfiguration configuration, @Connection IOTAConnection connection) {
		return "Using Configuration";
	}

	/**
	 * Example of a simple operation that receives a string parameter and returns a
	 * new string message that will be set on the payload.
	 */
	@MediaType(value = ANY, strict = false)
	public String sayHi(String person) {
		return buildHelloMessage(person);
	}

	/**
	 * Private Methods are not exposed as operations
	 */
	private String buildHelloMessage(String person) {
		return "Hello " + person + "!!!";
	}
}
