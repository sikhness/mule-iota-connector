// Automatically generated by flapigen
package org.iota.client;

/**
 * The iota.rs client instance
 */
public final class Client {

    private Client() {}
    /**
     * Creates a new instance of the CLient builder
     */
    public static ClientBuilder Builder() {
        long ret = do_Builder();
        ClientBuilder convRet = new ClientBuilder(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_Builder();
    /**
     * GET /health endpoint
     */
    public final boolean getHealth() {
        boolean ret = do_getHealth(mNativeObj);

        return ret;
    }
    private static native boolean do_getHealth(long self);
    /**
     * GET /health endpoint for the passed node
     * @param node the node url which you want to query speicifically for, in the case of multiple nodes in a pool
     */
    public final boolean getNodeHealth(String node) {
        boolean ret = do_getNodeHealth(mNativeObj, node);

        return ret;
    }
    private static native boolean do_getNodeHealth(long self, String node);

    public final Node getNode() {
        long ret = do_getNode(mNativeObj);
        Node convRet = new Node(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getNode(long self);
    /**
     * Gets the network id of the node we're connecting to.
     */
    public final long getNetworkId() {
        long ret = do_getNetworkId(mNativeObj);

        return ret;
    }
    private static native long do_getNetworkId(long self);
    /**
     * Gets the miner to use based on the PoW setting
     */
    public final ClientMiner getPowProvider() {
        long ret = do_getPowProvider(mNativeObj);
        ClientMiner convRet = new ClientMiner(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getPowProvider(long self);
    /**
     * Gets the network related information such as network_id and min_pow_score
     * and if it's the default one, sync it first.
     */
    public final NetworkInfo getNetworkInfo() {
        long ret = do_getNetworkInfo(mNativeObj);
        NetworkInfo convRet = new NetworkInfo(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getNetworkInfo(long self);
    /**
     * GET /api/v1/info endpoint
     */
    public final NodeInfoWrapper getInfo() {
        long ret = do_getInfo(mNativeObj);
        NodeInfoWrapper convRet = new NodeInfoWrapper(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getInfo(long self);
    /**
     * GET /api/v1/peers endpoint
     */
    public final PeerDto [] getPeers() {
        PeerDto [] ret = do_getPeers(mNativeObj);

        return ret;
    }
    private static native PeerDto [] do_getPeers(long self);
    /**
     * GET /api/v1/tips endpoint
     */
    public final java.lang.String [] getTips() {
        java.lang.String [] ret = do_getTips(mNativeObj);

        return ret;
    }
    private static native java.lang.String [] do_getTips(long self);
    /**
     * GET /api/v1/outputs/{outputId} endpoint
     * Find an output by its transaction_id and corresponding output_index.
     * @param output_id The id of the output
     */
    public final OutputResponse getOutput(String output_id) {
        long ret = do_getOutput(mNativeObj, output_id);
        OutputResponse convRet = new OutputResponse(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getOutput(long self, String output_id);
    /**
     * GET /api/v1/addresses/{address} endpoint
     * Creates a new instance of the AddressBuilder
     */
    public final GetAddressBuilder getAddress() {
        long ret = do_getAddress(mNativeObj);
        GetAddressBuilder convRet = new GetAddressBuilder(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getAddress(long self);
    /**
     * Return the balance in iota for the given address; No seed needed to do this
     * since we are only checking and already know the address.
     * @param address The address we want to get the balance for
     */
    public final BalanceAddressResponse getAddressBalance(String address) {
        long ret = do_getAddressBalance(mNativeObj, address);
        BalanceAddressResponse convRet = new BalanceAddressResponse(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getAddressBalance(long self, String address);
    /**
     * since we are only checking and already know the addresses.
     * @param address The addresses we want to get the balance for
     */
    public final BalanceAddressResponse [] getAddressesBalances(java.lang.String [] addresses) {
        BalanceAddressResponse [] ret = do_getAddressesBalances(mNativeObj, addresses);

        return ret;
    }
    private static native BalanceAddressResponse [] do_getAddressesBalances(long self, java.lang.String [] addresses);
    /**
     * Find all outputs based on the requests criteria. This method will try to query multiple nodes if
     * the request amount exceeds individual node limit.
     * @param output_ids The optional output ids to check for
     * @param addresses The optional list of addresses to check for
     */
    public final OutputResponse [] findOutputs(java.lang.String [] output_ids, java.lang.String [] addresses) {
        OutputResponse [] ret = do_findOutputs(mNativeObj, output_ids, addresses);

        return ret;
    }
    private static native OutputResponse [] do_findOutputs(long self, java.lang.String [] output_ids, java.lang.String [] addresses);
    /**
     * GET /api/v1/milestones/{index} endpoint
     * Get the milestone by the given index.
     * @param index the milestone index
     */
    public final MilestoneResponse getMilestone(long index) {
        long ret = do_getMilestone(mNativeObj, index);
        MilestoneResponse convRet = new MilestoneResponse(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getMilestone(long self, long index);
    /**
     * GET /api/v1/milestones/{index}/utxo-changes endpoint
     * Gets the utxo changes by the given milestone index.
     * @param index the milestone index
     */
    public final MilestoneUtxoChangesResponse getMilestoneUtxoChanges(long index) {
        long ret = do_getMilestoneUtxoChanges(mNativeObj, index);
        MilestoneUtxoChangesResponse convRet = new MilestoneUtxoChangesResponse(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getMilestoneUtxoChanges(long self, long index);
    /**
     * GET /api/v1/receipts endpoint
     * Get all receipts.
     */
    public final ReceiptDto [] getReceipts() {
        ReceiptDto [] ret = do_getReceipts(mNativeObj);

        return ret;
    }
    private static native ReceiptDto [] do_getReceipts(long self);
    /**
     * GET /api/v1/receipts/{migratedAt} endpoint
     * Get the receipts by the given milestone index.
     * @param index the milestone index
     */
    public final ReceiptDto [] getReceiptsMigratedAt(long index) {
        ReceiptDto [] ret = do_getReceiptsMigratedAt(mNativeObj, index);

        return ret;
    }
    private static native ReceiptDto [] do_getReceiptsMigratedAt(long self, long index);
    /**
     * GET /api/v1/treasury endpoint
     * Get the treasury output.
     */
    public final TreasuryResponse getTreasury() {
        long ret = do_getTreasury(mNativeObj);
        TreasuryResponse convRet = new TreasuryResponse(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getTreasury(long self);
    /**
     * GET /api/v1/transactions/{transactionId}/included-message
     * Returns the included message of the transaction.
     * @param transaction_id the transaction id
     */
    public final Message getIncludedMessage(TransactionId transaction_id) {
        long a0 = transaction_id.mNativeObj;
        transaction_id.mNativeObj = 0;

        long ret = do_getIncludedMessage(mNativeObj, a0);
        Message convRet = new Message(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(transaction_id);

        return convRet;
    }
    private static native long do_getIncludedMessage(long self, long transaction_id);
    /**
     * POST /api/v1/messages endpoint
     * @param msg The message to post. Use `Client.message()` to create one.
     */
    public final MessageId postMessage(Message msg) {
        long a0 = msg.mNativeObj;
        msg.mNativeObj = 0;

        long ret = do_postMessage(mNativeObj, a0);
        MessageId convRet = new MessageId(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(msg);

        return convRet;
    }
    private static native long do_postMessage(long self, long msg);
    /**
     * Reattaches messages for provided message id. Messages can be reattached only if they are valid and haven't been
     * confirmed for a while.
     * @param message_id The id of the Message to reattach
     */
    public final MessageWrap reattach(MessageId message_id) {
        long a0 = message_id.mNativeObj;
        message_id.mNativeObj = 0;

        long ret = do_reattach(mNativeObj, a0);
        MessageWrap convRet = new MessageWrap(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(message_id);

        return convRet;
    }
    private static native long do_reattach(long self, long message_id);
    /**
     * Reattach a message without checking if it should be reattached
     * @param message_id The id of the Message to reattach
     */
    public final MessageWrap reattachUnchecked(MessageId message_id) {
        long a0 = message_id.mNativeObj;
        message_id.mNativeObj = 0;

        long ret = do_reattachUnchecked(mNativeObj, a0);
        MessageWrap convRet = new MessageWrap(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(message_id);

        return convRet;
    }
    private static native long do_reattachUnchecked(long self, long message_id);
    /**
     * Promotes a message. The method should validate if a promotion is necessary through get_message. If not, the
     * method should error out and should not allow unnecessary promotions.
     * @param message_id The id of the Message to promote
     */
    public final MessageWrap promote(MessageId message_id) {
        long a0 = message_id.mNativeObj;
        message_id.mNativeObj = 0;

        long ret = do_promote(mNativeObj, a0);
        MessageWrap convRet = new MessageWrap(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(message_id);

        return convRet;
    }
    private static native long do_promote(long self, long message_id);
    /**
     * Promote a message without checking if it should be promoted
     * @param message_id The id of the Message to promote
     */
    public final MessageWrap promoteUnchecked(MessageId message_id) {
        long a0 = message_id.mNativeObj;
        message_id.mNativeObj = 0;

        long ret = do_promoteUnchecked(mNativeObj, a0);
        MessageWrap convRet = new MessageWrap(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(message_id);

        return convRet;
    }
    private static native long do_promoteUnchecked(long self, long message_id);
    /**
     * Return the balance for a provided seed
     * Addresses with balance must be consecutive, so this method will return once it encounters a zero
     * balance address.
     * @param seed the seed which contains the addressses you want to check balance for
     */
    public final GetBalanceBuilderApi getBalance(String seed) {
        long ret = do_getBalance(mNativeObj, seed);
        GetBalanceBuilderApi convRet = new GetBalanceBuilderApi(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getBalance(long self, String seed);
    /**
     * A generic send function for easily sending transaction or indexation messages.
     */
    public final ClientMessageBuilder message() {
        long ret = do_message(mNativeObj);
        ClientMessageBuilder convRet = new ClientMessageBuilder(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_message(long self);
    /**
     * GET /api/v1/messages/{messageId} endpoint
     */
    public final GetMessageBuilder getMessage() {
        long ret = do_getMessage(mNativeObj);
        GetMessageBuilder convRet = new GetMessageBuilder(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getMessage(long self);
    /**
     * Return a list of addresses from the seed regardless of their validity.
     * @param the ssed that will create the addresses
     */
    public final GetAddressesBuilder getAddresses(String seed) {
        long ret = do_getAddresses(mNativeObj, seed);
        GetAddressesBuilder convRet = new GetAddressesBuilder(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_getAddresses(long self, String seed);
    /**
     * Retries (promotes or reattaches) a message for provided message id until it's included (referenced by a
     * milestone). Default interval is 5 seconds and max attempts is 40. Returns reattached messages. Set to -1 for defaults.
     * 
     * @param message_id The id of the Message to include
     * @param interval The interval in seconds to try
     * @param max_attempts The maximum attempts for retrying
     */
    public final MessageWrap [] retryUntilIncluded(MessageId message_id, long interval, long max_attempts) {
        long a0 = message_id.mNativeObj;
        message_id.mNativeObj = 0;

        MessageWrap [] ret = do_retryUntilIncluded(mNativeObj, a0, interval, max_attempts);

        JNIReachabilityFence.reachabilityFence1(message_id);

        return ret;
    }
    private static native MessageWrap [] do_retryUntilIncluded(long self, long message_id, long interval, long max_attempts);
    /**
     * Returns a handle to the MQTT topics manager.
     */
    public final MqttManager subscriber() {
        long ret = do_subscriber(mNativeObj);
        MqttManager convRet = new MqttManager(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_subscriber(long self);
    /**
     * Generates a new mnemonic.
     */
    public static native String generateMnemonic();
    /**
     * Returns a hex encoded seed for a mnemonic.
     * @param mnemonic The mmnemonic to turn into a seed
     */
    public static native String mnemonicToHexSeed(String mnemonic);
    /**
     * Function to find inputs from addresses for a provided amount (useful for offline signing)
     * @param addresses The addresses to obtain balance from
     * @param amount the amount we need to find
     */
    public final UtxoInput [] findInputs(java.lang.String [] addresses, long amount) {
        UtxoInput [] ret = do_findInputs(mNativeObj, addresses, amount);

        return ret;
    }
    private static native UtxoInput [] do_findInputs(long self, java.lang.String [] addresses, long amount);
    /**
     * Returns a parsed hex String from bech32.
     * @param bech32 The address Bech32 string
     */
    public static native String bech32ToHex(String bech32);
    /**
     * Transforms hex to bech32
     * @param hex The address Bech32 string
     * @param bech32_hrp The Bech32 hrp string
     */
    public final String hexToBech32(String hex, String bech32_hrp) {
        String ret = do_hexToBech32(mNativeObj, hex, bech32_hrp);

        return ret;
    }
    private static native String do_hexToBech32(long self, String hex, String bech32_hrp);
    /**
     * Checks if a str is a valid bech32 encoded address.
     * @param address The addresss string to check
     */
    public static native boolean isAddressValid(String address);
    /**
     * Returns a valid Address parsed from a String.
     * @param address The addresss string to parse
     */
    public static Address parseBech32Address(String address) {
        long ret = do_parseBech32Address(address);
        Address convRet = new Address(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_parseBech32Address(String address);
    /**
     * Temporarily method to check if your seed is made using the incorrect generation of the old JAVA seed input
     * @param seed The seed you used previously
     * @param account_index The account index used, is 0 when you didnt use it
     * @param address_index The address index you want to migrate
     * @param pub_addr If it's a public or internal address
     */
    public final boolean shouldMigrate(String seed, long account_index, long address_index, boolean pub_addr) {
        boolean ret = do_shouldMigrate(mNativeObj, seed, account_index, address_index, pub_addr);

        return ret;
    }
    private static native boolean do_shouldMigrate(long self, String seed, long account_index, long address_index, boolean pub_addr);
    /**
     * Temporarily method in order to migrate wrongly generated seeds from JAVA to Rust
     * Migrates the balance of the address towards the provided to_address
     * And returns the message or an error
     * @param seed The seed you used previously
     * @param account_index The account index used, is 0 when you didnt use it
     * @param address_index The address index you want to migrate
     * @param pub_addr If it's a public or internal address
     * @param to_address The address we send the balance to
     */
    public final Message migrate(String seed, long account_index, long address_index, boolean pub_addr, String to_address) {
        long ret = do_migrate(mNativeObj, seed, account_index, address_index, pub_addr, to_address);
        Message convRet = new Message(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_migrate(long self, String seed, long account_index, long address_index, boolean pub_addr, String to_address);

    public synchronized void delete() {
        if (mNativeObj != 0) {
            do_delete(mNativeObj);
            mNativeObj = 0;
       }
    }
    @Override
    protected void finalize() throws Throwable {
        try {
            delete();
        }
        finally {
             super.finalize();
        }
    }
    private static native void do_delete(long me);
    /*package*/ Client(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}