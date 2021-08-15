package con.mastertheboss.ejb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class UserRegistry {

        public ArrayList<String> listUsers;
        @PostConstruct
        public void init() {
                listUsers = new ArrayList<String>();
                listUsers.add("administrator");

        }

    	public void addUser(String username) {
    		listUsers.add(username);
    	}
    	public void removeUser(String username) {
    		listUsers.remove(username);
    	}

        public ArrayList<String> getListUsers() {
            return listUsers;
    }
}