package de.ollie.eventtest.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.eventtest.service.AService;

/**
 * The main view of the Music Manager application.
 *
 * @author ollie (01.04.2020)
 */
@Route()
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout implements RouterLayout {

	private final AService aService;

	private Button buttonConsumer = new Button("consumer");
	private Button buttonProducer = new Button("producer");

	public MainView(AService aService) {
		super();
		this.aService = aService;
		this.buttonConsumer.addClickListener(event -> {
			removeAll();
			add(new ConsumerView(aService));
		});
		this.buttonConsumer.setWidthFull();
		this.buttonProducer.addClickListener(event -> {
			removeAll();
			add(new ProducerView(aService));
		});
		this.buttonProducer.setWidthFull();
		add( //
				buttonConsumer, //
				buttonProducer //
		);
	}

}
