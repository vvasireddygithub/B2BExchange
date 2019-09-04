package com.b2b.exchnage;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ServiceLoader;

import org.apache.sshd.common.Factory;
import org.apache.sshd.common.FactoryManager;
import org.apache.sshd.common.io.AbstractIoServiceFactoryFactory;
import org.apache.sshd.common.io.BuiltinIoServiceFactoryFactories;
import org.apache.sshd.common.io.DefaultIoServiceFactoryFactory;
import org.apache.sshd.common.io.IoServiceFactory;
import org.apache.sshd.common.io.IoServiceFactoryFactory;

import org.apache.sshd.common.util.GenericUtils;
import org.apache.sshd.common.util.threads.CloseableExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B2BExchangeServiceFactory extends  AbstractIoServiceFactoryFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(B2BExchangeServiceFactory.class);

    private IoServiceFactoryFactory factory;

    protected B2BExchangeServiceFactory() {
        this(null);
    }

    protected B2BExchangeServiceFactory(Factory<CloseableExecutorService> factory) {
        super(factory);
    }

    @Override
    public IoServiceFactory create(FactoryManager manager) {
        IoServiceFactoryFactory factoryInstance = getIoServiceProvider();
        return factoryInstance.create(manager);
    }

    /**
     * @return The actual {@link IoServiceFactoryFactory} being delegated
     */
    public IoServiceFactoryFactory getIoServiceProvider() {
        synchronized (this) {
			/*
			 * if (factory != null) { return factory; }
			 * 
			 * factory = newInstance(IoServiceFactoryFactory.class);
			 */
           // if (factory == null) {
                factory = BuiltinIoServiceFactoryFactories.NIO2.create();
                log.info("No detected/configured " + IoServiceFactoryFactory.class.getSimpleName()
                    + " using " + factory.getClass().getSimpleName());
			/*
			 * } else { log.info("Using {}", factory.getClass().getSimpleName()); }
			 */
            Factory<CloseableExecutorService> executorServiceFactory = getExecutorServiceFactory();
            if (executorServiceFactory != null) {
                factory.setExecutorServiceFactory(executorServiceFactory);
            }
        }

        return factory;
    }

    public static <T extends IoServiceFactoryFactory> T newInstance(Class<T> clazz) {
        String propName = clazz.getName();
        String factory = System.getProperty(propName);
        if (!GenericUtils.isEmpty(factory)) {
            return newInstance(clazz, factory);
        }

        Thread currentThread = Thread.currentThread();
        ClassLoader cl = currentThread.getContextClassLoader();
        if (cl != null) {
            T t = tryLoad(propName, ServiceLoader.load(clazz, cl));
            if (t != null) {
                return t;
            }
        }

        ClassLoader clDefault = DefaultIoServiceFactoryFactory.class.getClassLoader();
        if (cl != clDefault) {
            T t = tryLoad(propName, ServiceLoader.load(clazz, clDefault));
            if (t != null) {
                return t;
            }
        }

        return null;
    }

    public static <T extends IoServiceFactoryFactory> T tryLoad(String propName, ServiceLoader<T> loader) {
        Iterator<T> it = loader.iterator();
        Deque<T> services = new LinkedList<>();
        try {
            while (it.hasNext()) {
                try {
                    T instance = it.next();
                    services.add(instance);
                } catch (Throwable t) {
                    LOGGER.warn("Exception while instantiating factory from ServiceLoader", t);
                }
            }
        } catch (Throwable t) {
            LOGGER.warn("Exception while loading factory from ServiceLoader", t);
        }

        int numDetected = services.size();
        if (numDetected <= 0) {
            return null;
        }

        if (numDetected != 1) {
            LOGGER.error("Multiple ({}) registered instances detected:", numDetected);
            for (T s : services) {
                LOGGER.error("===> {}", s.getClass().getName());
            }
            throw new IllegalStateException("Multiple (" + numDetected + ")"
                + " registered " + IoServiceFactoryFactory.class.getSimpleName() + " instances detected."
                + " Please use -D" + propName + "=...factory class.. to select one"
                + " or remove the extra providers from the classpath");
        }

        return services.removeFirst();
    }

    public static <T extends IoServiceFactoryFactory> T newInstance(Class<T> clazz, String factory) {
        BuiltinIoServiceFactoryFactories builtin = BuiltinIoServiceFactoryFactories.fromFactoryName(factory);
        if (builtin != null) {
            IoServiceFactoryFactory builtinInstance = builtin.create();
            return clazz.cast(builtinInstance);
        }

        Thread currentThread = Thread.currentThread();
        ClassLoader cl = currentThread.getContextClassLoader();
        if (cl != null) {
            try {
                Class<?> loaded = cl.loadClass(factory);
                Object factoryInstance = loaded.newInstance();
                return clazz.cast(factoryInstance);
            } catch (Throwable t) {
                LOGGER.trace("Exception while loading factory " + factory, t);
            }
        }

        ClassLoader clDefault = DefaultIoServiceFactoryFactory.class.getClassLoader();
        if (cl != clDefault) {
            try {
                Class<?> loaded = clDefault.loadClass(factory);
                Object factoryInstance = loaded.newInstance();
                return clazz.cast(factoryInstance);
            } catch (Throwable t) {
                LOGGER.trace("Exception while loading factory " + factory, t);
            }
        }
        throw new IllegalStateException("Unable to create instance of class " + factory);
    }

    private static final class LazyDefaultIoServiceFactoryFactoryHolder {
        private static final B2BExchangeServiceFactory INSTANCE = new B2BExchangeServiceFactory();

        private LazyDefaultIoServiceFactoryFactoryHolder() {
            throw new UnsupportedOperationException("No instance allowed");
        }
    }

    @SuppressWarnings("synthetic-access")
    public static B2BExchangeServiceFactory getDefaultIoServiceFactoryFactoryInstance() {
        return LazyDefaultIoServiceFactoryFactoryHolder.INSTANCE;
    }
}

