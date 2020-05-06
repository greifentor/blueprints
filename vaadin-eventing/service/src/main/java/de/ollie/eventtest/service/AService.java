package de.ollie.eventtest.service;

/**
 * @author ollie (05.05.2020)
 */
public interface AService {

	void add();

	void addAServiceListener(AServiceListener l);

	int getValue();

}