package africa.semicolon.sendAm.dtos.requests;
public class RegisterPackageRequest {
    private String emailAddress;
    private String whatToOrder;
    private int quantity;


    public String getWhatToOrder() {
        return whatToOrder;
    }

    public void setWhatToOrder(String whatToOrder) {
        this.whatToOrder = whatToOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmailAddress() {
        return emailAddress;

    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
