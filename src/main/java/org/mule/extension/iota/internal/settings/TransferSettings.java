package org.mule.extension.iota.internal.settings;

import java.io.InputStream;

import org.mule.extension.iota.api.types.SecurityLevel;
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
	private int transferValue;
	
	@Parameter
	@Optional(defaultValue = "True")
	private boolean validateSpentAddress;
	
	@Parameter
	@Optional(defaultValue = "True")
	private boolean validateBalances;
	
	@Parameter
	@Optional
	private String remainderAddress;
	
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
	
	public int getTransferValue() {
		return transferValue;
	}
	
	public boolean getValidateSpentAddress() {
		return validateSpentAddress;
	}
	
	public boolean getValidateBalances() {
		return validateBalances;
	}
	
	public String getRemainderAddress() {
		return remainderAddress;
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
