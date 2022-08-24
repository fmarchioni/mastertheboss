/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
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
package org.jboss.as.quickstarts.gwthelloworld.test;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Tests GWT Hello World quickstart
 *
 * @author Matous Jobanek (mjobanek@redhat.com)
 */
@RunAsClient
@RunWith(Arquillian.class)
public class HelloworldGWTTest {

    /**
     * The message written out when we try to greet the empty string.
     */
    private String GREETING_ERROR = "Sorry, can't say hello now. Server responded with status code 404";

    /**
     * The name which is typed into the {@link #nameField}.
     */
    private static final String NAME = "Joe Doe";

    /**
     * The text field into which {@link #NAME} is typed.
     */
    @FindBy(css = "input[name='name']")
    private WebElement nameField;

    /**
     * The button which is pushed after entering the {@link #NAME} into the {@link #nameField}.
     */
    @FindBy(css = "button[name='sayHelloButton']")
    private WebElement button;

    /**
     * The text label which writes out greeting.
     */
    @FindBy(css = "fieldset>div")
    private WebElement label;

    /**
     * Specifies relative path to the war of built application in the main project.
     */
    private static final String DEPLOYMENT_WAR = "../target/jboss-helloworld-gwt.war";

    /**
     * Injects browser to our test.
     */
    @Drone
    private WebDriver browser;

    /**
     * Injects URL on which application is running.
     */
    @ArquillianResource
    private URL contextPath;

    /**
     * Creates deployment which is sent to the container upon test's start.
     *
     * @return war file which is deployed while testing, the whole application in our case
     */
    @Deployment(testable = false)
    public static WebArchive helloWorld() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File(DEPLOYMENT_WAR));
    }

    private void open() {
        browser.get(contextPath.toString());
    }

    /**
     * This method tests if the ordinary greeting mechanism works properly.
     */
    @Test
    @InSequence(1)
    public void testGreeting() {

        open();

        Graphene.waitModel().until().element(nameField).is().visible();
        nameField.sendKeys(NAME);

        Graphene.guardAjax(button).click();

        Graphene.waitModel().until().element(label).is().visible();
        Assert.assertTrue("Greeting service is not functioning well!", label.getText().equals("Hello " + NAME + "!"));
    }

    /**
     * This method tests what happens if empty string
     * into {@link #nameField} is entered. We await error message
     * saying we can't greet empty string.
     */
    @Test
    @InSequence(2)
    public void testEmptyGreeting() {

        open();

        Graphene.waitModel().until().element(nameField).is().visible();

        // entering of the empty string
        nameField.sendKeys("");

        Graphene.guardAjax(button).click();

        Graphene.waitModel().until().element(label).is().visible();
        Assert.assertTrue("We greet the empty string!", label.getText().equals(GREETING_ERROR));
    }

}
