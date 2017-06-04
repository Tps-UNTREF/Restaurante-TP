package Clases;

import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;

public class ProductoUnico extends Producto {

	/**
	 * pre: Debe ingresarse un precio de venta mayor a cero y un precio de costo
	 * mayor a cero y menor que el precio de venta. post: Crea el producto
	 * unico.
	 */
	public ProductoUnico(String descripcion, double precioDeCosto,
			double precioDeVenta, Categorias categoria)
			throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException {
		super(descripcion, precioDeCosto, precioDeVenta, categoria);
	}

	public void imprimir() {
		System.out.println(getCodigoDeProducto() + "..." + getDescripcion()
				+ leftPad(getPrecioDeVenta(), 50, '.', getDescripcion()));
	}

	private String leftPad(Object original, int length, char padCharacter,
			Object descripcion) {
		String paddedString = original.toString();
		String descripcionDelProducto = descripcion.toString();
		while (paddedString.length() < length - descripcionDelProducto.length()) {
			paddedString = padCharacter + paddedString;
		}
		return paddedString + "$";
	}

}
