package Clases;

import java.util.Map;

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
	
	@Override
	public String toStringMenu() {
		return super.padMiddle(getCodigoDeProducto() + "..." + getDescripcion(), 50, '.', String.valueOf(getPrecioDeVenta()) + "$") ;
	}
	
	public Map<Producto, Integer> getCantidad(Map<Producto, Integer> lista) {
		lista.put(this, lista.getOrDefault(this, 0) + 1);
		return lista;
	}
}
