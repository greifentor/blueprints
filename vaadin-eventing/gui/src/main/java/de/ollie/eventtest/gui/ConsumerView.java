package de.ollie.eventtest.gui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

import de.ollie.eventtest.service.AService;
import de.ollie.eventtest.service.AServiceEvent;
import de.ollie.eventtest.service.AServiceListener;

/**
 * @author ollie (05.05.2020)
 */
public class ConsumerView extends VerticalLayout implements AServiceListener, RouterLayout {

	private final AService aService;

	private Grid<Integer> grid = new Grid<>(15);
	private Label label = new Label("-");

	public ConsumerView(AService aService) {
		this.aService = aService;
		label.setWidthFull();
		grid.addColumn(i -> "" + i).setHeader("Values");
		grid.setItems(getValues(0));
		grid.setWidthFull();
		aService.addAServiceListener(this);
		add( //
				label, //
				grid //
		);
	}

	private Integer[] getValues(int i) {
		Integer[] ints = new Integer[i];
		for (int j = 0; j < i; j++) {
			ints[j] = j + 1;
		}
		return ints;
	}

	@Override
	public void added(AServiceEvent event) {
		getUI().ifPresentOrElse( //
				ui -> ui.access(() -> {
					label.setText("" + event.getValue());
					grid.setItems(getValues(event.getValue()));
				}), //
				() -> {
					throw new RuntimeException("An error!");
				});
	}

}