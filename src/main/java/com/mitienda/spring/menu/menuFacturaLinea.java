package com.mitienda.spring.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mitienda.spring.controllers.FacturaLineaController;
import com.mitienda.spring.models.FacturaLinea;
import com.mitienda.spring.models.comun.Tools;

public class menuFacturaLinea {

	private String opcion;
	private Scanner keyboard;
	private int sel;

	private List<FacturaLinea> facturasLin = new ArrayList<>();
	private FacturaLinea factLin = new FacturaLinea();
	private FacturaLineaController ctr = FacturaLineaController.getInstance();

	private static menuFacturaLinea instance;

	private menuFacturaLinea() {
	}

	public static menuFacturaLinea getInstance() {
		if (instance == null) {
			instance = new menuFacturaLinea();
		}
		return instance;
	}

	public void display() {

		facturasLin = ctr.list();

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
					menuPrincipal.getInstance().display();
					break;
				default:
					System.out.println("ACCION NO VALIDA!\n");
				}
			}
		} while (true);
	}

	private void listFactLin() {

		facturasLin = ctr.list();

		for (int i = 0; i < facturasLin.size(); i++) {
			System.out.println(facturasLin.get(i).getId() + ".-" + facturasLin.get(i) + "\n");
		}
	}

	private void createFactLin() {// TODO: COMPROBACIONES DE TODO
		System.out.println("Selecciona el ID de la factura a la que pertenece\n");
		// factLin.setId_factura(menuController.getInstance().elegirObj(new
		// Factura()).getId());
		System.out.println("Escriba el nombre de esta factura linea");
		factLin.setNombre(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Escriba el precio de esta factura linea");
		factLin.setPrecio(Integer.valueOf(keyboard.nextLine()));
		keyboard.reset();

		ctr.save(factLin);

	}

	private void deleteFactLin() {
		listFactLin();
		System.out.println("Seleccione que cliente quiere borrar\n");
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);

		factLin = ctr.findById((long) sel);
		ctr.delete(factLin);
	}

	private void actualizarFactLin() {
		System.out.println("Seleccione el cliente que quiera actualizar\n");
		listFactLin();
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		factLin = ctr.findById((long) sel);
		do {
			System.out.println("�Que quieres cambiar?");
			System.out.println("1.-ID\n2.-Nombre\n3.-Precio\n4.-Guardar Cambios\n5.-Atr�s\n");
			keyboard.reset();
			opcion = keyboard.nextLine();
			sel = Integer.valueOf(opcion);
			switch (sel) {
			case 1:
				System.out.println("Seleccione el nuevo ID al que pertenece: \n");
				// factLin.setId_factura(menuController.getInstance().elegirObj(new
				// FacturaLinea()).getId());
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
				ctr.save(factLin);
				break;
			case 5:
				System.out.println("Volviendo a Men� Factura Linea\n");
				menuFacturaLinea.getInstance().display();
				break;
			default:
				break;
			}
		} while (true);

	}
}
