package com.mitienda.spring.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mitienda.spring.controllers.CategoryController;
import com.mitienda.spring.controllers.ProductoController;
import com.mitienda.spring.models.Categoria;
import com.mitienda.spring.models.Producto;
import com.mitienda.spring.models.comun.Tools;

public class MenuProducto {

	private String opcion;
	private Scanner keyboard;
	private int sel;

	private List<Producto> productos = new ArrayList<>();
	private List<Categoria> cat = new ArrayList<>();
	private Producto prod = new Producto();
	private static MenuProducto instance;
	private ProductoController ctrP = ProductoController.getInstance();
	private CategoryController ctrC = CategoryController.getInstance();

	private MenuProducto() {
	}

	public static MenuProducto getInstance() {
		if (instance == null) {
			instance = new MenuProducto();
		}
		return instance;
	}

	public void display() {

		keyboard = new Scanner(System.in);
		do {
			System.out.println("~~~~~~~~~~MENÚ PRODUCTO~~~~~~~~~\n");
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
					createProd();
					break;
				case 2:
					System.out.println("Leer\n");
					listProd();
					break;
				case 3:
					System.out.println("Actualizar\n");
					actualizarProd();
					break;
				case 4:
					System.out.println("Borrar\n");
					deleteProd();
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

	private void listProd() {

		productos = ctrP.list();

		for (int i = 0; i < productos.size(); i++) {
			System.out.println(productos.get(i).getId() + ".-" + productos.get(i) + "\n");
		}
	}

	private void createProd() {// TODO: COMPROBACIONES DE TODO
		System.out.println("Introduzca nombre de Producto\n");
		prod.setNombre(keyboard.nextLine());
		keyboard.reset();
		System.out.println("Introduzca precio de Producto (Centimos)\n");
		prod.setPrecio(Integer.parseInt(keyboard.nextLine()));
		keyboard.reset();
		System.out.println("Introduzca stock de Producto\n");
		prod.setStock(Integer.parseInt(keyboard.nextLine()));
		keyboard.reset();
		System.out.println("Seleccione a que categoria pertenece el producto");
		cat=ctrC.list();
		for (int i = 0; i < cat.size(); i++) {
			System.out.println(cat.get(i).getId() + ".-" + cat.get(i) + "\n");
		}
		opcion=keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		prod.setId_categoria(sel);
		ctrP.save(prod);

	}

	private void deleteProd() {
		listProd();
		System.out.println("Seleccione que producto quiere borrar\n");
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);

		prod = ctrP.findById((long) sel);
		ctrP.delete(prod);
	}

	private void actualizarProd() {
		System.out.println("Seleccione el producto que quiera actualizar\n");
		listProd();
		opcion = keyboard.nextLine();
		sel = Integer.valueOf(opcion);
		prod = ctrP.findById((long) sel);
		String teclado;
		do {
			System.out.println("¿Que quieres cambiar?");
			System.out.println("1.-Nombre\n2.-Precio\n3.-Stock\n4.-ID Categoria\n5.-Guardar Cambios\n6.-Atrás\n");
			keyboard.reset();
			opcion = keyboard.nextLine();
			sel = Integer.valueOf(opcion);
			switch (sel) {
			case 1:
				System.out.println("Escriba el nuevo nombre: \n");
				teclado = keyboard.nextLine();
				prod.setNombre(teclado);
				keyboard.reset();
				break;
			case 2:
				System.out.println("Escriba el nuevo precio: \n");
				teclado = keyboard.nextLine();
				prod.setPrecio(Integer.parseInt(teclado));
				keyboard.reset();

				break;
			case 3:
				System.out.println("Escriba el nuevo stock: \n");
				teclado = keyboard.nextLine();
				prod.setStock(Integer.parseInt(teclado));
				keyboard.reset();

				break;
			case 4:
				System.out.println("Seleccione la nueva categoria de este producto: \n");
				cat=ctrC.list();
				for (int i = 0; i < cat.size(); i++) {
					System.out.println(cat.get(i).getId() + ".-" + cat.get(i) + "\n");
				}
				opcion=keyboard.nextLine();
				sel = Integer.valueOf(opcion);
				prod.setId_categoria(sel);
				ctrP.save(prod);

				break;
			case 5:
				System.out.println("Guardando...\n");
				ctrP.save(prod);
				break;
			case 6:
				System.out.println("Volviendo a Menú Productos\n");
				MenuProducto.getInstance().display();
				break;
			default:
				break;
			}
		} while (true);

	}
}
