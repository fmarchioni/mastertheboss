package com.packagename.myapp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

/**
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@PWA(name = "Project Base for Vaadin", shortName = "Project Base", enableInstallPrompt = false)
public class AppShell implements AppShellConfigurator {
}
