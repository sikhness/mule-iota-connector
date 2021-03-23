package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class RetrieveTransactionsSettings {

	@Parameter
	@ParameterDsl(allowReferences = false)
	private RetrieveTransactionsMode mode;
	
	public RetrieveTransactionsMode getMode() {
		return mode;
	}
	
}
