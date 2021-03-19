package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class AddressSettings {
	
	private enum SecurityLevel {
		ONE(1), TWO(2), THREE(3);
		
		private int value;
		
		private SecurityLevel(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	@Parameter
	@DisplayName("Seed")
	private String seed;

	@Parameter
	@DisplayName("Security level")
	@Optional(defaultValue = "TWO")
	private SecurityLevel securityLevel;
	
	public String getSeed() {
		return seed;
	}
	
	public int getSecurityLevel() {
		return securityLevel.getValue();
	}
	
}
