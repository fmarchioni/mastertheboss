///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.6.3


import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Command(name = "mvnversion", mixinStandardHelpOptions = true, version = "mvnversion 0.1",
        description = "mvnversion made with jbang")
class mvnversion implements Callable<Integer> {


    @CommandLine.Option(
        names = {"-g", "--groupId"},
        description = "The groupId",
        required = true)
    private String groupId;

    @CommandLine.Option(
        names = {"-a", "--artifactId"},
        description = "The artifactId",
        required = true)
    private String artifactId;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new mvnversion()).execute(args);
        System.exit(exitCode);
    }
    
    
        @Override
    public Integer call() throws Exception {
 

        File localRepo = new File(System.getProperty("user.home"), ".m2/repository");
        if (!localRepo.exists()) {
            System.err.println("Local repository not found at " + localRepo);
            return 1;
        }

        Pattern pattern = Pattern.compile(artifactId + "-(\\d+\\.\\d+\\.\\d+.*?)(\\.jar|\\.pom)");
        List<String> versions = new ArrayList<>();
        scan(localRepo, pattern, versions);

        Collections.sort(versions, new VersionComparator());

        System.out.println("The following versions of " + groupId + ":" + artifactId + " files were found in your local Maven repository:");
        for (String version : versions) {
            System.out.println("  - " + version);
        }
        return 0;
    }

    private static void scan(File file, Pattern pattern, List<String> versions) throws IOException {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                scan(child, pattern, versions);
            }
        } else {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                versions.add(matcher.group(1));
            }
        }
    }

    static class VersionComparator implements java.util.Comparator<String> {
              public int compare(String v1, String v2) {
            String[] v1parts = v1.split("\\.");
            String[] v2parts = v2.split("\\.");

            int majorCompare = Integer.compare(Integer.parseInt(v1parts[0]), Integer.parseInt(v2parts[0]));
            if (majorCompare != 0) {
                return majorCompare;
            }

            int minorCompare = Integer.compare(Integer.parseInt(v1parts[1]), Integer.parseInt(v2parts[1]));
            if (minorCompare != 0) {
                return minorCompare;
            }

            int patchCompare = Integer.compare(Integer.parseInt(v1parts[2]), Integer.parseInt(v2parts[2]));
            return patchCompare;
        }
    }
}


