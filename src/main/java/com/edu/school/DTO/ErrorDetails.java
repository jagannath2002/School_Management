package com.edu.school.DTO;

import java.util.Date;

public class ErrorDetails {
	private Integer statuscode;
    private Date timestamp;
    private String message;
    private String details;
    

    public ErrorDetails(Integer statuscode, Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.statuscode=statuscode;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


	public Integer getStatuscode() {
		return statuscode;
	}


	public void setStatuscode(Integer statuscode) {
		this.statuscode = statuscode;
	}
}
