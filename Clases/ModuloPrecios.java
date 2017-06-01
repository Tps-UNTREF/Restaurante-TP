/*
 * AGREGAR COMBO AL LISTAR CARTA
 */

package Clases;

import java.util.ArrayList;

import Clases.Producto.Categorias;

public class ModuloPrecios {
	
	private static ModuloPrecios moduloPrecios;
	private ArrayList<Producto> productos;
	
	private ModuloPrecios() {
		this.productos = new ArrayList<Producto>();
	}
	
	public static ModuloPrecios getModuloPrecios() {
		if(moduloPrecios == null) {
			moduloPrecios = new ModuloPrecios();
		}
		return moduloPrecios;
	}
	
	/**
	 * pre: Debe existir el producto en la lista.
	 * post: Devuelve una descripción del producto.
	 */
	public void getProducto(int codigoDeProducto) {
		for(Producto p : productos) {
			if(p.getCodigoDeProducto() == codigoDeProducto) {
				System.out.println("El producto " + p.getCategoria() + " " + p.getDescripcion() + 
									" con precio de costo $" + p.getPrecioDeCosto() + " y precio de venta $" + 
									p.getPrecioDeVenta());
			}
		}
	}
	
	/**
	 * pre: Debe existir el producto en la lista.
	 * post: Devuelve una descripción del producto.
	 */
	public void getProducto(String descripcion) {
		for(Producto p : productos) {
			if(p.getDescripcion().equals(descripcion)) {
				System.out.println("El producto " + p.getCategoria() + " " + p.getDescripcion() + 
									" con precio de costo $" + p.getPrecioDeCosto() + " y precio de venta $" + 
									p.getPrecioDeVenta());
			}
		}
	}
	
	/**
	 * pre: El nuevo producto no debe estar ya en la lista.
	 * post: Agrega el nuevo producto a la lista.
	 */
	public void altaProducto(Producto producto) {
		if(!productos.contains(producto)) {
			productos.add(producto);
		}
	}
	
	/**
	 * pre: El producto debe estar en la lista.
	 * post: Borra el producto de la lista.
	 */
	public void bajaProducto(int codigoDeProducto) {
		for(Producto p : productos) {
			if(p.getCodigoDeProducto() == codigoDeProducto) {
				productos.remove(p);
			}
		}
	}
	
	/**
	 * pre: El producto a actualizar debe existir en la lista.
	 * post: Se da de baja al producto desactualizado y se ingresa el actualizado.
	 */
	public void actualizarProducto(Producto productoAActualizar, Producto productoActualizado) {
		if(productos.contains(productoAActualizar)) {
			this.bajaProducto(productoAActualizar.getCodigoDeProducto());
			productos.add(productoActualizado);
		}
	}
	
	/**
	 * post: Lista los productos del menú con sus respectivos precios.
	 */
	public void listarMenu() {
		System.out.println("Bebidas sin alcohol");
		for(Producto p : productos) {
			if(p.getCategoria() == Categorias.BebidaSinAlcohol) {
				System.out.println(p.getCodigoDeProducto() + "..." + p.getDescripcion() + "......" + p.getPrecioDeVenta());
			}
		}
		System.out.println("Bebidas con alcohol");
		for(Producto p : productos) {
			if(p.getCategoria() == Categorias.BebidaConAlcohol) {
				System.out.println(p.getCodigoDeProducto() + "..." + p.getDescripcion() + "......" + p.getPrecioDeVenta());
			}
		}
		System.out.println("Hamburguesas");
		for(Producto p : productos) {
			if(p.getCategoria() == Categorias.Hamburguesas) {
				System.out.println(p.getCodigoDeProducto() + "..." + p.getDescripcion() + "......" + p.getPrecioDeVenta());
			}
		}
		System.out.println("Pizzas");
		for(Producto p : productos) {
			if(p.getCategoria() == Categorias.Pizzas) {
				System.out.println(p.getCodigoDeProducto() + "..." + p.getDescripcion() + "......" + p.getPrecioDeVenta());
			}
		}
		System.out.println("Minutas");
		for(Producto p : productos) {
			if(p.getCategoria() == Categorias.Minutas) {
				System.out.println(p.getCodigoDeProducto() + "..." + p.getDescripcion() + "......" + p.getPrecioDeVenta());
			}
		}
	}

}
