// Automatically generated by flapigen
package org.iota.client;


public final class GetBalanceBuilderApi {

    private GetBalanceBuilderApi() {}
    /**
     * Sets the account index.
     */
    public final GetBalanceBuilderApi withAccountIndex(long account_index) {
        long ret = do_withAccountIndex(mNativeObj, account_index);
        GetBalanceBuilderApi convRet = new GetBalanceBuilderApi(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_withAccountIndex(long self, long account_index);
    /**
     * Sets the index of the address to start looking for balance.
     */
    public final GetBalanceBuilderApi withInitialAddressIndex(long initial_address_index) {
        long ret = do_withInitialAddressIndex(mNativeObj, initial_address_index);
        GetBalanceBuilderApi convRet = new GetBalanceBuilderApi(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_withInitialAddressIndex(long self, long initial_address_index);
    /**
     * Sets the gap limit to specify how many addresses will be checked each round.
     * If gap_limit amount of addresses in a row have no balance the function will return.
     */
    public final GetBalanceBuilderApi withGapLimit(long gap_limit) {
        long ret = do_withGapLimit(mNativeObj, gap_limit);
        GetBalanceBuilderApi convRet = new GetBalanceBuilderApi(InternalPointerMarker.RAW_PTR, ret);

        return convRet;
    }
    private static native long do_withGapLimit(long self, long gap_limit);
    /**
     * Consume the builder and get the API result
     */
    public final long finish() {
        long ret = do_finish(mNativeObj);

        return ret;
    }
    private static native long do_finish(long self);

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
    /*package*/ GetBalanceBuilderApi(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}