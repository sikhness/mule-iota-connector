package org.mule.extension.iota.api.types;

public class Address {

	private String address;
	private long accountIndex;
	private long addressIndex;
	private long balance;

	public Address(String address, long accountIndex, long addressIndex, long balance) {
		this.address = address;
		this.accountIndex = accountIndex;
		this.addressIndex = addressIndex;
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public long getAccountIndex() {
		return accountIndex;
	}

	public long getAddressIndex() {
		return addressIndex;
	}

	public long getBalance() {
		return balance;
	}

}
