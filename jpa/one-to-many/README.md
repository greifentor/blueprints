# JPA one to many

A blue print for one-to-many relationships.

## Abstract

The project shows an example for a one-to-many relationship in a clean architecture environment.


## T&T

* It seems to be vital to not add a field for the reference to the parent class. This will be handled with the
``@JoinColumn`` annotation in the parent class.