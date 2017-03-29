/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package infinispan;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

/**
 * @author Martin Gencur
 */
public class FootballManager {

    private static final String JDG_HOST = "jdg.host";
    private static final String HOTROD_PORT = "jdg.hotrod.port";
    private static final String PROPERTIES_FILE = "jdg.properties";
    private static final String msgTeamMissing = "The specified team \"%s\" does not exist, choose next operation\n";
    private static final String msgEnterTeamName = "Enter team name: ";
    private static final String initialPrompt = "Choose action:\n" + "============= \n" + "at  -  add a team\n"
            + "ap  -  add a player to a team\n" + "rt  -  remove a team\n" + "rp  -  remove a player from a team\n"
            + "p   -  print all teams and players\n" + "q   -  quit\n";
    private static final String teamsKey = "teams";

    private Console con;
    private RemoteCacheManager cacheManager;
    private RemoteCache<String, Object> cache;

    public FootballManager() {
       
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.addServer()
              .host("datagrid-app-hotrod")
              .port(11333);
        cacheManager = new RemoteCacheManager(builder.build());
        cache = cacheManager.getCache("default");
        
        System.out.println("Cache is "+cache);
        if(!cache.containsKey(teamsKey)) {
            List<String> teams = new ArrayList<String>();
            Team t = new Team("Barcelona");
            t.addPlayer("Messi");
            t.addPlayer("Pedro");
            t.addPlayer("Puyol");
            cache.put(t.getName(), t);
            teams.add(t.getName());
            cache.put(teamsKey, teams);
        }
    }
/*
    public void addTeam(String teamName) {
         
        @SuppressWarnings("unchecked")
        List<String> teams = (List<String>) cache.get(teamsKey);
        if (teams == null) {
            teams = new ArrayList<String>();
        }
        Team t = new Team(teamName);
        cache.put(teamName, t);
        teams.add(teamName);
        // maintain a list of teams under common key
        cache.put(teamsKey, teams);
    }

    public void addPlayers(String teamName) {
          String playerName = null;
        Team t = (Team) cache.get(teamName);
        if (t != null) {
           
                t.addPlayer(playerName);
        }
            cache.put(teamName, t);
        } else {
            con.printf(msgTeamMissing, teamName);
        }
    }

    public void removePlayer() {
        String playerName = con.readLine("Enter player's name: ");
        String teamName = con.readLine("Enter player's team: ");
        Team t = (Team) cache.get(teamName);
        if (t != null) {
            t.removePlayer(playerName);
            cache.put(teamName, t);
        } else {
            con.printf(msgTeamMissing, teamName);
        }
    }

    public void removeTeam() {
        String teamName = con.readLine(msgEnterTeamName);
        Team t = (Team) cache.get(teamName);
        if (t != null) {
            cache.remove(teamName);
            @SuppressWarnings("unchecked")
            List<String> teams = (List<String>) cache.get(teamsKey);
            if (teams != null) {
                teams.remove(teamName);
            }
            cache.put(teamsKey, teams);
        } else {
            con.printf(msgTeamMissing, teamName);
        }
    }
*/
    public void printTeams(PrintWriter out) {
        @SuppressWarnings("unchecked")
        List<String> teams = (List<String>) cache.get(teamsKey);
        if (teams != null) {
            for (String teamName : teams) {
               out.println(cache.get("Barcelona").toString());
            }
        }
    }

    public void stop() {
        cacheManager.stop();
    }

    

   
}
