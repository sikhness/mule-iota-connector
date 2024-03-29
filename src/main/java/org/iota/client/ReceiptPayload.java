// Automatically generated by flapigen
package org.iota.client;


public final class ReceiptPayload {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private ReceiptPayload() {}
    /**
     * Creates a new `ReceiptPayload`.
     */
    public static ReceiptPayload from(long migrated_at, boolean last, MigratedFundsEntry[] funds, MessagePayload transaction) {
        long a3 = transaction.mNativeObj;
        transaction.mNativeObj = 0;

        long ret = do_from(migrated_at, last, funds, a3);
        ReceiptPayload convRet = new ReceiptPayload(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(transaction);

        return convRet;
    }
    private static native long do_from(long migrated_at, boolean last, MigratedFundsEntry[] funds, long transaction);

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);
    /**
     * Serializes the receipt payload into a json string
     */
    public final String serialize() {
        String ret = do_serialize(mNativeObj);

        return ret;
    }
    private static native String do_serialize(long self);
    /**
     * Turns a serialized receipt payload string back into its class
     */
    public static ReceiptPayload deserialize(String serialised_data) {
        long ret = do_deserialize(serialised_data);
        ReceiptPayload convRet = new ReceiptPayload(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_deserialize(String serialised_data);
    /**
     * Returns the milestone index at which the funds of a `ReceiptPayload` were migrated at in the legacy network.
     */
    public final long migratedAt() {
        long ret = do_migratedAt(mNativeObj);

        return ret;
    }
    private static native long do_migratedAt(long self);
    /**
     * Returns whether a `ReceiptPayload` is the final one for a given migrated at index.
     */
    public final boolean last() {
        boolean ret = do_last(mNativeObj);

        return ret;
    }
    private static native boolean do_last(long self);
    /**
     * The funds which were migrated with a `ReceiptPayload`.
     */
    public final MigratedFundsEntry [] funds() {
        MigratedFundsEntry [] ret = do_funds(mNativeObj);

        return ret;
    }
    private static native MigratedFundsEntry [] do_funds(long self);
    /**
     * The `TreasuryTransaction` used to fund the funds of a `ReceiptPayload`.
     */
    public final TreasuryPayload transaction() {
        long ret = do_transaction(mNativeObj);
        TreasuryPayload convRet = new TreasuryPayload(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_transaction(long self);
    /**
     * Returns the sum of all `MigratedFundsEntry` items within a `ReceiptPayload`.
     */
    public final long amount() {
        long ret = do_amount(mNativeObj);

        return ret;
    }
    private static native long do_amount(long self);

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
    /*package*/ ReceiptPayload(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}