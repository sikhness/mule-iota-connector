# MuleSoft IOTA Connector

A MuleSoft connector enabling easy access for MuleSoft applications to interact with the IOTA tangle.

## Usage

In your MuleSoft Anypoint Studio project, add the following lines to your Maven POM file:

### Dependency
```
<dependency>
	<groupId>com.github.sikhness</groupId>
	<artifactId>mule-iota-connector</artifactId>
	<version>1.5.1</version>
	<classifier>mule-plugin</classifier>
</dependency>
```
### Repository
```
<repository>
	<id>jitpack.io</id>
	<url>https://jitpack.io</url>
</repository>
```

## Changelog
### Version 1.5.1
- Updated IOTA Client SDK to the latest production source code branch for iota.rs. Branch: [3cdaeefa8f727d77beda605c228a42e14752bebc](https://github.com/iotaledger/iota.rs/tree/3cdaeefa8f727d77beda605c228a42e14752bebc)
- Added option to enable (default) or disable Local proof of work (PoW) in IOTA connector configuration
- Added connector operation to convert private mnemonic seed to hex seed

### Version 1.5.0
- This is the the first release capable of connecting and interacting MuleSoft applications with the IOTA 1.5 (Chrysalis) protocol.

### Version 1.0.0
- This is the first pre-release capable of connecting and interacting MuleSoft applications with the IOTA 1.0 protocol.