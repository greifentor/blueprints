package de.ollie.demo;


import javax.annotation.PostConstruct;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Route(StartLayout.URL)
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport(value = "./styles/vaadin-text-area-styles.css", themeFor = "vaadin-text-area")
public class StartLayout extends VerticalLayout {

	public static final String URL = "demo";

	@PostConstruct
	void postConstruct() {
		setMargin(false);
		setWidthFull();
		TextArea textArea = new TextArea("TextArea", "Bla, bla, laber sülz!");
		textArea.setClassName("colored-text-area");
		add(textArea, new TextField("TextField", "Another laber"));
	}

}