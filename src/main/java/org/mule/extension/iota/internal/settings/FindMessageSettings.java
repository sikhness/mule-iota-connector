package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

@Alias(value = "find-message")
public class FindMessageSettings {

	@Parameter
	@DisplayName("Message id")
	private String messageId;

	public String getMessageId() {
		return messageId;
	}
}
