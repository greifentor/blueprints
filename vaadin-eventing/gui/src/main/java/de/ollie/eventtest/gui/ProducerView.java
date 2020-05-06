package de.ollie.eventtest.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

import de.ollie.eventtest.service.AService;

/**
 * @author ollie (05.05.2020)
 */
public class ProducerView extends VerticalLayout implements RouterLayout {

	private final AService aService;

	private Button button = new Button("add");
	private Label label = new Label("-");

	public ProducerView(AService aService) {
		this.aService = aService;
		this.button.addClickListener(event -> this.aService.add());
		button.setWidthFull();
		label.setWidthFull();
		aService.addAServiceListener( //
				event -> getUI().ifPresentOrElse( //
						ui -> ui.access(() -> {
							label.setText("" + event.getValue());
						}), //
						() -> {
							throw new RuntimeException("An error!");
						}));
		add( //
				button, //
				label //
		);
	}

}