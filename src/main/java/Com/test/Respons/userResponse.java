package Com.test.Respons;


import Com.test.Model.user;

public class userResponse {


    public  String  status;

    public user user;

    public  String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Com.test.Model.user getUser() {
        return user;
    }

    public void setUser(Com.test.Model.user user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public userResponse(String status, Com.test.Model.user user, String message) {
        this.status = status;
        this.user = user;
        this.message = message;
    }

    public userResponse() {
    }
}
