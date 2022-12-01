package com.nagarro.employee.payload.response;

public class MessageResponse {

    private String message;
    
    private  String status ;

    

    public MessageResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
