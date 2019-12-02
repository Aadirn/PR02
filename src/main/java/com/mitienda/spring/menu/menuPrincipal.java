package com.mitienda.spring.menu;

import java.util.Scanner;

public class menuPrincipal {
	private int opcion;
	private Scanner keyboard;

	private static menuPrincipal instance;

	public static menuPrincipal getInstance() {
		if (instance == null) {
			instance = new menuPrincipal();
		}
		return instance;
	}

	public void display() {

		keyboard = new Scanner(System.in);

		do {
			System.out.println("~~~~~~~~~~MENÚ~~~~~~~~~\n");
			System.out.println("1.-Productos\n2.-Clientes\n3.-Categorias\n4.-Facturas\n5.-Facturas Linea\n6.-Salir\n");
			System.out.println("Seleccione(1|2|3|4|5): ");

			opcion = Integer.parseInt(keyboard.nextLine());

			switch (opcion) {
			case 1:
				System.out.println("Has elegido Productos");
				menuProducto.getInstance().display();
				break;
			case 2:
				System.out.println("Has elegido Clientes");
				menuClientes.getInstance().display();
				break;
			case 3:
				System.out.println("Has elegido Categorias");
				menuCategoria.getInstance().display();
				break;
			case 4:
				System.out.println("Has elegido Facturas");
				menuFactura.getInstance().display();
				break;
			case 5:
				System.out.println("Has elegido Factura Lineas");
				menuFacturaLinea.getInstance().display();
				break;
			case 6:
				System.out.println("Has elegido Salir");
				System.exit(0);
			default:
				System.out.println("ACCION NO VALIDA!\n");
				break;
			}
		} while (true);

	}

}
