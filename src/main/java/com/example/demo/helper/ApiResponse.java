package com.example.demo.helper;

import java.time.LocalDateTime;

public class ApiResponse {
    boolean success;
    String messages;

    public ApiResponse(boolean success, String messages) {
        this.success = success;
        this.messages = messages;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
    public String getTimeStamp(){
        return LocalDateTime.now().toString();
    }
}
