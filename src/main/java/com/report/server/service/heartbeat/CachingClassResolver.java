package com.report.server.service.heartbeat;

import io.netty.handler.codec.serialization.ClassResolver;

import java.util.Map;

public class CachingClassResolver implements ClassResolver{

    private final Map<String, Class<?>> classCache;
    private final ClassResolver delegate;

    public CachingClassResolver(ClassResolver delegate, Map<String, Class<?>> classCache) {
        this.delegate = delegate;
        this.classCache = classCache;
    }

    @Override
    public Class<?> resolve(String className) throws ClassNotFoundException {
        // Query the cache first.
        Class<?> clazz;
        clazz = classCache.get(className);
        if (clazz != null) {
            return clazz;
        }

        // And then try to load.
        clazz = delegate.resolve(className);

        classCache.put(className, clazz);
        return clazz;
    }
}
