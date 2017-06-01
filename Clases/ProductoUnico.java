package Clases;

import Excepciones.PrecioDeVentaInvalidoException;

public class ProductoUnico extends Producto {

	public ProductoUnico(String descripcion, double precioDeCosto,
			double precioDeVenta, Categorias categoria) throws PrecioDeVentaInvalidoException {
		super(descripcion, precioDeCosto, precioDeVenta, categoria);
	}
	

}
