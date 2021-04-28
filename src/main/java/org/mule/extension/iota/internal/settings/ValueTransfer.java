package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class ValueTransfer implements ValueTransferMode {
	
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
	
}
