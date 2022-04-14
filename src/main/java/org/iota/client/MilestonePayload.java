// Automatically generated by flapigen
package org.iota.client;

/**
 * A payload which defines the inclusion set of other messages in the Tangle.
 */
public final class MilestonePayload {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private MilestonePayload() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);
    /**
     * Returns the essence of a `MilestonePayload`.
     */
    public final MilestonePayloadEssence essence() {
        long ret = do_essence(mNativeObj);
        MilestonePayloadEssence convRet = new MilestonePayloadEssence(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_essence(long self);
    /**
     * Returns the signatures of a `MilestonePayload`.
     */
    public final MilestoneSignature [] signatures() {
        MilestoneSignature [] ret = do_signatures(mNativeObj);

        return ret;
    }
    private static native MilestoneSignature [] do_signatures(long self);
    /**
     * Semantically validate a `MilestonePayload`.
     * @param applicable_public_keys The public keys to use to validate
     * @param min_threshold THe minimum threshold for this to succeed (0...100)
     */
    public final void validate(java.lang.String [] applicable_public_keys, long min_threshold) {
        do_validate(mNativeObj, applicable_public_keys, min_threshold);
    }
    private static native void do_validate(long self, java.lang.String [] applicable_public_keys, long min_threshold);
    /**
     * Computes the identifier of a `MilestonePayload`.
     */
    public final String id() {
        String ret = do_id(mNativeObj);

        return ret;
    }
    private static native String do_id(long self);

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
    /*package*/ MilestonePayload(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}