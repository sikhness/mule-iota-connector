// Automatically generated by flapigen
package org.iota.client;


public final class TreasuryOutput {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private TreasuryOutput() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    public static TreasuryOutput from(long amount) {
        long ret = do_from(amount);
        TreasuryOutput convRet = new TreasuryOutput(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_from(long amount);
    /**
     * Returns the amount of a `TreasuryOutput`.
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
    /*package*/ TreasuryOutput(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}