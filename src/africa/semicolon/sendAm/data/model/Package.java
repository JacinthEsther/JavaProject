package africa.semicolon.sendAm.data.model;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private int id;
    private User owner;
    private PackageDescription description;
    private final List <Status> statusList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public PackageDescription getDescription() {
        return description;
    }

    public void setDescription(PackageDescription description) {
        this.description = description;
    }

    public List<Status> getStatusList() {
        return statusList;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Package{");
        sb.append("id=").append(id);
        sb.append(", owner=").append(owner);
        sb.append(", description=").append(description);
        sb.append(", statusList=").append(statusList);
        sb.append('}');
        return sb.toString();
    }
}
