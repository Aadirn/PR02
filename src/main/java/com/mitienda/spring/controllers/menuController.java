package com.mitienda.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;

public class menuController {

	private static menuController instance;
	private List<Controller> lista= new ArrayList<>();
	private Scanner keyboard = new Scanner(System.in);

	private menuController() {
	}

	public static menuController getInstance() {
		if (instance == null) {
			instance = new menuController();
		}
		return instance;
	}
	
	public void elegirObj() {
		
		//lista=obj.list();
		
		for(int i=0;i<lista.size();i++) {
			//System.out.println(lista.get(i).getId()+".-"+lista.get(i)+"\n");
		}
		
		System.out.println("Seleccione la ID del objeto que desee\n");
		
		int idPedido = Integer.parseInt(keyboard.nextLine());
		
		//DbObject peticion = obj.getByid(idPedido);
		
		//return peticion;
		
		
	}

}
