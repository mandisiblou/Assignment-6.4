package com.example.mandisi.myassign7.Services.settings.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.mandisi.myassign7.Services.settings.MetaDataUpdateService;
import com.example.mandisi.myassign7.domain.AddressType;
import com.example.mandisi.myassign7.domain.ContactType;
import com.example.mandisi.myassign7.domain.Gender;
import com.example.mandisi.myassign7.restapi.settings.AddressTypeResource;
import com.example.mandisi.myassign7.restapi.settings.ContactTypeResource;

import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.restapi.settings.GenderResource;
import com.example.mandisi.myassign7.settings.AddressTypeRepository;
import com.example.mandisi.myassign7.settings.ContactTypeRespository;
import com.example.mandisi.myassign7.settings.GenderRepository;
import com.example.mandisi.myassign7.settings.Imp.AddressTypeRepositoryImpl;
import com.example.mandisi.myassign7.settings.Imp.ContactTypeRespositoryImpl;
import com.example.mandisi.myassign7.settings.Imp.GenderTypeRepositoryImpl;

public class MetaDataUpdateServiceImpl extends IntentService implements MetaDataUpdateService {
    private GenderRepository genderRepository;
    private ContactTypeRespository contactTypeRespository;
    private AddressTypeRepository addressTypeRepository;

    private static MetaDataUpdateServiceImpl service = null;

    public static MetaDataUpdateServiceImpl getInstance() {
        if (service == null)
            service = new MetaDataUpdateServiceImpl();
        return service;
    }

    public MetaDataUpdateServiceImpl() {
        super("MetaDataUpdateServiceImpl");
    }

    private static final String ACTION_CONTACT = "com.example.mandisi.myassign7.restapi.settings.api.Impl.action.CONTACT";
    private static final String ACTION_ADDRESS = "com.example.mandisi.myassign7.restapi.settings.api.Impl.action.ADDRESS";
    private static final String ACTION_GENDER = "com.example.mandisi.myassign7.restapi.settings.api.Impl.action.GENDER";

    // TODO: Rename parameters
    private static final String EXTRA_CONTACT = "com.example.mandisi.myassign7.restapi.settings.api.Impl.extra.CONTACT";
    private static final String EXTRA_ADDRESS = "com.example.mandisi.myassign7.restapi.settings.api.Impl.extra.ADDRESS";
    private static final String EXTRA_GENDER = "com.example.mandisi.myassign7.restapi.settings.api.Impl.extra.GENDER";


    @Override
    public void addContactTypes(Context context, ContactTypeResource contactTypeResource) {
        Intent intent = new Intent(context, MetaDataUpdateServiceImpl.class);
        intent.setAction(ACTION_CONTACT);
        intent.putExtra(EXTRA_CONTACT, contactTypeResource);
        context.startService(intent);
    }

    @Override
    public void addAddressTypes(Context context, AddressTypeResource addressTypeResource) {
        Intent intent = new Intent(context, MetaDataUpdateServiceImpl.class);
        intent.setAction(ACTION_ADDRESS);
        intent.putExtra(EXTRA_ADDRESS, addressTypeResource);
        context.startService(intent);
    }

    @Override
    public void addGenderType(Context context, GenderResource gender) {
        Intent intent = new Intent(context, MetaDataUpdateServiceImpl.class);
        intent.setAction(ACTION_GENDER);
        intent.putExtra(EXTRA_GENDER, gender);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CONTACT.equals(action)) {
                final ContactTypeResource contact = (ContactTypeResource) intent.getSerializableExtra(EXTRA_CONTACT);
                handleActionContact(contact);
            } else if (ACTION_ADDRESS.equals(action)) {
                final AddressTypeResource address = (AddressTypeResource) intent.getSerializableExtra(EXTRA_ADDRESS);

                handleActionAddress(address);
            } else if (ACTION_GENDER.equals(action)) {
                final GenderResource gender = (GenderResource) intent.getSerializableExtra(EXTRA_GENDER);
                handleActionGender(gender);
            }
        }
    }

    private void handleActionGender(GenderResource genderResource) {
        genderRepository = new GenderTypeRepositoryImpl(App.getAppContext());
        Gender gender = new Gender.Builder()
                .name(genderResource.getName())
                .serverId(genderResource.getServerId())
                .state(genderResource.getState())
                .build();
        genderRepository.save(gender);
    }

    private void handleActionAddress(AddressTypeResource address) {
        addressTypeRepository = new AddressTypeRepositoryImpl(App.getAppContext());
        AddressType addressType = new AddressType.Builder()
                .name(address.getName())
                .serverId(address.getServerId())
                .state(address.getState())
                .build();
        addressTypeRepository.save(addressType);
    }

    private void handleActionContact(ContactTypeResource contact) {
        contactTypeRespository = new ContactTypeRespositoryImpl(App.getAppContext());
        ContactType contactTypeResource = new ContactType.Builder()
                .name(contact.getName())
                .serverId(contact.getServerId())
                .state(contact.getState())
                .build();
        contactTypeRespository.save(contactTypeResource);
    }


}
