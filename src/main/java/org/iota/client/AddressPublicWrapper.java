// Automatically generated by flapigen
package org.iota.client;


public final class AddressPublicWrapper {
    @Override
    public String toString() {{
        return this.to_string();
    }}


    private AddressPublicWrapper() {}

    private final boolean rustEq(AddressPublicWrapper o) {
        long a0 = o.mNativeObj;
        boolean ret = do_rustEq(mNativeObj, a0);
        java.lang.ref.Reference.reachabilityFence(o);

        return ret;
    }
    private static native boolean do_rustEq(long self, long o);

    private final String to_string() {
        String ret = do_to_string(mNativeObj);

        return ret;
    }
    private static native String do_to_string(long self);

    public final boolean isPublic() {
        boolean ret = do_isPublic(mNativeObj);

        return ret;
    }
    private static native boolean do_isPublic(long self);

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
    /*package*/ AddressPublicWrapper(InternalPointerMarker marker, long ptr) {
        assert marker == InternalPointerMarker.RAW_PTR;
        this.mNativeObj = ptr;
    }
    /*package*/ long mNativeObj;
}