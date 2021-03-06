package org.mule.extension.iota.internal.config;

import org.mule.extension.iota.internal.connection.IOTAConnectionProvider;
import org.mule.extension.iota.internal.operation.IOTAOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;

/**
 * This class represents an extension configuration, values set in this class
 * are commonly used across multiple operations since they represent something
 * core from the extension.
 */
@Operations(IOTAOperations.class)
@ConnectionProviders(IOTAConnectionProvider.class)
public class IOTAConfiguration {

}
