package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.model.Package;
import africa.semicolon.sendAm.dtos.requests.UpdateTrackingInfoRequest;
import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageResponse;
import africa.semicolon.sendAm.dtos.responses.TrackPackageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageServiceImplTest {
    private PackageService packageService;

    @BeforeEach
    public void setUp(){
        packageService = new PackageServiceImpl();
    }
    @Test
    void registerAddedPackage_RegisterAddedPackageContainsOneElement() {
        RegisterPackageRequest packageToBeAdded = new RegisterPackageRequest();
        packageToBeAdded.setWhatToAdd("Chocolate");
        packageToBeAdded.setEmailAddress("esther@gmail.com");
        packageToBeAdded.setQuantity(2);
        packageService.registerPackage(packageToBeAdded);
        assertEquals(1, packageService.getRepository().count());
    }

    @Test
    void testStatusIsUpdated_WhenANewPackageIsCreatedTest(){
        savePackage();
        assertEquals(1, packageService.getRepository().count());

        Package savedPackage = packageService.getRepository().findById(1);
        assertEquals(1, savedPackage.getStatusList().size());

    }

    private void savePackage() {
        RegisterPackageRequest packageToAdd = new RegisterPackageRequest();
        packageToAdd.setQuantity(2);
        packageToAdd.setWhatToAdd("Big Boxers");
        packageToAdd.setEmailAddress("agboniro@gmail.com");

        packageService.registerPackage(packageToAdd);
    }

    @Test
    void correctOrderIsReturned() {
        RegisterPackageRequest packageDetails = new RegisterPackageRequest();
        packageDetails.setWhatToAdd("Chocolate");
        packageDetails.setEmailAddress("esther@gmail.com");
        packageDetails.setQuantity(2);

        assertEquals("esther@gmail.com",packageDetails.getEmailAddress());
     }

     @Test
    public void generateTrackingId(){
        RegisterPackageRequest packageDetails = new RegisterPackageRequest();
         packageDetails.setWhatToAdd("Chocolate");
         packageDetails.setEmailAddress("esther@gmail.com");
         packageDetails.setQuantity(2);

         RegisterPackageRequest packageDetails2 = new RegisterPackageRequest();
         packageDetails2.setWhatToAdd("Chocolate");
         packageDetails2.setEmailAddress("jacinth@gmail.com");
         packageDetails2.setQuantity(2);


         RegisterPackageResponse trackPackage = packageService.registerPackage(packageDetails);
         RegisterPackageResponse trackPackage2 = packageService.registerPackage(packageDetails2);
         assertEquals(1, trackPackage.getId());
         assertEquals(2, trackPackage2.getId());
         assertEquals("SAA/01", trackPackage.getTrackId());
         assertEquals("SAA/02", trackPackage2.getTrackId());
     }

     @Test
    public void statusCanBeAddedTest(){
       savePackage();

       UpdateTrackingInfoRequest trackingRequest = new UpdateTrackingInfoRequest();
       trackingRequest.setTrackingNumber(1);
       trackingRequest.setTrackingInfo("Arrived");
        packageService.updateTrackingInfo(trackingRequest);
        packageService.updateTrackingInfo(trackingRequest);

        Package savedPackage = packageService.getRepository().findById(1);
        assertEquals(3, savedPackage.getStatusList().size());

     }

     @Test
    void trackPackageTest(){
        savePackage();
        TrackPackageResponse trackingInfo = packageService.trackPackage(1);
     }

}