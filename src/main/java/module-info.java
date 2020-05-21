module WhereIGo {
	exports logic.beans;
	exports logic.graphiccontrollers;
	exports logic.controllers;
	exports logic.model;
	exports logic;
	exports logic.view;

	requires java.logging;
	requires transitive java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires java.desktop;
	requires javafx.media;
	requires java.base;
	requires transitive javax.servlet.api;
	
	opens logic;
	opens logic.graphiccontrollers;
	opens logic.controllers;
	opens logic.beans;
	opens logic.model;
	opens logic.view;
}