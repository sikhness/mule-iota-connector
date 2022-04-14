// Automatically generated by flapigen
package org.iota.client;


public final class TransactionId {
    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof TransactionId)
        equal = ((TransactionId)obj).rustEq(this);
        return equal;
    }

    public int hashCode() {
        return (int)mNativeObj;
    }

    @Override
    public String toString() {{
        return this.to_string();
    }}


    private TransactionId() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    private final boolean rustEq(TransactionId o) {
        long a0 = o.mNativeObj;
        boolean ret = do_rustEq(mNativeObj, a0);

        JNIReachabilityFence.reachabilityFence1(o);

        return ret;
    }
    private static native boolean do_rustEq(long self, long o);
    /**
     * Create a TransactionId from string
     */
    public static TransactionId fromString(String str_rep) {
        long ret = do_fromString(str_rep);
        TransactionId convRet = new TransactionId(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_fromString(String str_rep);

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
    /*package*/ TransactionId(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}