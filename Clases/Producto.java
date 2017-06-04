package Clases;

import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;

public abstract class Producto implements Comparable<Producto> {

	private static int contadorId = 0;
	private int codigoDeProducto;
	private String descripcion;
	private double precioDeCosto;
	private double precioDeVenta;
	private Categorias categoria;

	/**
	 * pre: El precio de venta debe ser mayor a cero y el precio de costo debe
	 * ser mayor a cero y menor que el precio de venta. post: Se crea el
	 * producto.
	 */
	public Producto(String descripcion, double precioDeCosto,
			double precioDeVenta, Categorias categoria)
			throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException {
		contadorId++;
		this.codigoDeProducto = contadorId;
		this.descripcion = descripcion;
		this.setPrecioDeCosto(precioDeCosto);
		this.setPrecioDeVenta(precioDeVenta);
		this.categoria = categoria;
	}

	/**
	 * post: Devuelve el codigo del producto.
	 */
	public int getCodigoDeProducto() {
		return codigoDeProducto;
	}

	/**
	 * post: Devuelve la categoria del producto.
	 */
	public Categorias getCategoria() {
		return categoria;
	}

	/**
	 * post: Cambia la descripcion del producto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * post: Devuelve la descripcion del producto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * pre: El precio de costo debe ser mayor a cero. post: Cambia el precio de
	 * costo.
	 */
	public void setPrecioDeCosto(double precioDeCosto)
			throws PrecioDeCostoInvalidoException {
		if (precioDeCosto > 0) {
			this.precioDeCosto = precioDeCosto;
		} else {
			throw new PrecioDeCostoInvalidoException();
		}
	}

	/**
	 * post: Devuelve el precio de costo.
	 */
	public double getPrecioDeCosto() {
		return precioDeCosto;
	}

	/**
	 * pre: El precio de venta debe ser mayor al precio de costo. post: Cambia
	 * el precio de venta.
	 */
	public void setPrecioDeVenta(double precioDeVenta)
			throws PrecioDeVentaInvalidoException {
		if (precioDeVenta >= this.getPrecioDeCosto()) {
			this.precioDeVenta = precioDeVenta;
		} else {
			throw new PrecioDeVentaInvalidoException();
		}
	}

	/**
	 * post: Devuelve el precio de venta.
	 */
	public double getPrecioDeVenta() {
		return precioDeVenta;
	}

	public abstract void imprimir();

	@Override
	public int compareTo(Producto other) {
		return this.getCategoria().compareTo(other.getCategoria());
	}

	public enum Categorias {
		BebidaConAlcohol, BebidaSinAlcohol, Pizzas, Hamburguesas, Minutas, Combos
	}
}
