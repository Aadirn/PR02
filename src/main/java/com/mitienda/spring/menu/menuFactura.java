package com.mitienda.spring.menu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.mitienda.spring.controllers.FacturaController;
import com.mitienda.spring.models.Factura;
import com.mitienda.spring.models.comun.Tools;

public class menuFactura {

	List<Factura> facturas = new ArrayList<>();
	Factura fac = new Factura();
	private String opcion;
	private Scanner keyboard;
	private int sel;
	private FacturaController ctr = FacturaController.getInstance();

	private static menuFactura instance;

	public menuFactura() {
	}

	public static menuFactura getInstance() {
		if (instance == null) {
			instance = new menuFactura();
		}
		return instance;
	}

	public void display() {
		facturas = ctr.list();

		keyboard = new Scanner(System.in);

		do {
			System.out.println("~~~~~~~~~~MENÚ FACTURAS~~~~~~~~~\n");
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
					createFact();
					break;
				case 2:
					System.out.println("Leer\n");
					listFact();
					break;
				case 3:
					System.out.println("Actualizar\n");
					actualizarFact();
					break;
				case 4:
					System.out.println("Atras\n");
					deleteFact();
					break;
				case 5:
					System.out.println("Has elegido volver al Menu Principal");
					menuPrincipal.getInstance().display();
					break;
				default:
					break;
				}
			}
		} while (true);

	}

	public void listFact() {

		facturas = ctr.list();

		for (int i = 0; i < facturas.size(); i++) {
			System.out.println(facturas.get(i).getId() + ".-" + facturas.get(i) + "\n");
		}
	}

	public void actualizarFact() {

		Date fecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Seleccione la factura que quiera actualizar\n");
		listFact();
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		fac = ctr.findById((long) sel);
		do {
			System.out.println("¿Que quieres cambiar?");
			System.out.println("1.-ID Cliente\n2.-Fecha (No implementado)\n3.-Serie\n4.-Guardar Cambios\n5.-Atrás\n");
			keyboard.reset();
			opcion = keyboard.nextLine();
			sel = Integer.valueOf(opcion);
			switch (sel) {
			case 1:
				System.out.println("Seleccione el nuevo cliente: \n");
				// fact.setId_cliente(menuController.getInstance().elegirObj(new
				// Clientes()).getId());
				keyboard.reset();
				break;
			case 2:
				System.out.println("Escriba la nueva fecha: (No Implementado) \n");
				break;
			case 3:
				System.out.println("Escriba la nueva serie: \n");
				opcion = keyboard.nextLine();
				fac.setSerie(Integer.valueOf(opcion));
				keyboard.reset();

				break;
			case 4:
				System.out.println("Guardando...\n");
				ctr.save(fac);
				break;
			case 5:
				System.out.println("Volviendo a Menú Facturas\n");
				menuFactura.getInstance().display();
				break;
			default:
				break;
			}
		} while (true);

		/*
		 * Date fecha = null; SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 * String eleccion;
		 * 
		 * System.out.println("Dime la posicion del Articulo que quieres Modificar");
		 * eleccion = keyboard.nextLine(); int opcion = Integer.parseInt(eleccion);
		 * facturas.get(opcion); int id = facturas.get(opcion).getId(); // TODO: Por
		 * Hacer // fac = (Factura) fac.getByid(id);
		 * 
		 * System.out.println("Dime la fecha de Factura"); String nuevaFechaFactura =
		 * keyboard.nextLine(); try { fecha = sdf.parse(nuevaFechaFactura); } catch
		 * (ParseException e) { e.printStackTrace(); } fac.setFecha(fecha);
		 * System.out.println("Dime el ID del cliente"); int nuevoIdCliente =
		 * Integer.parseInt(keyboard.nextLine()); fac.setId_cliente(nuevoIdCliente);
		 * System.out.println("Dime la Serie de la Factura"); int nuevaSerieCliente =
		 * Integer.parseInt(keyboard.nextLine()); fac.setSerie(nuevaSerieCliente);
		 */

	}

	public void deleteFact() {
		listFact();
		System.out.println("Seleccione que factura quiere borrar\n");
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);

		fac = ctr.findById((long) sel);
		ctr.delete(fac);

	}

	public void createFact() {

		/*
		 * keyboard.reset(); Date fecha = null; SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy/MM/dd");
		 * 
		 * System.out.println("Dime la fecha de Factura"); String nuevaFechaFactura =
		 * keyboard.nextLine();
		 * 
		 * try { fecha = sdf.parse(nuevaFechaFactura); } catch (ParseException e) {
		 * e.printStackTrace(); }
		 * 
		 * fac.setFecha(fecha);
		 * System.out.println("Dime el ID del cliente de la Factura"); int
		 * nuevoIdCliente = Integer.parseInt(keyboard.nextLine());
		 * fac.setId_cliente(nuevoIdCliente);
		 * System.out.println("Dime la Serie de la Factura"); int nuevaSerieCliente =
		 * Integer.parseInt(keyboard.nextLine()); fac.setSerie(nuevaSerieCliente);
		 * 
		 * System.out.println("Se ha insertado el nuevo cliente");
		 */

		Date fecha = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		System.out.println("Seleccione ID del cliente al que pertenece esta factura\n");
		// fact.setId_cliente(menuController.getInstance().elegirObj(new
		// Clientes()).getId());
		keyboard.reset();
		System.out.println("Introduzca la serie de esta factura\n");
		fac.setSerie(Integer.parseInt(keyboard.nextLine()));
		keyboard.reset();
		// Problema con la fecha en sql, asi que...
		fac.setFecha(null);
		ctr.save(fac);

	}

}
