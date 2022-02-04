package org.mule.extension.iota.internal.settings;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;

@Alias(value = "find-message-by-index")
@DisplayName("Find message by index")
public class FindMessageByIndex implements FindMessageMode {

	@Parameter
	@DisplayName("Index")
	private String index;
	
	@Parameter
	@DisplayName("Max results")
	@Example("10")
	private int maxResults;

	public String getIndex() {
		return index;
	}
	
	public int getMaxResults() {
		return maxResults;
	}
}
