package com.easy.url.model.response;

/**
 * Base response represent basic response e.g. request state and error message
 *
 * @author Ahmad Mahagna
 */
public class BaseResponse {


    private int requestState;
    private String errorMessage;

    public BaseResponse(int requestState,String errorMessage) {
        this.requestState = requestState;
        this.errorMessage = errorMessage;
    }


    public int getRequestState() {
        return requestState;
    }

    public void setRequestState(int requestState) {
        this.requestState = requestState;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    /**
     * Response State represent response code of request
     */
    public enum ResponseState {
        OK(200),
        ERROR(503),
        INVALID_URL(403),
        NOT_FOUND(404);

        int state;

        ResponseState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

    }
}
