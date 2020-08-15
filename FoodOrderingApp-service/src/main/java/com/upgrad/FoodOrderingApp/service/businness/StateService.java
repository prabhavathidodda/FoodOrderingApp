package com.upgrad.FoodOrderingApp.service.businness;

import com.upgrad.FoodOrderingApp.service.dao.StateDAO;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StateService {

    @Autowired
    private StateDAO stateDao;

    //Method to get state entity using UUID of state.
    public StateEntity getStateByUUID (String uuid)throws AddressNotFoundException {
        //Calls getStateByUuid od StateDao to get all the State details.
        StateEntity stateEntity = stateDao.getStateByUUID(uuid);
        if(stateEntity == null) {//Checking if its null to return error message.
            throw new AddressNotFoundException("ANF-002", "No state by this id");
        }
        return  stateEntity;
    }
}