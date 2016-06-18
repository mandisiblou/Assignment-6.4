package com.example.mandisi.myassign7.restapi.settings.api.Impl;

import com.example.mandisi.myassign7.Persons.PersonAddress;
import com.example.mandisi.myassign7.conf.util.AppUtil;
import com.example.mandisi.myassign7.restapi.settings.api.PersonAddressAPI;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Mandisi on 2016/05/02.
 */
public class PersonAddressAPIImpl implements PersonAddressAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/person/address/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/person/address/update";

    @Override
    public PersonAddress createPersonAddress(PersonAddress address) throws IOException {
        String json = new Gson().toJson(address);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        PersonAddress personAddress = new Gson().fromJson(value, PersonAddress.class);
        return personAddress;
    }

    @Override
    public PersonAddress updatePersonAddress(PersonAddress address) throws IOException {
        String json = new Gson().toJson(address);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        PersonAddress personAddress = new Gson().fromJson(value, PersonAddress.class);
        return personAddress;
    }
}
