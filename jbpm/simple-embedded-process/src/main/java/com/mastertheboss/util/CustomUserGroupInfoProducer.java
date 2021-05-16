package com.mastertheboss.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;

import org.jbpm.services.cdi.producer.UserGroupInfoProducer;
import org.kie.api.task.UserGroupCallback;
import org.kie.internal.task.api.UserInfo;

@ApplicationScoped
@Alternative
public class CustomUserGroupInfoProducer implements UserGroupInfoProducer {

    private UserGroupCallback	callback	= new CustomUserGroupCallBack();
    private UserInfo			userInfo	= new CustomUserInfo();

    @Override
    @Produces
    public UserGroupCallback produceCallback() {

        return callback;
    }

    @Override
    @Produces
    public UserInfo produceUserInfo() {

        return userInfo;
    }

}
