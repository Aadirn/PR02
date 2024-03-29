package com.mitienda.spring.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mitienda.spring.controllers.FacturaController;
import com.mitienda.spring.controllers.FacturaLineaController;
import com.mitienda.spring.models.Factura;
import com.mitienda.spring.models.FacturaLinea;
import com.mitienda.spring.models.comun.Tools;

public class MenuFacturaLinea {

	private String opcion;
	private Scanner keyboard;
	private int sel;

	private List<FacturaLinea> facturasLin = new ArrayList<>();
	private List<Factura> facturas = new ArrayList<>();
	private FacturaLinea factLin = new FacturaLinea();
	private FacturaLineaController ctrFL = FacturaLineaController.getInstance();
	private FacturaController ctrF = FacturaController.getInstance();

	private static MenuFacturaLinea instance;

	private MenuFacturaLinea() {
	}

	public static MenuFacturaLinea getInstance() {
		if (instance == null) {
			instance = new MenuFacturaLinea();
		}
		return instance;
	}

	public void display() {

		keyboard = new Scanner(System.in);
		do {
			System.out.println("~~~~~~~~~~MENU FACTURA LINEA~~~~~~~~~\n");
			System.out.println("1.-Crear\n2.-Leer\n3.-Actualizar\n4.-Borrar\n5.-Atr�s\n");
			System.out.println("Seleccione(1|2|3|4|5): ");

			opcion = keyboard.nextLine();
			if (!Tools.getInstance().isNumeric(opcion)) {
				System.out.println("Teclea un numero, capullo\n");
			} else {
				sel = Integer.valueOf(opcion);
				switch (sel) {

				case 1:
					System.out.println("Crear\n");
					createFactLin();
					break;
				case 2:
					System.out.println("Leer\n");
					listFactLin();
					break;
				case 3:
					System.out.println("Actualizar\n");
					actualizarFactLin();
					break;
				case 4:
					System.out.println("Borrar\n");
					deleteFactLin();
					break;
				case 5:
					System.out.println("Atras\n");
					MenuPrincipal.getInstance().display();
					break;
				default:
					System.out.println("ACCION NO VALIDA!\n");
				}
			}
		} while (true);
	}

	private void listFactLin() {

		facturasLin = ctrFL.list();

		for (int i = 0; i < facturasLin.size(); i++) {
			System.out.println(facturasLin.get(i).getId() + ".-" + facturasLin.get(i) + "\n");
		}
	}

	private void createFactLin() {// TODO: COMPROBACIONES DE TODO
		
		System.out.println("Selecciona el ID de la factura a la que pertenece\n");
		facturas=ctrF.list();
		for (int i = 0; i < facturas.size(); i++) {
			System.out.println(facturas.get(i).getId() + ".-" + facturas.get(i) + "\n");
		}
		opcion=keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		factLin.setId_factura(sel);
		System.out.println("Escriba el nombre de esta factura linea");
		factLin.setNombre(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Escriba el precio de esta factura linea");
		factLin.setPrecio(Integer.valueOf(keyboard.nextLine()));
		keyboard.reset();

		ctrFL.save(factLin);

	}

	private void deleteFactLin() {
		listFactLin();
		System.out.println("Seleccione que cliente quiere borrar\n");
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);

		factLin = ctrFL.findById((long) sel);
		ctrFL.delete(factLin);
	}

	private void actualizarFactLin() {
		System.out.println("Seleccione el cliente que quiera actualizar\n");
		listFactLin();
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		factLin = ctrFL.findById((long) sel);
		do {
			System.out.println("�Que quieres cambiar?");
			System.out.println("1.-ID\n2.-Nombre\n3.-Precio\n4.-Guardar Cambios\n5.-Atr�s\n");
			keyboard.reset();
			opcion = keyboard.nextLine();
			sel = Integer.valueOf(opcion);
			switch (sel) {
			case 1:
				System.out.println("Seleccione el nuevo ID al que pertenece: \n");
				facturas=ctrF.list();
				for (int i = 0; i < facturas.size(); i++) {
					System.out.println(facturas.get(i).getId() + ".-" + facturas.get(i) + "\n");
				}
				opcion=keyboard.nextLine();
				sel = Integer.valueOf(opcion);
				factLin.setId_factura(sel);
				break;
			case 2:
				System.out.println("Escriba el nuevo nombre: \n");
				opcion = keyboard.nextLine();
				factLin.setNombre(opcion);
				keyboard.reset();

				break;
			case 3:
				System.out.println("Escriba el nuevo precio: \n");
				opcion = keyboard.nextLine();
				factLin.setPrecio(Integer.valueOf(opcion));
				keyboard.reset();

				break;
			case 4:
				System.out.println("Guardando...\n");
				ctrFL.save(factLin);
				break;
			case 5:
				System.out.println("Volviendo a Men� Factura Linea\n");
				MenuFacturaLinea.getInstance().display();
				break;
			default:
				break;
			}
		} while (true);

	}
}
