package org.mule.extension.iota.api.types;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private String id;
	private boolean isSolid;
	private String ledgerInclusionState;
	private long referencedByMilestoneIndex;
	private long referencedMilestoneTimestamp;
	private String payloadType;
	private IndexationPayload indexationPayload;
	private MilestonePayload milestonePayload;
	private TransactionPayload transactionPayload;

	public Message(org.iota.client.Message message, org.iota.client.MessageMetadata metadata,
			org.iota.client.MilestoneResponse milestoneMessage, String humanReadableAddressPrefix) {
		id = message.id().toString();
		isSolid = metadata.isSolid();
		ledgerInclusionState = "NOT_INCLUDED";
		referencedByMilestoneIndex = metadata.referencedByMilestoneIndex();

		// Check to see if milestone is attached to get milestone specific attributes.
		// If not, it is probably still pending to be confirmed onto the tangle
		if (milestoneMessage != null) {
			referencedMilestoneTimestamp = milestoneMessage.timestamp();
			ledgerInclusionState = metadata.ledgerInclusionState().get().toString();
		}

		// Check to see if the payload is not empty and then fill in the necessary
		// payload details
		if (message.payload().isPresent()) {
			payloadType = message.payload().get().payloadType().toString();

			// Fill in payload details based on the payloadType. Unsupported types in this
			// connector will not get populated.
			if (payloadType.equals("INDEXATION"))
				indexationPayload = new IndexationPayload(message.payload().get().getAsIndexation().get());
			else if (payloadType.equals("MILESTONE"))
				milestonePayload = new MilestonePayload(message.payload().get().getAsMilestone().get());
			else if (payloadType.equals("TRANSACTION"))
				transactionPayload = new TransactionPayload(message.payload().get().getAsTransaction().get(),
						humanReadableAddressPrefix);
			
		}
	}

	public String getId() {
		return id;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public String getLedgerInclusionState() {
		return ledgerInclusionState;
	}

	public long getReferencedByMilestoneIndex() {
		return referencedByMilestoneIndex;
	}

	public long getReferencedMilestoneTimestamp() {
		return referencedMilestoneTimestamp;
	}

	public String getPayloadType() {
		return payloadType;
	}

	public IndexationPayload getIndexationPayload() {
		return indexationPayload;
	}

	public MilestonePayload getMilestonePayload() {
		return milestonePayload;
	}

	public TransactionPayload getTransactionPayload() {
		return transactionPayload;
	}

	public class IndexationPayload {
		private String index;
		private String data;

		public IndexationPayload(org.iota.client.IndexationPayload indexationPayload) {
			index = new String(indexationPayload.index());
			data = new String(indexationPayload.data());
		}

		public String getIndex() {
			return index;
		}

		public String getData() {
			return data;
		}
	}

	public class MilestonePayload {
		private long index;
		private long timestamp;

		public MilestonePayload(org.iota.client.MilestonePayload milestonePayload) {
			index = milestonePayload.essence().index();
			timestamp = milestonePayload.essence().timestamp();
		}

		public long getIndex() {
			return index;
		}

		public long getTimestamp() {
			return timestamp;
		}
	}

	public class TransactionPayload {
		private String index;
		private String data;
		private List<Output> outputs;
		//TO DO: ADD INPUTS, currently a bug does not allow for proper retrieval of inputs
		
		public TransactionPayload(org.iota.client.TransactionPayload transactionPayload,
				String humanReadableAddressPrefix) {
			// Check to see if IndexationPayload information is attached to the transaction
			if (transactionPayload.essence().getAsRegular().get().payload().isPresent()) {
				index = new String(transactionPayload.essence().getAsRegular().get().payload().get().getAsIndexation()
						.get().index());
				data = new String(transactionPayload.essence().getAsRegular().get().payload().get().getAsIndexation()
						.get().data());
			}

			// Add outputs to the payload
			outputs = new ArrayList<Output>();
			for (org.iota.client.Output o : transactionPayload.essence().getAsRegular().get().outputs())
				outputs.add(new Output(o, humanReadableAddressPrefix));
		}

		public String getIndex() {
			return index;
		}

		public String getData() {
			return data;
		}

		public List<Output> getOutputs() {
			return outputs;
		}

		public class Output {
			private String address;
			private long amount;
			private String type;

			public Output(org.iota.client.Output output, String humanReadableAddressPrefix) {
				type = output.kind().toString();
				// Check to see if SIGNATURE_LOCKED_SINGLE or SIGNATURE_LOCKED_DUST_ALLOWANCE
				if (type.equals("SIGNATURE_LOCKED_SINGLE")) {
					address = output.asSignatureLockedSingleOutput().address().to_bech32(humanReadableAddressPrefix);
					amount = output.asSignatureLockedSingleOutput().amount();
				} else if (type.equals("SIGNATURE_LOCKED_DUST_ALLOWANCE")) {
					address = output.asSignatureLockedDustAllowanceOutput().address()
							.to_bech32(humanReadableAddressPrefix);
					amount = output.asSignatureLockedDustAllowanceOutput().amount();
				}
			}

			public String getAddress() {
				return address;
			}

			public long getAmount() {
				return amount;
			}

			public String getType() {
				return type;
			}
		}
	}
}
