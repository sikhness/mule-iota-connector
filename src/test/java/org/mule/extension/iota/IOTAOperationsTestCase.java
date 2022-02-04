package org.mule.extension.iota;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.text.IsEmptyString;
import org.mule.extension.iota.api.types.Address;
import org.mule.extension.iota.api.types.GenerateSeed;
import org.mule.extension.iota.api.types.Message;
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

	@Test
	public void executeGenerateSeedOperation() throws Exception {
		GenerateSeed payloadValue = ((GenerateSeed) flowRunner("generateSeedFlow").run().getMessage().getPayload()
				.getValue());
		assertThat(payloadValue.getPrivateMnemonicSeed(), CoreMatchers.not(IsEmptyString.isEmptyOrNullString()));
	}

	@Test
	public void executeGenerateAddressOperation() throws Exception {
		@SuppressWarnings("unchecked")
		List<Address> payloadValue = ((List<Address>) flowRunner("generateAddressFlow").run()
				.getMessage().getPayload().getValue());
		assertThat(payloadValue.get(0).getAddress(), CoreMatchers.not(IsEmptyString.isEmptyOrNullString()));
	}
	
	@Test
	public void executeFindAddressOperation() throws Exception {
		Address payloadValue = ((Address) flowRunner("findAddressFlow").run()
				.getMessage().getPayload().getValue());
		assertThat(payloadValue.getAddressIndex(), is((long) 25));
	}
	
	@Test
	public void executeFindMessageOperation() throws Exception {
		@SuppressWarnings("unchecked")
		List<Message> payloadValue = ((List<Message>) flowRunner("findMessageFlow").run()
				.getMessage().getPayload().getValue());
		assertThat(payloadValue.get(0).getIndexationPayload().getIndex(), is("HORNET Spammer"));
	}
}
