package africa.semicolon.sendAm.controllers;

import africa.semicolon.sendAm.dtos.requests.RegisterPackageRequest;
import africa.semicolon.sendAm.dtos.responses.FindPackageResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterPackageResponse;
import africa.semicolon.sendAm.services.PackageService;
import africa.semicolon.sendAm.services.PackageServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
public class PackageController {
   private PackageService packageService = new PackageServiceImpl();

   @PostMapping("/register")
    public RegisterPackageResponse registerNewPackage(@RequestBody RegisterPackageRequest request){
       return packageService.registerOrder(request);
   }

   @GetMapping("/{trackingId}")
    public FindPackageResponse trackPackageByTrackingId(@PathVariable String trackingId){
       return packageService.findPackageByTrackingId(trackingId);
   }
}
