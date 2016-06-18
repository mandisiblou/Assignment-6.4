package com.example.mandisi.myassign7.restapi.settings.api;

import com.example.mandisi.myassign7.restapi.settings.GenderResource;

import java.io.IOException;
import java.util.Set;


/**
 * Created by Mandisi on 2016/04/29.
 */
public interface GenderAPI {
    Set<GenderResource> getGenderType() throws IOException;
}
