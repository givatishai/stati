package com.example.Services.impl;

import com.example.Objects.TutorObject;
import com.example.Persist;
import com.example.Services.TutorManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Sigal on 6/2/2016.
 */
public class TutorManagerImpl implements TutorManager{

    @Autowired
    private Persist persist;

    public void addTutorObject(String name, String telephoneNumber, String email, int pricePerHour, boolean activePayment) {
        TutorObject tutor = new TutorObject();
        tutor.setName(name);
        tutor.setTelephoneNumber(telephoneNumber);
        tutor.setEmail(email);
        tutor.setPricePerHour(pricePerHour);
        tutor.setActivePayment(activePayment);
        persist.save(tutor);

    }

}
