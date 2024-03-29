package org.iringtools.services;


import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@Provider
public class JAXBContextResolver implements ContextResolver {

    private final static String annotatedPackages = "org.iringtools.mapping";

    private final static JAXBContext context = initContext();

    private static JAXBContext initContext() {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(annotatedPackages);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return context;
    }

    @Override
    public JAXBContext getContext(Class clazz) {
        return context;
    }

}