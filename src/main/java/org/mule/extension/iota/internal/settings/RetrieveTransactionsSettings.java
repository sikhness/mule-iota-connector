package org.mule.extension.iota.internal.settings;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class RetrieveTransactionsSettings {

	@Parameter
	@ParameterDsl(allowReferences = false)
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private RetrieveTransactionsMode mode;
	
	public RetrieveTransactionsMode getMode() {
		return mode;
	}
	
}
