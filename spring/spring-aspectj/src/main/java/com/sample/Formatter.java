package com.sample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import java.util.ArrayList;


public class Formatter {

    public static void main(String args[]){
        ArrayList<String> listClass = new ArrayList();
        ArrayList<String> listMet = new ArrayList();

        listClass.add("it.vtfinance.vtpie.sct.mq.resulthandler.CBIMessageResultHandler");
        listMet.add("handleResult");

        listClass.add("it.vtfinance.vtpie.sct.mq.resulthandler.CBIMessageResultHandler");
        listMet.add("send");

        listClass.add("it.vtfinance.vtpie.sct.mq.resulthandler.CBIMessageResultHandler");
        listMet.add("initNotification");

        listClass.add("it.vtfinance.vtpie.sct.gateway.service.SctFileNotificationManagerImpl");
        listMet.add("createFileNotification");

        listClass.add("org.apache.commons.io.FilenameUtils");
        listMet.add("getExtension");

        listClass.add("org.springframework.jms.core.JmsTemplate.");
        listMet.add("send");

        listClass.add("javax.jms.Session");
        listMet.add("createObjectMessage");

        listClass.add("org.springframework.jms.core.JmsTemplate");
        listMet.add("send");

        listClass.add("org.springframework.jms.core.JmsTemplate");
        listMet.add("doSend");

        listClass.add("org.springframework.jms.core.MessageCreator");
        listMet.add("createMessage");


        listClass.add("org.springframework.integration.core.MessageProducer");
        listMet.add("send");

        listClass.add("it.vtfinance.vtpie.core.jms.SimpleJmsSender");
        listMet.add("send");

        listClass.add("it.vtfinance.vtpie.core.model.key.GenericKey");
        listMet.add("writeExternal");

        listClass.add("it.vtfinance.vtpie.core.model.key.GenericKey");
        listMet.add("readExternal");


        listClass.add("com.sample.Formatter");
        listMet.add("format");


        for (int ii=0;ii<listClass.size();ii++) {
            String clazz = listClass.get(ii);
            String meth = listMet.get(ii);


            System.out.println("RULE "+clazz+"."+meth + " start time");

            System.out.println("CLASS "+clazz);
            System.out.println("METHOD "+meth);
            System.out.println("AT ENTRY");
            System.out.println("BIND thread = Thread.currentThread();");
            System.out.println("startTime = System.currentTimeMillis()");
            System.out.println("IF true");
            System.out.println("DO link(\"" + meth + "." + clazz + "\", thread, startTime)");
            System.out.println("ENDRULE");


            System.out.println("\n\n");

            System.out.println("RULE "+clazz+"."+meth + " end time");
            System.out.println("CLASS "+clazz);
            System.out.println("METHOD "+meth);
            System.out.println("AT EXIT");
            System.out.println("BIND thread = Thread.currentThread();");
            System.out.println("startTime:int = unlink(\"" + meth + "." + clazz + "\", thread);");


            System.out.println("endTime = System.currentTimeMillis()");
            System.out.println("IF true");
            System.out.println("DO traceln(\"[BYTEMAN] " + clazz+"." +meth + " elapsedTime = \" + (endTime - startTime))");
            System.out.println("ENDRULE");

            System.out.println("\n\n\n");
        }


    }

    public String format(String s, Integer i){
        try {
            System.out.println("format");
            Thread.sleep((long)(Math.random() * 1000));
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        }
        return "foo";
    }


    public String format(String s){
        try {
            System.out.println("format2");
            Thread.sleep((long)(Math.random() * 100));
        } catch (java.lang.Exception exception) {
            exception.printStackTrace();
        }
        return "foo";
    }
}