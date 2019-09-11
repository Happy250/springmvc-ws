package net.antra.restful.mobileappws.ui.model.response;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String meesage;

    public ErrorMessage() {
    }

    public ErrorMessage(Date timestamp, String meesage) {
        this.timestamp = timestamp;
        this.meesage = meesage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMeesage() {
        return meesage;
    }

    public void setMeesage(String meesage) {
        this.meesage = meesage;
    }
}


