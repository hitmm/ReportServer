package com.report.server.service.encry;

import com.report.server.model.common.auth.EncryptionType;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author huguangyin
 * @Date 2019/6/25-14:30
 *  
 */
public class ProviderFactory {
    private static Map<EncryptionType, IEncryProvider> providerMap = new HashMap<>();

    private ProviderFactory() {

    }

    public static ProviderFactory getInstance() {
        return Singleton.INSTANCE;
    }

    public void registerProvider(EncryptionType type, IEncryProvider provider) {
        providerMap.put(type, provider);
    }

    public IEncryProvider getProvider(EncryptionType type) {
        return providerMap.get(type);
    }

    private static class Singleton {
        private final static ProviderFactory INSTANCE = new ProviderFactory();
    }
}
