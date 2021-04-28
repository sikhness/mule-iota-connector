package org.mule.extension.iota.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.SubTypeMapping;
import org.mule.extension.iota.internal.config.IOTAConfiguration;
import org.mule.extension.iota.internal.settings.RetrieveTransactionsAddressSettings;
import org.mule.extension.iota.internal.settings.RetrieveTransactionsSeedSettings;
import org.mule.extension.iota.internal.settings.ValueTransfer;
import org.mule.extension.iota.internal.settings.ValueTransferMode;
import org.mule.extension.iota.internal.settings.ZeroValueTransfer;
import org.mule.extension.iota.internal.settings.RetrieveTransactionsMode;
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
@SubTypeMapping(baseType = RetrieveTransactionsMode.class, subTypes = { RetrieveTransactionsSeedSettings.class,
		RetrieveTransactionsAddressSettings.class })
@SubTypeMapping(baseType = ValueTransferMode.class, subTypes = { ZeroValueTransfer.class, ValueTransfer.class })
public class IOTAConnector {

}
