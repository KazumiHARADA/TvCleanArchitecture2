package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import com.example.excadmin.tvcleanarchitecture.data.api.APIClient;
import com.example.excadmin.tvcleanarchitecture.data.api.ServiceGenerator;

/**
 * Created by excadmin on 2017/07/21.
 */

public class RemoteAuthDataStore {

    private final APIClient client;

    public RemoteAuthDataStore() {
        this.client = ServiceGenerator.createService(APIClient.class);
    }

}
