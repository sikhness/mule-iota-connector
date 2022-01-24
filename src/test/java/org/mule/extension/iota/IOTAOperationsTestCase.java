package org.mule.extension.iota;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.mule.extension.iota.api.types.RetrieveNodeInfo;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class IOTAOperationsTestCase extends MuleArtifactFunctionalTestCase {

	/**
	 * Specifies the mule config xml with the flows that are going to be executed in
	 * the tests, this file lives in the test resources.
	 */
	@Override
	protected String getConfigFile() {
		return "test-mule-config.xml";
	}

	@Test
	public void executeRetrieveInfoOperation() throws Exception {
		RetrieveNodeInfo payloadValue = ((RetrieveNodeInfo) flowRunner("retrieveInfoFlow").run().getMessage()
				.getPayload().getValue());
		assertThat(payloadValue.getNodeName(), is("HORNET"));
	}
}
