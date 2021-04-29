package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

public class ValueTransfer implements ValueTransferMode {
	
	@Parameter
	private String sourceAddress;
	
	@Parameter
	private int transferValue;
	
	@Parameter
	@Optional(defaultValue = "True")
	private boolean validateSourceBalance;
	
	@Parameter
	@Optional(defaultValue = "True")
	private boolean validateSourceSpentAddress;
	
	@Parameter
	@Optional(defaultValue = "True")
	private boolean validateDestinationSpentAddress;
	
	@Parameter
	@Optional
	private String remainderAddress;
	
	public String getSourceAddress() {
		return sourceAddress;
	}
	
	public int getTransferValue() {
		return transferValue;
	}
	
	public boolean getValidateSourceBalance() {
		return validateSourceBalance;
	}
	
	public boolean getValidateSourceSpentAddress() {
		return validateSourceSpentAddress;
	}
	
	public boolean getValidateDestinationSpentAddress() {
		return validateDestinationSpentAddress;
	}
	
	public String getRemainderAddress() {
		return remainderAddress;
	}
	
}
