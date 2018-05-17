package com.example.testtask.di.module;

import android.content.Context;
import android.net.NetworkInfo;

import com.example.testtask.Utils.NetworkUtils;
import com.example.testtask.data.base.Repository;
import com.example.testtask.data.base.RepositoryImpl;
import com.example.testtask.data.database.Storage;
import com.example.testtask.data.encryption.RSAEncryption;
import com.example.testtask.data.encryption.RSAEncryptionImpl;
import com.example.testtask.data.network.Network;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    NetworkUtils provideNetworkUtils(Context context) {
        return new NetworkUtils(context);
    }

    @Provides
    Repository provideRepository(Network network, Storage storage, NetworkUtils networkUtils, RSAEncryption rsaEncryption) {
        return new RepositoryImpl(network, storage, networkUtils, rsaEncryption);
    }

    @Provides
    RSAEncryption provideRSAEncryption() {
        return new RSAEncryptionImpl();
    }
}
