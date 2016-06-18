package com.example.mandisi.myassign7.restapi.settings.api.Impl;

import com.example.mandisi.myassign7.Persons.Persons;
import com.example.mandisi.myassign7.restapi.settings.Credential;
import com.example.mandisi.myassign7.restapi.settings.PersonResource;
import com.example.mandisi.myassign7.restapi.settings.api.ActivateAPI;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.example.mandisi.myassign7.conf.util.AppUtil;
/**
 * Created by Mandisi on 2016/04/17.
 */
public class ActivateAPIImpl implements ActivateAPI {
    private static final String url = AppUtil.getBaserURI() + "api/droid/activate/account/post";
    @Override
    public Persons activateAccount(final String email, final String auth) throws IOException {
        Credential cred = new Credential.Builder()
                .email(email)
                .value(auth)
                .build();
        String json = new Gson().toJson(cred);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
            String value = response.body().string();
            PersonResource resource = new Gson().fromJson(value, PersonResource.class);
        return new Persons.Builder()
                .firstName(resource.getFirstName())
                .lastName(resource.getLastName())
                .authvalue((resource.getAuthvalue()))
                .emailAddress(resource.getEmailAddress())
                .build();
    }
}
