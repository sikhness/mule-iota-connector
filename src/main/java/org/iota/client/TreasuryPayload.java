// Automatically generated by flapigen
package org.iota.client;


public final class TreasuryPayload {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    public TreasuryPayload(TreasuryInput input, TreasuryOutput output) {
        long a0 = input.mNativeObj;
        input.mNativeObj = 0;

        long a1 = output.mNativeObj;
        output.mNativeObj = 0;

        mNativeObj = init(a0, a1);        java.lang.ref.Reference.reachabilityFence(input);
        java.lang.ref.Reference.reachabilityFence(output);
    }
    private static native long init(long input, long output);

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    public final TreasuryOutput output() {
        long ret = do_output(mNativeObj);
        TreasuryOutput convRet = new TreasuryOutput(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_output(long self);

    public final TreasuryInput input() {
        long ret = do_input(mNativeObj);
        TreasuryInput convRet = new TreasuryInput(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_input(long self);

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
    /*package*/ TreasuryPayload(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}