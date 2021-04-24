package org.mule.extension.iota.internal.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.lifecycle.Initialisable;
import org.mule.runtime.api.lifecycle.InitialisationException;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.api.tls.TlsContextFactory;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpConstants.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mule.runtime.api.meta.ExpressionSupport.NOT_SUPPORTED;
import static org.mule.runtime.core.api.lifecycle.LifecycleUtils.initialiseIfNeeded;
import static org.mule.runtime.extension.api.annotation.param.display.Placement.SECURITY_TAB;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

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
public class IOTAConnectionProvider implements CachedConnectionProvider<IOTAConnection>, Initialisable {

	private final Logger LOGGER = LoggerFactory.getLogger(IOTAConnectionProvider.class);
	private IotaAPI client;

	public static final class ConnectionProperties {
		@Parameter
		@DisplayName("Protocol")
		@Optional(defaultValue = "HTTP")
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

	/**
	 * Reference to a TLS config element. This will enable HTTPS for this config.
	 */
	@Parameter
	@Optional
	@Expression(NOT_SUPPORTED)
	@DisplayName("TLS Configuration")
	@Placement(tab = SECURITY_TAB)
	private TlsContextFactory tlsContext;

	public TlsContextFactory getTlsContext() {
		return tlsContext;
	}

	@Override
	public void initialise() throws InitialisationException {
		if (tlsContext != null) {
			initialiseIfNeeded(tlsContext);
		}

		try {
			setupClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private IotaAPI setupClient() throws ConnectionException, KeyManagementException, NoSuchAlgorithmException {
		if (tlsContext != null) {
			client = new IotaAPI.Builder().protocol(properties.getProtocol().toString())
					.host(properties.getHost(), false).port(properties.getPort())
					.sslSocketFactory(tlsContext.createSslContext().getSocketFactory()).build();
		} else {
			client = new IotaAPI.Builder().protocol(properties.getProtocol().toString())
					.host(properties.getHost(), false).port(properties.getPort()).build();
		}

		return client;
	}

	@Override
	public IOTAConnection connect() throws ConnectionException {
		return new IOTAConnection(client, properties.getProtocol(), properties.getHost(), properties.getPort(),
				tlsContext);
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
		try {
			IOTAFunctions.getNodeInfoResponse(connection.getClient());
			return ConnectionValidationResult.success();
		} catch (Exception e) {
			LOGGER.error("Error validating connection [" + e.getMessage(), e);
			return ConnectionValidationResult.failure("Error validating connection", e);
		}
	}
}
