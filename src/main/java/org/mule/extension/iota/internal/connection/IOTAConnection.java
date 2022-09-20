package org.mule.extension.iota.internal.connection;

import org.mule.runtime.http.api.HttpConstants.Protocol;
import org.iota.client.Client;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class IOTAConnection {

	private final Protocol protocol;
	private final String host;
	private final Integer port;
	private final boolean localProofOfWork;
	private final Client iotaClient;

	public IOTAConnection(Client iotaClient, Protocol protocol, String host, Integer port, 
			boolean localProofOfWork) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.localProofOfWork = localProofOfWork;
		this.iotaClient = iotaClient;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}
	
	public boolean getLocalProofOfWork() {
		return localProofOfWork;
	}

	public Client getIotaClient() {
		return iotaClient;
	}

	public void invalidate() {
		// do something to invalidate this connection!
	}
}
