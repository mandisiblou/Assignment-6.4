package com.example.mandisi.myassign7.Services.settings;

import android.content.Context;

import com.example.mandisi.myassign7.restapi.settings.AddressTypeResource;
import com.example.mandisi.myassign7.restapi.settings.ContactTypeResource;
import com.example.mandisi.myassign7.restapi.settings.GenderResource;


/**
 * Created by Mandisi on 2016/05/05.
 */
public interface MetaDataUpdateService {
    void addContactTypes(Context context, ContactTypeResource contactTypeResources);

    void addAddressTypes(Context context, AddressTypeResource addressTypeResources);

    void addGenderType(Context context, GenderResource genders);
}
