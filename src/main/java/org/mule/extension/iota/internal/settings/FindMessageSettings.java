package org.mule.extension.iota.internal.settings;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class FindMessageSettings {

	@Parameter
	@ParameterDsl(allowReferences = false)
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	@DisplayName("Find mode")
	private FindMessageMode findMode;

	public FindMessageMode getFindMode() {
		return findMode;
	}
}
