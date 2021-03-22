package org.mule.extension.iota.api.types;

import java.util.Set;

import org.mule.runtime.api.value.Value;
import org.mule.runtime.extension.api.values.ValueBuilder;
import org.mule.runtime.extension.api.values.ValueProvider;

public class SecurityLevel implements ValueProvider {

	@Override
	public Set<Value> resolve() {
		return ValueBuilder.getValuesFor("1", "2", "3");
	}

}
