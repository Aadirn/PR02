package com.mitienda.spring.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mitienda.spring.controllers.ClienteController;
import com.mitienda.spring.models.Clientes;
import com.mitienda.spring.models.comun.Tools;

public class menuClientes {
	private List<Clientes> clientesLista = new ArrayList<>();
	private ClienteController ctr = ClienteController.getInstance();
	private Clientes cli = new Clientes();
	private String opcion;
	private int sel;
	public  Scanner keyboard;
	private static menuClientes instance;

	public menuClientes() {

	}

	public static menuClientes getInstance() {
		if (instance == null) {
			instance = new menuClientes();
		}
		return instance;
	}

	public static menuClientes menu = new menuClientes();

	public void display() {
		keyboard = new Scanner(System.in);

		do {
			System.out.println("~~~~~~~~~~MENÚ CLIENTES~~~~~~~~~\n");
			System.out.println("1.-Crear\n2.-Leer\n3.-Actualizar\n4.-Borrar\n5.-Atrás\n");
			System.out.println("Seleccione(1|2|3|4|5): ");

			opcion = keyboard.nextLine();
			if (!Tools.getInstance().isNumeric(opcion)) {
				System.out.println("Teclea un numero, capullo\n");
			} else {
				sel = Integer.valueOf(opcion);

				switch (sel) {
				case 1:
					System.out.println("Crear\n");
					createClient();
					break;
				case 2:
					System.out.println("Leer\n");
					listClients();
					break;
				case 3:
					System.out.println("Actualizar\n");
					actualizarClients();
					break;
				case 4:
					System.out.println("Borrar\n");
					deleteClients();
					break;
				case 5:
					System.out.println("Atras\n");
					menuPrincipal.getInstance().display();
					break;
				default:
					System.out.println("ACCION NO VALIDA!\n");
					break;
				}
			}

		} while (true);

	}

	public void listClients() {

		clientesLista = ctr.list();

		for (int i = 0; i < clientesLista.size(); i++) {

			System.out.println(clientesLista.get(i).getId() + ".-" + clientesLista.get(i));

		}
	}

	public void actualizarClients() {

		System.out.println("Seleccione el cliente que quiera actualizar\n");
		listClients();
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		cli = ctr.findById((long) sel);
		do {
			System.out.println("¿Que quieres cambiar?");
			System.out.println(
					"1.-Nombre\n2.-DNI\n3.-Direcciones\n4.-Telefono\n5.-E-mail\n6.-Guardar Cambios\n7.-Atrás\n");
			keyboard.reset();
			opcion = keyboard.nextLine();
			sel = Integer.valueOf(opcion);
			switch (sel) {
			case 1:
				System.out.println("Escriba el nuevo nombre: \n");
				opcion = keyboard.nextLine();
				cli.setNombre(opcion);
				keyboard.reset();
				break;
			case 2:
				System.out.println("Escriba el nuevo DNI: \n");
				opcion = keyboard.nextLine();
				cli.setDni(opcion);
				keyboard.reset();

				break;
			case 3:
				System.out.println("Escriba la nueva direccion: \n");
				opcion = keyboard.nextLine();
				cli.setDireccion(opcion);
				keyboard.reset();

				break;
			case 4:
				System.out.println("Escriba el nuevo telefono: \n");
				opcion = keyboard.nextLine();
				cli.setTelefono(opcion);
				keyboard.reset();
				break;
			case 5:
				System.out.println("Escriba el nuevo e-mail: \n");
				opcion = keyboard.nextLine();
				cli.setEmail(opcion);
				keyboard.reset();
				break;
			case 6:
				System.out.println("Guardando...\n");
				ctr.save(cli);
				break;
			case 7:
				System.out.println("Volviendo a Menú Clientes\n");
				menuClientes.getInstance().display();
				break;
			default:
				break;
			}
		} while (true);

	}

	public void deleteClients() {
		listClients();
		System.out.println("Seleccione que cliente quiere borrar\n");
		opcion = keyboard.nextLine();

		sel = Integer.valueOf(opcion);
		cli = (Clientes) ctr.findById((long) sel);
		ctr.delete(cli);

		// TODO: Por Hacer
		// clientesLista.get(opcion).delete();

	}

	public void createClient() {

		System.out.println("Introduzca nombre del cliente\n");
		cli.setNombre(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Introduzca el DNI\n");
		cli.setDni(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Introduzca direccion\n");
		cli.setDireccion(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Introduzca telefono\n");
		cli.setTelefono(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Introduzca e-mail\n");
		cli.setEmail(keyboard.nextLine());
		keyboard.reset();

		ctr.save(cli);

		System.out.println("Se ha insertado el nuevo cliente");

	}
}