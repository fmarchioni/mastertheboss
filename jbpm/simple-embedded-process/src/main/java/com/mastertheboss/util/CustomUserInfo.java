package com.mastertheboss.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kie.api.task.model.Group;
import org.kie.api.task.model.OrganizationalEntity;
import org.kie.api.task.model.User;
import org.kie.internal.task.api.TaskModelProvider;
import org.kie.internal.task.api.UserInfo;
import org.kie.internal.task.api.model.InternalOrganizationalEntity;

public class CustomUserInfo implements UserInfo {

    @Override
    public String getDisplayName(OrganizationalEntity entity) {

        String displayname = "Soumya";
        return displayname;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<OrganizationalEntity> getMembersForGroup(Group group) {

        List<OrganizationalEntity> membersList = new ArrayList<>();
        User user = TaskModelProvider.getFactory().newUser();
        ((InternalOrganizationalEntity) user).setId("soumya");
        membersList.add(user);
        return (Iterator<OrganizationalEntity>) membersList;
    }

    @Override
    public boolean hasEmail(Group group) {

        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getEmailForEntity(OrganizationalEntity entity) {

        String email = "soumya.bose@plavaga.in";
        return email;
    }

    @Override
    public String getLanguageForEntity(OrganizationalEntity entity) {

        String language = "en_US";
        return language;
    }

	@Override
	public String getEntityForEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}