package org.mule.extension.iota.internal.connection;

import org.mule.runtime.api.tls.TlsContextFactory;
import org.mule.runtime.http.api.HttpConstants.Protocol;
import org.iota.jota.IotaAPI;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public final class IOTAConnection {

	private final Protocol protocol;
	private final String host;
	private final Integer port;
	private final IotaAPI client;
	private final TlsContextFactory tlsContext;

	public IOTAConnection(IotaAPI client, Protocol protocol, String host, Integer port, TlsContextFactory tlsContext) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.client = client; 
		this.tlsContext = tlsContext;
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
	
	public IotaAPI getClient() {
		return client;
	}
	
	public TlsContextFactory getTlsContext() {
		return tlsContext;
	}

	public void invalidate() {
		// do something to invalidate this connection!
	}
}
