package org.mule.extension.iota.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.extension.iota.internal.config.IOTAConfiguration;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;

/**
 * This is the main class of an extension, is the entry point from which
 * configurations, connection providers, operations and sources are going to be
 * declared.
 */
@Xml(prefix = "iota")
@Extension(name = "IOTA")
@Configurations(IOTAConfiguration.class)
public class IOTAConnector {

}
