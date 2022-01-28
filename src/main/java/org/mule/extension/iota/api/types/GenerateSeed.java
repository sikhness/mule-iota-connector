package org.mule.extension.iota.api.types;

import org.iota.client.Client;

public class GenerateSeed {

	private String privateHexSeed;
	private String privateMnemonicSeed;
	
	public GenerateSeed () {
		privateMnemonicSeed = Client.generateMnemonic();
		privateHexSeed = Client.mnemonicToHexSeed(privateMnemonicSeed);
	}
	
	public String getPrivateHexSeed() {
		return privateHexSeed;
	}
	
	public String getPrivateMnemonicSeed() {
		return privateMnemonicSeed;
	}
}
