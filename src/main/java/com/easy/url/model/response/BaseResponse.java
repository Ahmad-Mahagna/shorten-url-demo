package com.easy.url.model.response;

/**
 * Created by amahagna on 11/24/14.
 */
public class BaseResponse {

    private int httpState;
    private String errorMessage;

    public BaseResponse(int httpState,String errorMessage) {
        this.httpState = httpState;
        this.errorMessage = errorMessage;
    }


    public int getHttpState() {
        return httpState;
    }

    public void setHttpState(int httpState) {
        this.httpState = httpState;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public enum ResponseState {
        OK(200),
        ERROR(503),
        INVALID_URL(403);

        int state;

        ResponseState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

    }
}
