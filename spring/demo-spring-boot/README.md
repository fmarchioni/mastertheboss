# Spring Boot Web MVC QuickStart

This example demonstrates how you can use Spring Boot and Spring MVC on Kubernetes or OpenShift to implement a REST service.

${H}# Building

The example can be built with

    mvn clean install

${H}# Running the example in Kubernetes

It is assumed a running Kubernetes platform is already running. If not you can find details how to [get started](http://fabric8.io/guide/getStarted/index.html).

Assuming your current shell is connected to Kubernetes or OpenShift so that you can type a command like

```
kubectl get pods
```

or for OpenShift

```
oc get pods
```

Then the following command will package your app and run it on Kubernetes:

```
mvn fabric8:run
```

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the [fabric8 developer console](http://fabric8.io/guide/console.html) to manage the running pods, and view logs and much more.


${H}# More details

You can find more details about running this [quickstart](http://fabric8.io/guide/quickstarts/running.html) on the website. This also includes instructions how to change the Docker image user and registry.


${H}# Access services using a web browser

When the application is running, you can use a web browser to access the REST service. Assuming that you
have a [Vagrant setup](http://fabric8.io/guide/getStarted/vagrant.html) you can access the REST service with
`http://spring-boot-webmvc-default.vagrant.f8`.

Notice: As it depends on your OpenShift setup, the hostname (route) might vary. Verify with `oc get routes` which
hostname is valid for you.  Add the '-Dfabric8.deploy.createExternalUrls=true' option to your maven commands if you want it to deploy a Route configuration for the service.

The URL `http://spring-boot-webmvc-default.vagrant.f8/ip` can be used to obtain the IP address to show service load-balancing
when running with multiple pods.


${H}# More details

You can find more details about running this [quickstart](http://fabric8.io/guide/quickstarts/running.html) on the website. This also includes instructions how to change the Docker image user and registry.
