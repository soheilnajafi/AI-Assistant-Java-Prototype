package com.aiassistant.dto;

import java.util.List;

public class ChatResponse {

    private boolean success;
    private String reply;
    private String error;
    private List<String> errors;

    public ChatResponse() {
    }

    public ChatResponse(boolean success, String reply, String error, List<String> errors) {
        this.success = success;
        this.reply = reply;
        this.error = error;
        this.errors = errors;
    }

    public static ChatResponse success(String reply) {
        return new ChatResponse(true, reply, null, null);
    }

    public static ChatResponse error(String error) {
        return new ChatResponse(false, null, error, null);
    }

    public static ChatResponse validationError(String error, List<String> errors) {
        return new ChatResponse(false, null, error, errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}