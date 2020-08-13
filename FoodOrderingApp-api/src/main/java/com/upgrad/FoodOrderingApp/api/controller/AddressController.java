package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.SaveAddressRequest;
import com.upgrad.FoodOrderingApp.api.model.SaveAddressResponse;
import com.upgrad.FoodOrderingApp.service.businness.AddressService;
import com.upgrad.FoodOrderingApp.service.businness.CustomerService;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAddressEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/** */
@CrossOrigin
@RestController
@RequestMapping("/")
public class AddressController {

  @Autowired private AddressService addressService;
  @Autowired private CustomerService customerService;

  /**
   * Method to save address of a customer
   *
   * @param authorization
   * @param saveAddressRequest
   * @return
   * @throws AuthorizationFailedException
   * @throws AddressNotFoundException
   * @throws SaveAddressException
   */
  @CrossOrigin
  @RequestMapping(
      method = RequestMethod.POST,
      path = "/address",
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<SaveAddressResponse> saveAddress(
      @RequestHeader("authorization") final String authorization,
      @RequestBody(required = false) SaveAddressRequest saveAddressRequest)
      throws AuthorizationFailedException, AddressNotFoundException, SaveAddressException {

    AddressEntity addressEntity = new AddressEntity();
    String bearerToken = authorization.split("Bearer ")[1];
    CustomerEntity customerEntity = customerService.getCustomer(bearerToken);

    addressEntity.setUuid(UUID.randomUUID().toString());
    addressEntity.setFlatBuilNumber(saveAddressRequest.getFlatBuildingName());
    addressEntity.setLocality(saveAddressRequest.getLocality());
    addressEntity.setCity(saveAddressRequest.getCity());
    addressEntity.setPinCode(saveAddressRequest.getPincode());

    StateEntity stateEntity = addressService.findStateByUUID(saveAddressRequest.getStateUuid());
    AddressEntity addressCommittedEntity = addressService.saveAddress(addressEntity, stateEntity);
    CustomerAddressEntity customerAddressEntity =
        addressService.saveCustomerAddressEntity(addressCommittedEntity, customerEntity);
    SaveAddressResponse saveAddressResponse =
        new SaveAddressResponse()
            .id(addressCommittedEntity.uuid)
            .status("ADDRESS SUCCESSFULLY REGISTERED");
    return new ResponseEntity<SaveAddressResponse>(saveAddressResponse, HttpStatus.CREATED);
  }
}
