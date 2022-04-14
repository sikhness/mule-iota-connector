// Automatically generated by flapigen
package org.iota.client;

/**
 * Output type for deposits that enables an address to receive dust outputs. It can be consumed as an input like a
 * regular SigLockedSingleOutput.
 */
public final class SignatureLockedDustAllowanceOutput {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private SignatureLockedDustAllowanceOutput() {}

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);
    /**
     * Creates a new `SignatureLockedDustAllowanceOutput`.
     * @param address The address to set
     * @param amount The amount to set
     */
    public static SignatureLockedDustAllowanceOutput from(Address address, long amount) {
        long a0 = address.mNativeObj;
        address.mNativeObj = 0;

        long ret = do_from(a0, amount);
        SignatureLockedDustAllowanceOutput convRet = new SignatureLockedDustAllowanceOutput(InternalPointerMarker.RAW_PTR, ret);

        JNIReachabilityFence.reachabilityFence1(address);

        return convRet;
    }
    private static native long do_from(long address, long amount);
    /**
     * Returns the amount of a `SignatureLockedDustAllowanceOutput`.
     */
    public final long amount() {
        long ret = do_amount(mNativeObj);

        return ret;
    }
    private static native long do_amount(long self);
    /**
     * Returns the address of a `SignatureLockedDustAllowanceOutput`.
     */
    public final Address address() {
        long ret = do_address(mNativeObj);
        Address convRet = new Address(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_address(long self);

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
    /*package*/ SignatureLockedDustAllowanceOutput(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}