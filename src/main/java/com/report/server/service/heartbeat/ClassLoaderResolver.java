package com.report.server.service.heartbeat;

import io.netty.handler.codec.serialization.ClassResolver;

public class ClassLoaderResolver implements ClassResolver{

    private final ClassLoader classLoader;

    public ClassLoaderResolver(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Class<?> resolve(String className) throws ClassNotFoundException {
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException ignored) {
            return Class.forName(className, false, classLoader);
        }
    }
}
