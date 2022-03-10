package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackPackageResponse;
import africa.semicolon.sendAm.dtos.responses.UpdateTrackingInfoResponse;

public interface PackageService {
    RegisterPackageResponse registerPackage(RegisterPackageRequest packageOrder);
    PackageRepository getRepository();

    UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest trackingRequest);

    TrackPackageResponse trackPackage(int trackingNumber);

//    FindPackageResponse findPackageByTrackingId(String trackingId);
}
