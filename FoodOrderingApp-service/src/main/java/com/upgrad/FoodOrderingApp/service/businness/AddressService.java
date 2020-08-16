package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.AddressDAO;
import com.upgrad.FoodOrderingApp.service.dao.CustomerAddressDAO;
import com.upgrad.FoodOrderingApp.service.dao.StateDAO;
import com.upgrad.FoodOrderingApp.service.entity.*;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private StateDAO stateDAO;

    @Autowired
    private CustomerAddressDAO customerAddressDAO;

    /**
     * Method to save address
     *
     * @param addressEntity
     * @param stateEntity
     * @return
     * @throws SaveAddressException
     * @throws AddressNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AddressEntity saveAddress(AddressEntity addressEntity, StateEntity stateEntity)
            throws SaveAddressException, AddressNotFoundException {
        //    Block to check for empty values in fields
        if (addressEntity.getFlatBuilNumber() == null
                || addressEntity.getLocality() == null
                || addressEntity.getCity() == null
                || addressEntity.getPinCode() == null) {
            throw new SaveAddressException("SAR-001", "No field can be empty");
        }

        //    Block to check the validity of pincode
        if (!addressEntity.getPinCode().matches("[0-9]+") && addressEntity.getPinCode().length() != 6) {
            throw new SaveAddressException("SAR-002", "Invalid pincode");
        }

        //    Block to check the existence of UUID in table
//    if (findStateByUUID(stateEntity.getUuid()) == null) {
//      throw new AddressNotFoundException("ANF-002", "No state by this id");
//    }

        //    On success, return AddressEntity object
        return addressDAO.saveAddress(addressEntity);
    }

    /**
     * Method to find UUID in the State table
     *
     * @param uuid
     * @return On success, returns State entity object. On failure, returns NULL
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public StateEntity getStateByUUID(String uuid) {
        StateEntity stateEntity = stateDAO.findStateByUuid(uuid);
        if (stateEntity != null) {
            return stateEntity;
        }
        return null;
    }

    /**
     * Method to save customer address in table
     *
     * @param addressEntity
     * @param customerEntity
     * @return CustomerAddress entity object
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomerAddressEntity saveCustomerAddressEntity(
            AddressEntity addressEntity, CustomerEntity customerEntity) {
        CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
        customerAddressEntity.setAddress(addressEntity);
        customerAddressEntity.setCustomer(customerEntity);

        return customerAddressDAO.saveCustomerAddress(customerAddressEntity);
    }

//  @Transactional(propagation = Propagation.REQUIRED)
//  public AddressEntity deleteAddress(AddressEntity addressEntity) {
//
//  }

    /**
     * @param addressUuid
     * @param customerEntity
     * @return
     * @throws AuthorizationFailedException
     * @throws AddressNotFoundException
     */
    public AddressEntity getAddressByUUID(String addressUUID, CustomerEntity customerEntity) throws AuthorizationFailedException, AddressNotFoundException {
        AddressEntity addressEntity;
        CustomerAddressEntity customerAddressEntity;

        if (addressUUID == null) {
            throw new AddressNotFoundException("ANF-005", "Address id can not be empty");
        }
        addressEntity = addressDAO.getAddressByUUID(addressUUID);
        if (addressEntity == null) {
            throw new AddressNotFoundException("ANF-003", "No address by this id");
        }
        customerAddressEntity = customerAddressDAO.getCustomerAddressByAddress(addressEntity);
        if (customerAddressEntity.getCustomer().getUuid() == customerEntity.getUuid()) {
            return addressEntity;
        } else {
            throw new AuthorizationFailedException("ATHR-004", "You are not authorized to view/update/delete any one else's address");
        }

    }

    /**
     * @param addressEntity
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public AddressEntity deleteAddress(AddressEntity addressEntity) {
        AddressEntity deletedAddressEntity;
        List<OrdersEntity> ordersEntities = orderDao.getOrdersByAddress(addressEntity);

        if (ordersEntities == null || ordersEntities.isEmpty()) {
            deletedAddressEntity = addressDAO.deleteAddress(addressEntity);
            return deletedAddressEntity;
        } else {
            addressEntity.setActive(0);
            AddressEntity updatedAddressActiveStatus = addressDAO.updateAddressActiveStatus(addressEntity);
            return updatedAddressActiveStatus;
        }
    }
}
