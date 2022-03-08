package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
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
    void registerOrder_RegisterOrderContainsOneElement() {
        RegisterPackageRequest orderDetails = new RegisterPackageRequest();
        orderDetails.setWhatToOrder("Chocolate");
        orderDetails.setEmailAddress("esther@gmail.com");
        orderDetails.setQuantity(2);
        packageService.registerOrder(orderDetails);
        assertEquals(1, packageService.getRepository().count());
    }

    @Test
    void correctOrderIsReturned() {
        RegisterPackageRequest orderDetails = new RegisterPackageRequest();
        orderDetails.setWhatToOrder("Chocolate");
        orderDetails.setEmailAddress("esther@gmail.com");
        orderDetails.setQuantity(2);

//        RegisterPackageResponse orderReceipt = packageService.registerOrder(orderDetails);
//        assertEquals(1, orderReceipt.getId());
//        assertEquals("Chocolate",orderReceipt.getDescription().getName());
        assertEquals("esther@gmail.com",orderDetails.getEmailAddress());
     }

     @Test
    public void generateTrackingId(){
        RegisterPackageRequest packageDetails = new RegisterPackageRequest();
         packageDetails.setWhatToOrder("Chocolate");
         packageDetails.setEmailAddress("esther@gmail.com");
         packageDetails.setQuantity(2);

         RegisterPackageRequest packageDetails2 = new RegisterPackageRequest();
         packageDetails2.setWhatToOrder("Chocolate");
         packageDetails2.setEmailAddress("jacinth@gmail.com");
         packageDetails2.setQuantity(2);


         RegisterPackageResponse trackPackage = packageService.registerOrder(packageDetails);
         RegisterPackageResponse trackPackage2 = packageService.registerOrder(packageDetails2);
         assertEquals(1, trackPackage.getId());
         assertEquals(2, trackPackage2.getId());
         assertEquals("SAA/01", trackPackage.getTrackId());
         assertEquals("SAA/02", trackPackage2.getTrackId());
     }

     @Test
    public void statusCanBeGotten(){
         RegisterPackageRequest packageDetails = new RegisterPackageRequest();
         packageDetails.setWhatToOrder("Chocolate");
         packageDetails.setEmailAddress("esther@gmail.com");
         packageDetails.setQuantity(2);

         RegisterPackageResponse trackPackage = packageService.registerOrder(packageDetails);
         assertEquals(1, trackPackage.getId());
         assertEquals("SAA/01", trackPackage.getTrackId());
         assertEquals("Pending", trackPackage.checkStatus(trackPackage.getTrackId()));
     }

}