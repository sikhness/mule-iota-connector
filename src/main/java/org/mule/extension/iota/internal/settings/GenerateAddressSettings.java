package org.mule.extension.iota.internal.settings;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class GenerateAddressSettings {

	@Parameter
	@ParameterDsl(allowReferences = false)
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	@DisplayName("Generate mode")
	private GenerateAddressMode generateMode;

	public GenerateAddressMode getGenerateMode() {
		return generateMode;
	}

}
