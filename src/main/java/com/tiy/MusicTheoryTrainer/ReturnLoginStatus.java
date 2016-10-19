package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/19/16.
 */

public class ReturnLoginStatus {

    UserStatus userStatus;

    String errorMessage = null;

    public ReturnLoginStatus() {
    }

    public ReturnLoginStatus(UserStatus userStatus, String errorMessage) {
        this.userStatus = userStatus;
        this.errorMessage = errorMessage;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
