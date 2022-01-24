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
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.extension.iota.api.IOTAFunctions;
import org.mule.extension.iota.api.NativeUtils;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpConstants.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.iota.client.Client;

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
	private Client iotaClient;

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

	/*
	 * Load a singular instance of the required native library
	 */
	static {
		loadNativeLibrary();
	}

	/*
	 * Static function that loads a singular instance of the required embedded
	 * native library based on the operating system
	 */
	private static void loadNativeLibrary() {
		String osName = System.getProperty("os.name").toLowerCase();
		String libraryPath;

		if (osName.contains("windows")) {
			// Try to load Windows library from resources folder (for testing), otherwise
			// load from embedded .dll for runtime
			try {
				libraryPath = IOTAConnection.class.getClassLoader().getResource("iota_client.dll").getPath();
				System.load(libraryPath);
			} catch (UnsatisfiedLinkError e) {
				try {
					NativeUtils.loadLibraryFromJar("/iota_client.dll");
				} catch (IOException error) {
					throw new RuntimeException(error);
				}
			}
		} else if (osName.contains("mac os x")) {
			// Try to load MacOS library from resources folder (for testing), otherwise
			// load from embedded .dylib for runtime
			try {
				libraryPath = IOTAConnection.class.getClassLoader().getResource("libiota_client.dylib").getPath();
				System.load(libraryPath);
			} catch (UnsatisfiedLinkError e) {
				try {
					NativeUtils.loadLibraryFromJar("/libiota_client.dylib");
				} catch (IOException error) {
					throw new RuntimeException(error);
				}
			}
		} else {
			// Try to load Linux library from resources folder (for testing), otherwise
			// load from embedded .so for runtime
			try {
				libraryPath = IOTAConnection.class.getClassLoader().getResource("libiota_client.so").getPath();
				System.load(libraryPath);
			} catch (UnsatisfiedLinkError e) {
				try {
					NativeUtils.loadLibraryFromJar("/libiota_client.so");
				} catch (IOException error) {
					throw new RuntimeException(error);
				}
			}
		}
	}

	@Override
	public void initialise() throws InitialisationException {
		try {
			setupClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Client setupClient() throws ConnectionException, KeyManagementException, NoSuchAlgorithmException {
		String url = properties.protocol.toString().concat("://").concat(properties.host).concat(":")
				.concat(properties.port.toString()).toLowerCase();
		iotaClient = Client.Builder().withNode(url).finish();

		return iotaClient;
	}

	@Override
	public IOTAConnection connect() throws ConnectionException {
		return new IOTAConnection(iotaClient, properties.getProtocol(), properties.getHost(), properties.getPort());
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
			IOTAFunctions.getNodeInfo(connection.getIotaClient());
			return ConnectionValidationResult.success();
		} catch (Exception e) {
			LOGGER.error("Error validating connection [" + e.getMessage(), e);
			return ConnectionValidationResult.failure("Error validating connection", e);
		}
	}
}
