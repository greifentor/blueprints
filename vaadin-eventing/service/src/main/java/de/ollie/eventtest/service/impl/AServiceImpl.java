package de.ollie.eventtest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import de.ollie.eventtest.service.AService;
import de.ollie.eventtest.service.AServiceEvent;
import de.ollie.eventtest.service.AServiceListener;

/**
 * @author ollie (05.05.2020)
 */
@Named
public class AServiceImpl implements AService {

	private int value = 0;
	private List<AServiceListener> listeners = new ArrayList<>();

	public void add() {
		this.value++;
		fireAServiceEvent(new AServiceEvent(this.value));
	}

	public void addAServiceListener(AServiceListener l) {
		if (l != null) {
			this.listeners.add(l);
		}
	}

	protected void fireAServiceEvent(AServiceEvent event) {
		this.listeners.forEach(l -> {
			try {
				l.added(event);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public int getValue() {
		return this.value;
	}

}