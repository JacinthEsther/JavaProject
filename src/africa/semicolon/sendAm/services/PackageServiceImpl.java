package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.model.Package;
import africa.semicolon.sendAm.data.model.PackageDescription;
import africa.semicolon.sendAm.data.model.Status;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImpl;
import africa.semicolon.sendAm.data.model.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.*;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class PackageServiceImpl implements PackageService{
    private PackageRepository packageRepository = new PackageRepositoryImpl();
    private int id = 0;

    @Override
    public RegisterPackageResponse registerPackage(RegisterPackageRequest packageOrder) {
        Package packageToBeAdded = new Package();
        PackageDescription order1 = new PackageDescription();
        Status packageStatus = new Status();
        User user = new User();


        order1.setName(packageOrder.getWhatToOrder());
        order1.setWeightInGrammes(packageOrder.getQuantity());

        packageStatus.setStatus("Created");
        packageToBeAdded.getStatusList().add(packageStatus);

        user.setEmail(packageOrder.getEmailAddress());
        packageToBeAdded.setOwner(user);
        packageToBeAdded.setId(generateId());
        packageToBeAdded.setDescription(order1);

        Package savedPackage = packageRepository.save(packageToBeAdded);
        return getRegisterPackageResponse(packageToBeAdded, savedPackage);
    }

    private RegisterPackageResponse getRegisterPackageResponse(Package order, Package savedPackage) {

        RegisterPackageResponse packageOrder1 = new RegisterPackageResponse();
        packageOrder1.setEmailAddress(savedPackage.getOwner().getEmail());
        packageOrder1.setId(savedPackage.getId());
        packageOrder1.setDescription(savedPackage.getDescription());

        return packageOrder1;
    }

    private int generateId() {
        id = id + 1;
        return id;

    }

    @Override
    public PackageRepository getRepository() {
        return packageRepository;
    }

    @Override
    public UpdateTrackingInfoResponse updateTrackingInfo(UpdateTrackingInfoRequest trackingRequest) {

        Package foundPackage = packageRepository.findById(trackingRequest.getTrackingNumber());

        Status status = new Status();
        status.setStatus(trackingRequest.getTrackingInfo());

        foundPackage.getStatusList().add(status);

        packageRepository.save(foundPackage);

        return null;
    }

    @Override
    public TrackPackageResponse trackPackage(int trackingNumber) {
        Package savedPackage = packageRepository.findById(trackingNumber);

        List<Status> statusList = savedPackage.getStatusList();

        TrackPackageResponse response = new TrackPackageResponse();

        for (Status status : statusList) {
            TrackingInfo info = new TrackingInfo();
            info.setInformation(status.getStatus());
            info.setDateTime(DateTimeFormatter.ofPattern("E dd/MM/yyyy hh:mm a")
                    .format(status.getDateTime()));
            response.getTrackingInfo().add(info);
        }

        return response;
    }

//    @Override
//    public FindPackageResponse findPackageByTrackingId(String trackingId) {
//        return null;
//    }
}
