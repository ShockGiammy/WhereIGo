module WhereIGo {
	exports logic.beans;
	exports logic.view;
	exports logic.graphiccontrollers;
	exports logic.controllers;
	exports logic;

	requires java.logging;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires mysql.connector.java;
	requires java.desktop;
	
	opens logic.graphiccontrollers;
	opens logic;
}