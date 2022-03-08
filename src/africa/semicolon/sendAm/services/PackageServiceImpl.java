package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.model.Package;
import africa.semicolon.sendAm.data.model.PackageDescription;
import africa.semicolon.sendAm.data.model.Status;
import africa.semicolon.sendAm.data.repositories.PackageRepositoryImpl;
import africa.semicolon.sendAm.data.model.User;
import africa.semicolon.sendAm.data.repositories.PackageRepository;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.FindPackageResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageResponse;

import java.time.LocalDateTime;
import java.util.List;


public class PackageServiceImpl implements PackageService{
    private PackageRepository packageRepository = new PackageRepositoryImpl();
    private int id = 0;

    @Override
    public RegisterPackageResponse registerOrder(RegisterPackageRequest packageOrder) {
        Package order = new Package();
        PackageDescription order1 = new PackageDescription();
        Status packageStatus = new Status();
        User user = new User();


        order1.setName(packageOrder.getWhatToOrder());
        order1.setWeightInGrammes(packageOrder.getQuantity());

//        packageStatus.setStatus("Pending");
//        var dateTime = packageStatus.getDateTime();
//        order.setStatusList(List.of(packageStatus.setStatus("pending")));

        user.setEmail(packageOrder.getEmailAddress());
        order.setOwner(user);
        order.setId(generateId());
        order.setDescription(order1);

        Package savedPackage = packageRepository.save(order);
        return getRegisterPackageResponse(order, savedPackage);
    }

    private RegisterPackageResponse getRegisterPackageResponse(Package order, Package savedPackage) {



        RegisterPackageResponse packageOrder1 = new RegisterPackageResponse();
        packageOrder1.setEmailAddress(savedPackage.getOwner().getEmail());
        packageOrder1.setId(savedPackage.getId());
        packageOrder1.setDescription(savedPackage.getDescription());
//        packageOrder1.setStatus(savedPackage.getStatusList());

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
    public FindPackageResponse findPackageByTrackingId(String trackingId) {
        return null;
    }
}
