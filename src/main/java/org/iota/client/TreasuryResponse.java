// Automatically generated by flapigen
package org.iota.client;

/**
 * Response of GET /api/v1/treasury.
 * Returns all information about the treasury.
 */
public final class TreasuryResponse {
    @Override
    public String toString() {{
        return this.to_string();
    }}

    public boolean equals(Object obj) {
        boolean equal = false;
        if (obj instanceof TreasuryResponse)
        equal = ((TreasuryResponse)obj).rustEq(this);
        return equal;
    }

    public int hashCode() {
        return (int)mNativeObj;
    }


    private TreasuryResponse() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    private final boolean rustEq(TreasuryResponse o) {
        long a0 = o.mNativeObj;
        boolean ret = do_rustEq(mNativeObj, a0);

        JNIReachabilityFence.reachabilityFence1(o);

        return ret;
    }
    private static native boolean do_rustEq(long self, long o);

    public final long amount() {
        long ret = do_amount(mNativeObj);

        return ret;
    }
    private static native long do_amount(long self);

    public final String milestoneId() {
        String ret = do_milestoneId(mNativeObj);

        return ret;
    }
    private static native String do_milestoneId(long self);

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
    /*package*/ TreasuryResponse(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}