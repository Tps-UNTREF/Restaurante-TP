package Clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Excepciones.ProductoAActualizarNoExistenteException;
import Excepciones.ProductoADarDeBajaNoExistenteException;
import Excepciones.ProductoActualizadoYaExistenteException;

public class ModuloPrecios {

	private static ModuloPrecios moduloPrecios;
	private ArrayList<Producto> productos = new ArrayList<Producto>();

	private ModuloPrecios() {
	}

	public static ModuloPrecios getModuloPrecios() {
		if (moduloPrecios == null) {
			moduloPrecios = new ModuloPrecios();
		}
		return moduloPrecios;
	}

	/**
	 * pre: Debe existir el producto en la lista. post: Devuelve una descripción
	 * del producto.
	 */
	public void getProducto(int codigoDeProducto) {
		for (Producto p : productos) {
			if (p.getCodigoDeProducto() == codigoDeProducto) {
				System.out.println("El producto " + p.getCategoria() + " "
						+ p.getDescripcion() + " con precio de costo $"
						+ p.getPrecioDeCosto() + " y precio de venta $"
						+ p.getPrecioDeVenta());
			}
		}
	}

	/**
	 * pre: Debe existir el producto en la lista. post: Devuelve una descripción
	 * del producto.
	 */
	public void getProducto(String descripcion) {
		for (Producto p : productos) {
			if (p.getDescripcion().equals(descripcion)) {
				System.out.println("El producto " + p.getCategoria() + " "
						+ p.getDescripcion() + " con precio de costo $"
						+ p.getPrecioDeCosto() + " y precio de venta $"
						+ p.getPrecioDeVenta());
			}
		}
	}

	/**
	 * pre: El nuevo producto no debe estar ya en la lista. post: Agrega el
	 * nuevo producto a la lista.
	 */
	public void altaProducto(Producto producto) {
		if (!productos.contains(producto)) {
			productos.add(producto);
		}
	}

	/**
	 * pre: El producto a dar de baja debe estar en la lista. post: Borra el
	 * producto de la lista.
	 */
	public void bajaProducto(Producto p)
			throws ProductoADarDeBajaNoExistenteException {
		if (productos.contains(p)) {
			productos.remove(p);
		} else {
			throw new ProductoADarDeBajaNoExistenteException();
		}
	}

	/**
	 * pre: El producto a actualizar debe existir en la lista y el producto
	 * actualizado no debe existir. post: Se da de baja al producto
	 * desactualizado y se ingresa el actualizado.
	 */
	public void actualizarProducto(Producto productoAActualizar,
			Producto productoActualizado)
			throws ProductoActualizadoYaExistenteException,
			ProductoAActualizarNoExistenteException,
			ProductoADarDeBajaNoExistenteException {
		boolean existe = false;
		if (productos.contains(productoAActualizar)
				&& !(existe = productos.contains(productoActualizado))) {
			this.bajaProducto(productoAActualizar);
			productos.add(productoActualizado);
		} else if (existe) {
			throw new ProductoActualizadoYaExistenteException();
		} else {
			throw new ProductoAActualizarNoExistenteException();
		}
	}

	/**
	 * pre: Se le pasa un producto por parametro post: Devuelve verdadero si el
	 * producto se encuentra en la lista.
	 */
	public boolean existeProducto(Producto producto) {
		return productos.contains(producto);
	}

	/**
	 * post: Lista los productos del menú con sus respectivos precios.
	 */
	public List<Producto> listarMenu() {
		// Ordeno los productos por categoria
		Collections.sort(productos);
		String ultimaCategoria = "";
		for (Producto p : productos) {
			// Si esta categoria de este producto es diferente a la anterior, la
			// imprimo
			if (ultimaCategoria != p.getCategoria().toString()) {
				System.out.println(p.getCategoria().toString());
				ultimaCategoria = p.getCategoria().toString();
			}
			// Imprimo el producto
			p.imprimir();
		}
		return productos;
	}

}
