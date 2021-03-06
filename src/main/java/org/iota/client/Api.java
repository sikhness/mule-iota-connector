// Automatically generated by flapigen
package org.iota.client;


public enum Api {
        /**
     * `get_health` API
     */
    GET_HEALTH(0),
        /**
     * `get_info`API
     */
    GET_INFO(1),
        /**
     * `get_peers`API
     */
    GET_PEERS(2),
        /**
     * `get_tips` API
     */
    GET_TIPS(3),
        /**
     * `post_message` API
     */
    POST_MESSAGE(4),
        /**
     * `post_message` API with remote pow
     */
    POST_MESSAGE_WITH_REMOTE_POW(5),
        /**
     * `get_output` API
     */
    GET_OUTPUT(6),
        /**
     * `get_milestone` API
     */
    GET_MILESTONE(7),
        /**
     * `get_message` API
     */
    GET_MESSAGE(8),
        /**
     * `get_balance` API
     */
    GET_BALANCE(9);

    private final int value;
    Api(int value) {
        this.value = value;
    }
    public final int getValue() { return value; }
    /*package*/ static Api fromInt(int x) {
        switch (x) {
            case 0: return GET_HEALTH;
            case 1: return GET_INFO;
            case 2: return GET_PEERS;
            case 3: return GET_TIPS;
            case 4: return POST_MESSAGE;
            case 5: return POST_MESSAGE_WITH_REMOTE_POW;
            case 6: return GET_OUTPUT;
            case 7: return GET_MILESTONE;
            case 8: return GET_MESSAGE;
            case 9: return GET_BALANCE;
            default: throw new Error("Invalid value for enum Api: " + x);
        }
    }
}
