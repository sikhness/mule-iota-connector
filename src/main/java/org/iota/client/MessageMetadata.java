// Automatically generated by flapigen
package org.iota.client;

/**
 * Response of GET /api/v1/messages/{message_id}/metadata.
 * Returns the metadata of a message.
 */
public final class MessageMetadata {
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof MessageMetadata)
        equal = ((MessageMetadata)obj).rustEq(this);
        return equal;
    }

    public int hashCode() {
        return (int)mNativeObj;
    }

    @Override
    public String toString() {{
        return this.to_string();
    }}


    private MessageMetadata() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    private final boolean rustEq(MessageMetadata o) {
        long a0 = o.mNativeObj;
        boolean ret = do_rustEq(mNativeObj, a0);

        JNIReachabilityFence.reachabilityFence1(o);

        return ret;
    }
    private static native boolean do_rustEq(long self, long o);
    /**
     * The message id of this message
     */
    public final String messageId() {
        String ret = do_messageId(mNativeObj);

        return ret;
    }
    private static native String do_messageId(long self);
    /**
     * The ids of the parents of this message
     */
    public final java.lang.String [] parentMessageIds() {
        java.lang.String [] ret = do_parentMessageIds(mNativeObj);

        return ret;
    }
    private static native java.lang.String [] do_parentMessageIds(long self);
    /**
     * IF this message is solid
     */
    public final boolean isSolid() {
        boolean ret = do_isSolid(mNativeObj);

        return ret;
    }
    private static native boolean do_isSolid(long self);
    /**
     * The optional milestone index if this message is referenced
     */
    public final long referencedByMilestoneIndex() {
        long ret = do_referencedByMilestoneIndex(mNativeObj);

        return ret;
    }
    private static native long do_referencedByMilestoneIndex(long self);
    /**
     * The optional milestone index this was included in
     */
    public final long milestoneIndex() {
        long ret = do_milestoneIndex(mNativeObj);

        return ret;
    }
    private static native long do_milestoneIndex(long self);
    /**
     * The ledger state
     */
    public final java.util.Optional<LedgerInclusionStateDto> ledgerInclusionState() {
        int ret = do_ledgerInclusionState(mNativeObj);
        java.util.Optional<LedgerInclusionStateDto> convRet;
        if (ret != -1) {
            convRet = java.util.Optional.of(LedgerInclusionStateDto.fromInt(ret));
        } else {
            convRet = java.util.Optional.empty();
        }

        return convRet;
    }
    private static native int do_ledgerInclusionState(long self);
    /**
     * The optional reason of conflict
     */
    public final int conflictReason() {
        int ret = do_conflictReason(mNativeObj);

        return ret;
    }
    private static native int do_conflictReason(long self);
    /**
     * Optional; if the message needs to be promoted
     */
    public final java.util.Optional<java.lang.Boolean> shouldPromote() {
        short ret = do_shouldPromote(mNativeObj);
        java.util.Optional<java.lang.Boolean> convRet;
        if (ret == -1 ) {
            convRet = java.util.Optional.empty();
        } else {
            convRet = java.util.Optional.of(java.lang.Boolean.valueOf(ret == 1 ? true : false));
        }

        return convRet;
    }
    private static native short do_shouldPromote(long self);
    /**
     * Optional; if the message needs to be reattached
     */
    public final java.util.Optional<java.lang.Boolean> shouldReattach() {
        short ret = do_shouldReattach(mNativeObj);
        java.util.Optional<java.lang.Boolean> convRet;
        if (ret == -1 ) {
            convRet = java.util.Optional.empty();
        } else {
            convRet = java.util.Optional.of(java.lang.Boolean.valueOf(ret == 1 ? true : false));
        }

        return convRet;
    }
    private static native short do_shouldReattach(long self);

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
    /*package*/ MessageMetadata(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}