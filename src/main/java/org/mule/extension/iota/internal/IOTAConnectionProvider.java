package org.mule.extension.iota.internal;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpConstants.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.iota.jota.IotaAPI;

/**
 * This class (as it's name implies) provides connection instances and the
 * funcionality to disconnect and validate those connections.
 * <p>
 * All connection related parameters (values required in order to create a
 * connection) must be declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares
 * that connections resolved by this provider will be pooled and reused. There
 * are other implementations like {@link CachedConnectionProvider} which lazily
 * creates and caches connections or simply {@link ConnectionProvider} if you
 * want a new connection each time something requires one.
 */
public class IOTAConnectionProvider implements CachedConnectionProvider<IOTAConnection> {

	private final Logger LOGGER = LoggerFactory.getLogger(IOTAConnectionProvider.class);
	private IotaAPI client;
	
	public static final class ConnectionProperties {
		@Parameter
		@DisplayName("Protocol")
		@Example("HTTP")
		@Expression(ExpressionSupport.SUPPORTED)
		private Protocol protocol;
		
		@Parameter
		@DisplayName("Host")
		@Example("localhost")
		@Expression(ExpressionSupport.SUPPORTED)
		private String host;
		
		@Parameter
		@DisplayName("Port")
		@Example("14265")
		@Expression(ExpressionSupport.SUPPORTED)
		private Integer port;
		
		public HttpConstants.Protocol getProtocol() {
			return protocol;
		}
		
		public String getHost() {
			return host;
		}
		
		public Integer getPort() {
			return port;
		}
	}
	
	@ParameterGroup(name = "Connection")
	private ConnectionProperties properties;

	private IotaAPI setupClient() throws ConnectionException {
		client = new IotaAPI.Builder().protocol(properties.getProtocol().toString())
				.host(properties.getHost())
				.port(properties.getPort())
				.build();
		
		return client;
	}
	
	@Override
	public IOTAConnection connect() throws ConnectionException {
		return new IOTAConnection(setupClient(), properties.getProtocol(), properties.getHost(), properties.getPort());
	}

	@Override
	public void disconnect(IOTAConnection connection) {
		try {
			connection.invalidate();
		} catch (Exception e) {
			LOGGER.error("Error while disconnecting [" + e.getMessage(), e);
		}
	}

	@Override
	public ConnectionValidationResult validate(IOTAConnection connection) {
		return ConnectionValidationResult.success();
	}
}
