package org.mule.extension.iota.api.types;

import org.iota.client.Client;
import org.iota.client.NodeInfoWrapper;

public class RetrieveNodeInfo {

	private String nodeUrl;
	private String nodeName;
	private String nodeVersion;
	private boolean isNodeHealthy;
	private String networkId;
	private String bech32Hrp;
	private double minimumProofOfWorkScore;
	private double messagesPerSecond;
	private double referencedMessagesPerSecond;
	private double referencedRate;
	private long latestMilestoneIndex;
	private long latestMilestoneTimestamp;
	private long confirmedMilestoneIndex;
	private long pruningIndex;
	private String[] features;

	public RetrieveNodeInfo(Client iotaClient) {
		NodeInfoWrapper nodeInfo = iotaClient.getInfo();

		nodeUrl = nodeInfo.url();
		nodeName = nodeInfo.nodeInfo().name();
		nodeVersion = nodeInfo.nodeInfo().version();
		isNodeHealthy = iotaClient.getHealth();
		networkId = nodeInfo.nodeInfo().networkId();
		bech32Hrp = nodeInfo.nodeInfo().bech32Hrp();
		minimumProofOfWorkScore = nodeInfo.nodeInfo().minPowScore();
		messagesPerSecond = nodeInfo.nodeInfo().messagesPerSecond();
		referencedMessagesPerSecond = nodeInfo.nodeInfo().referencedMessagesPerSecond();
		referencedRate = nodeInfo.nodeInfo().referencedRate();
		latestMilestoneIndex = nodeInfo.nodeInfo().latestMilestoneIndex();
		latestMilestoneTimestamp = nodeInfo.nodeInfo().latestMilestoneTimestamp();
		confirmedMilestoneIndex = nodeInfo.nodeInfo().confirmedMilestoneIndex();
		pruningIndex = nodeInfo.nodeInfo().pruningIndex();
		features = nodeInfo.nodeInfo().features();
	}

	public String getNodeUrl() {
		return nodeUrl;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public String getNodeVersion() {
		return nodeVersion;
	}
	
	public boolean getIsNodeHealthy() {
		return isNodeHealthy;
	}

	public String getNetworkId() {
		return networkId;
	}

	public String getBech32Hrp() {
		return bech32Hrp;
	}

	public double getMinimumProofOfWorkScore() {
		return minimumProofOfWorkScore;
	}

	public double getMessagesPerSecond() {
		return messagesPerSecond;
	}

	public double getReferencedMessagesPerSecond() {
		return referencedMessagesPerSecond;
	}

	public double getReferencedRate() {
		return referencedRate;
	}

	public long getLatestMilestoneIndex() {
		return latestMilestoneIndex;
	}

	public long getLatestMilestoneTimestamp() {
		return latestMilestoneTimestamp;
	}

	public long getConfirmedMilestoneIndex() {
		return confirmedMilestoneIndex;
	}

	public long getPruningIndex() {
		return pruningIndex;
	}

	public String[] getFeatures() {
		return features;
	}

}
