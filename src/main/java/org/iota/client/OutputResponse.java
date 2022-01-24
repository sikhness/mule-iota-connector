// Automatically generated by flapigen
package org.iota.client;


public final class OutputResponse {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private OutputResponse() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    public final String messageId() {
        String ret = do_messageId(mNativeObj);

        return ret;
    }
    private static native String do_messageId(long self);

    public final String transactionId() {
        String ret = do_transactionId(mNativeObj);

        return ret;
    }
    private static native String do_transactionId(long self);

    public final int outputIndex() {
        int ret = do_outputIndex(mNativeObj);

        return ret;
    }
    private static native int do_outputIndex(long self);

    public final boolean isSpent() {
        boolean ret = do_isSpent(mNativeObj);

        return ret;
    }
    private static native boolean do_isSpent(long self);

    public final OutputDto output() {
        long ret = do_output(mNativeObj);
        OutputDto convRet = new OutputDto(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_output(long self);

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
    /*package*/ OutputResponse(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}