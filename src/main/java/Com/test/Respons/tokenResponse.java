package Com.test.Respons;

public class tokenResponse {

    public  String  status;

    public  String token;

    public  String name;

    public  String  message;




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public tokenResponse(String status, String token, String name, String message) {
        this.status = status;
        this.token = token;
        this.name = name;
        this.message = message;
    }

    public tokenResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
