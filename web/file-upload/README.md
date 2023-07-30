Servlet Upload and Download example
=====================================
Example taken from: https://www.mastertheboss.com/java-ee/servlet-30/uploading-a-file-with-a-servlet/

This example demonstrates the usage of a Servlet to upload and download files

###### Deploy
```shell
mvn clean install wildfly:deploy
```
###### Test
```shell
localhost:8080/servlet-demo/upload
```

```shell
localhost:8080/servlet-demo/download?file=myfile.txt
```