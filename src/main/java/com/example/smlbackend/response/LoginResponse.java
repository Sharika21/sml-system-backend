package com.example.smlbackend.response;

public class LoginResponse {
    String message;
    Boolean status;

//    public LoginResponse(String message, Boolean status) {
//        this.message = message;
//        this.status = status;
//    }
public LoginResponse(String message) {
    this.message = message;
}

//    public LoginResponse(String message) {
//        this.message = message;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean isFail() {
        return false;
    }

    public boolean isSuccess() {
        return true;
    }

    public boolean isFound() {
        return false;
    }
}
