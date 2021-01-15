package com.example.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("mvc")
public class MvcConfig extends Application {

//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> classes = new HashSet<>();
//        classes.add(TaskController.class);
//        classes.add(CustomerController.class);
//        classes.add(LocaleController.class);
//        classes.add(PrimitiveConverterProvider.class);
//        classes.add(PostNotFoundExceptionMapper.class);
//        
//        // add krazo feature.
//        classes.add(ViewResponseFilter.class);
//        classes.add(ViewableWriter.class);
//        classes.add(CsrfValidateFilter.class);
//        classes.add(CsrfProtectFilter.class);
//        classes.add(CsrfExceptionMapper.class);
//        classes.add(PreMatchingRequestFilter.class);
//        classes.add(PostMatchingRequestFilter.class);
//        classes.add(MvcConverterProvider.class);
//        classes.add(HiddenMethodFilter.class);
//
//        return classes;
//    }

    /**
     *
     *
     * ViewEngine.VIEW_FOLDER
     *
     * Csrf.CSRF_PROTECTION
     *
     * Csrf.CSRF_HEADER_NAME
     *
     *
     *
     * @Override public Map<String, Object> getProperties() { final Map<String, Object> map = new HashMap<>();
     * map.put(ViewEngine.VIEW_FOLDER, "/jsp/"); return map; }
     */
}
