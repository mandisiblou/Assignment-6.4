package com.example.mandisi.myassign7.Services.settings.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;
import java.util.Set;

import com.example.mandisi.myassign7.Services.settings.GetMetaDataService;
import com.example.mandisi.myassign7.Services.settings.MetaDataUpdateService;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.restapi.settings.AddressTypeResource;
import com.example.mandisi.myassign7.restapi.settings.ContactTypeResource;
import com.example.mandisi.myassign7.restapi.settings.GenderResource;
import com.example.mandisi.myassign7.restapi.settings.api.AddressTypeAPI;
import com.example.mandisi.myassign7.restapi.settings.api.ContactTypeAPI;
import com.example.mandisi.myassign7.restapi.settings.api.GenderAPI;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.AddressTypeAPIImpl;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.ContactTypeAPIImpl;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.GenderAPIImpl;


public class GetMetaDataServiceImpl extends IntentService implements GetMetaDataService {
    private final GenderAPI genderApi;
    private final ContactTypeAPI contactTypeAPI;
    private final AddressTypeAPI addressTypeAPI;
    private final MetaDataUpdateService metaDataUpdateService;

    private static GetMetaDataServiceImpl service = null;

    public static GetMetaDataServiceImpl getInstance() {
        if (service == null)
            service = new GetMetaDataServiceImpl();
        return service;
    }

    public GetMetaDataServiceImpl() {
        super("GetMetaDataServiceImpl");
        genderApi = new GenderAPIImpl();
        contactTypeAPI = new ContactTypeAPIImpl();
        addressTypeAPI = new AddressTypeAPIImpl();
        metaDataUpdateService = MetaDataUpdateServiceImpl.getInstance();
    }

    @Override
    public void getMetaData(Context context) {
        Intent intent = new Intent(context, GetMetaDataServiceImpl.class);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Set<GenderResource> genderResources = genderApi.getGenderType();
            handleGender(genderResources);

            Set<ContactTypeResource> contactTypes = contactTypeAPI.getContactType();
            handleContacts(contactTypes);

            Set<AddressTypeResource> addressTypes = addressTypeAPI.getAddressType();
            handleAddress(addressTypes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleAddress(Set<AddressTypeResource> addressTypes) {
        for (AddressTypeResource resource : addressTypes) {
            metaDataUpdateService.addAddressTypes(App.getAppContext(), resource);
        }
    }

    private void handleContacts(Set<ContactTypeResource> contactTypes) {
        for (ContactTypeResource contactType : contactTypes) {
            metaDataUpdateService.addContactTypes(App.getAppContext(), contactType);
        }

    }

    private void handleGender(Set<GenderResource> genderResources) {
        for (GenderResource gender : genderResources) {
            metaDataUpdateService.addGenderType(App.getAppContext(), gender);
        }
    }


}
