package com.employee.crud.app.payload.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserSignupResponse implements Serializable {
    private Boolean success;
    private String message;
    private Object data;

    public UserSignupResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

