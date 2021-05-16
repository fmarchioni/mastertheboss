package com.mastertheboss.util;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.task.UserGroupCallback;

    public class CustomUserGroupCallBack implements UserGroupCallback {

        @Override
        public boolean existsUser(String userId) {

            return true;
        }

        @Override
        public boolean existsGroup(String groupId) {

            return true;
        }

       // @Override
        public List<String> getGroupsForUser(String userId, List<String> groupIds, List<String> allExistingGroupIds) {

            List<String> roles = new ArrayList<>();
            roles.add("admin");
            return roles;
        }

		@Override
		public List<String> getGroupsForUser(String userId) {
			  List<String> roles = new ArrayList<>();
	            roles.add("admin");
	            return roles;
		}
    }


