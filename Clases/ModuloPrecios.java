package Clases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import Excepciones.ProductoADarDeBajaNoExistenteException;
import Excepciones.ProductoYaExistenteException;
import Excepciones.ProductoNoEncontradoException;

public class ModuloPrecios {

	private static ModuloPrecios moduloPrecios;
	private HashMap<Integer, Producto> productos = new HashMap<Integer, Producto>();

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
	public Producto getProducto(int codigoDeProducto) throws ProductoNoEncontradoException {
		if (productos.containsKey(codigoDeProducto)) {
			System.out.println(productos.get(codigoDeProducto).toString());
			return productos.get(codigoDeProducto);
		} else {
			throw new ProductoNoEncontradoException();
		}
		
	}

	/**
	 * pre: Debe existir el producto en la lista. post: Devuelve una descripción
	 * del producto.
	 */
	public Producto getProducto(String descripcion) throws ProductoNoEncontradoException {
		Iterator<Producto> it = productos.values().iterator();
		while (it.hasNext()) {
			Producto p = it.next();
			if (p.getDescripcion().equals(descripcion)) {
				System.out.println(p.toString());
				return p;
			}
		}
		throw new ProductoNoEncontradoException();
	}

	/**
	 * pre: El nuevo producto no debe estar ya en la lista. post: Agrega el
	 * nuevo producto a la lista.
	 */
	public void altaProducto(Producto producto) throws ProductoYaExistenteException {
		if (!productos.containsValue(producto)) {
			productos.put(producto.getCodigoDeProducto(), producto);
		} else {
			throw new ProductoYaExistenteException();
		}
	}

	/**
	 * pre: El producto a dar de baja debe estar en la lista. post: Borra el
	 * producto de la lista.
	 */
	public void bajaProducto(Producto p) throws ProductoADarDeBajaNoExistenteException {
		if (productos.containsValue(p)) {
			productos.remove(p.getCodigoDeProducto());
		} else {
			throw new ProductoADarDeBajaNoExistenteException();
		}
	}

	/**
	 * pre: El producto a actualizar debe existir en la lista y el producto
	 * actualizado no debe existir. post: Se da de baja al producto
	 * desactualizado y se ingresa el actualizado.
	 */
	public void actualizarProducto(Producto productoActualizado)
			throws ProductoNoEncontradoException,
			ProductoADarDeBajaNoExistenteException {
		if (!(productos.containsKey(productoActualizado.getCodigoDeProducto()))) {
			throw new ProductoNoEncontradoException();
		} else {
			productos.put(productoActualizado.getCodigoDeProducto(), productoActualizado);
		}
	}

	/**
	 * pre: Se le pasa un producto por parametro post: Devuelve verdadero si el
	 * producto se encuentra en la lista.
	 */
	public boolean existeProducto(Producto producto) {
		return productos.containsValue(producto);
	}

	/**
	 * post: Lista los productos del menú con sus respectivos precios.
	 */
	public void listarMenu() {
		// Ordeno los productos por categoria
		LinkedList<Producto> lista = new LinkedList<Producto>();
		for (Producto p : productos.values()) {
			lista.add(p);
		}
		Collections.sort(lista);
		String ultimaCategoria = "";
		for (Producto p : lista) {
			// Si esta categoria de este producto es diferente a la anterior, la
			// imprimo
			if (ultimaCategoria != p.getCategoria().toString()) {
				System.out.println(p.getCategoria().toString());
				ultimaCategoria = p.getCategoria().toString();
			}
			// Imprimo el producto
			System.out.println(p.toStringMenu());
		}
	}

}
