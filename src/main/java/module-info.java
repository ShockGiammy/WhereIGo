module WhereIGo {
	exports logic.beans;
	exports logic.graphiccontrollers;
	exports logic.controllers;
	exports logic.model;
	exports logic;
	exports logic.view;

	requires java.logging;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires mysql.connector.java;
	requires java.desktop;
	
	opens logic;
	opens logic.graphiccontrollers;
	opens logic.controllers;
	opens logic.beans;
	opens logic.model;
}