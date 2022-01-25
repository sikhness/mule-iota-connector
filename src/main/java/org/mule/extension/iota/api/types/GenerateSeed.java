package org.mule.extension.iota.api.types;

import org.iota.client.Client;

public class GenerateSeed {

	private String privateSeed;
	private String privateMnemonicSeed;
	private String publicSeed;
	
	public GenerateSeed () {
		privateMnemonicSeed = Client.generateMnemonic();
		privateSeed = Client.mnemonicToHexSeed(privateMnemonicSeed);
		publicSeed = privateSeed.substring(64);
	}
	
	public String getPrivateSeed() {
		return privateSeed;
	}
	
	public String getPrivateMnemonicSeed() {
		return privateMnemonicSeed;
	}
	
	public String getPublicSeed() {
		return publicSeed;
	}
}
