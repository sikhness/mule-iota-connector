package org.mule.extension.iota.internal.settings;

import java.io.InputStream;

import org.mule.extension.iota.api.types.SecurityLevel;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.dsl.xml.ParameterDsl;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.values.OfValues;

public class TransferSettings {

	@Parameter
	private String seed;
	
	@Parameter
	@Optional(defaultValue = "2")
	@OfValues(SecurityLevel.class)
	private String securityLevel;
	
	@Parameter
	private String destinationAddress;
	
	@Parameter
	@ParameterDsl(allowReferences = false)
	@Expression(ExpressionSupport.NOT_SUPPORTED)
	private ValueTransferMode valueTransferMode;
	
	@Parameter
	@Optional
	@Content
	private InputStream message;
	
	@Parameter
	@Optional
	private String tag;
	
	@Parameter
	@Example("3")
	private int depth;
	
	@Parameter
	@Example("9")
	private int minimumWeightMagnitude;
	
	public String getSeed() {
		return seed;
	}

	public int getSecurityLevel() {
		return Integer.parseInt(securityLevel);
	}
	
	public String getDestinationAddress() {
		return destinationAddress;
	}
	
	public ValueTransferMode getValueTransferMode() {
		return valueTransferMode;
	}
	
	public InputStream getMessage() {
		return message;
	}
	
	public String getTag() {
		return tag;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public int getMinimumWeightMagnitude() {
		return minimumWeightMagnitude;
	}
	
}
