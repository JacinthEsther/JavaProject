package africa.semicolon.sendAm.dtos.responses;

import java.util.ArrayList;
import java.util.List;

public class TrackPackageResponse {

    private List<TrackingInfo> trackingInfo = new ArrayList<>();

    public List<TrackingInfo> getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(List<TrackingInfo> trackingInfo) {
        this.trackingInfo = trackingInfo;
    }
}
