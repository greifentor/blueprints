package de.ollie.eventtest.gui;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;

@Push
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class AppShell implements AppShellConfigurator {
}
