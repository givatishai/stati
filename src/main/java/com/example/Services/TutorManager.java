package com.example.Services;

/**
 * Created by Sigal on 6/2/2016.
 */

import javax.transaction.Transactional;

@Transactional
public interface TutorManager {

    public void addTutorObject(String name, String telephoneNumber, String email, int pricePerHour, boolean activePayment);



}
