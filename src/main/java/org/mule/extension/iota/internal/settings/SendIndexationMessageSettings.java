package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

@Alias(value = "send-indexation-message")
@DisplayName("Send indexation message")
public class SendIndexationMessageSettings implements SendMessageMode {

	@Parameter
	@Optional
	@DisplayName("Index")
	private String index;

	@Parameter
	@Optional
	@DisplayName("Message content")
	private String messageContent;

	public String getIndex() {
		return index;
	}

	public String getMessageContent() {
		return messageContent;
	}

}
