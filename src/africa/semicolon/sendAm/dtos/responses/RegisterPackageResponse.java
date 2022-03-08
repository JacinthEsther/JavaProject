package africa.semicolon.sendAm.dtos.responses;

import africa.semicolon.sendAm.data.model.PackageDescription;
import africa.semicolon.sendAm.data.model.Status;

import java.util.Objects;

public class RegisterPackageResponse {
    private int id;
    private String emailAddress;
    private PackageDescription description;
    private Status status;


    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PackageDescription getDescription() {
        return description;
    }

    public void setDescription(PackageDescription description) {
        this.description = description;
    }

    public String  getTrackId() {
        return "SAA/0" + ""+ id;
    }

    public void setStatus(Status status) {

        this.status = status;
    }

    public Status checkStatus(String trackId) {
//        return trackId;
        if(Objects.equals(trackId, getTrackId())){
           return status;
        }
        return null;
    }
}
