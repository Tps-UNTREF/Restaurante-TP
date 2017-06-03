package Clases;

import java.util.HashMap;
import java.util.Map.Entry;

import Excepciones.CantidadDeProductosInvalidoException;
import Excepciones.DescuentoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;

public class Combo extends Producto {

	private int descuento;
	HashMap<Producto, Integer> productos;
	
	public Combo(String descripcion, int descuento) throws PrecioDeVentaInvalidoException, DescuentoInvalidoException {
		super(descripcion, 0, 1, Categorias.Combos);
		this.setDescuento(descuento);
		this.productos = new HashMap<Producto, Integer>();
	}

	public int getDescuento() {
		return descuento;
	}
	
	public void setDescuento(int descuento) throws DescuentoInvalidoException {
		if(descuento > 0 && descuento < 100) {
			this.descuento = descuento;
		} else {
			throw new DescuentoInvalidoException();
		}
	}
	
	/**
	 * pre: El producto no debe existir ya en la lista y la cantidad debe ser mayor a cero.
	 * post: Agrega el producto al combo.
	 */
	public void agregarProducto(Producto producto, int cantidad) throws CantidadDeProductosInvalidoException, PrecioDeVentaInvalidoException {
		if(!productos.containsKey(producto) && cantidad > 0) {
			productos.put(producto, cantidad);
			aplicarPrecios();
		} else {
			throw new CantidadDeProductosInvalidoException();
		}
	}
	
	/**
	 * pre: El producto debe existir en la lista.
	 * post: Borra el producto del combo.
	 */
	public void borrarProducto(Producto producto) throws PrecioDeVentaInvalidoException {
		if(productos.containsKey(producto)) {
			productos.remove(producto);
			aplicarPrecios();
		}
	}
	
	/**
	 * pre: Se deben haber ingresado los productos que estarán en el combo.
	 * post: Se actualiza el precio del combo.
	 */
	public void aplicarPrecios() throws PrecioDeVentaInvalidoException {
		int precioDeVentaTotal = 0;
		int precioDeCostoTotal = 0;
		for(Entry<Producto, Integer> p : productos.entrySet()) {
			precioDeVentaTotal += (p.getValue() * p.getKey().getPrecioDeVenta());
			precioDeCostoTotal += (p.getValue() * p.getKey().getPrecioDeCosto());
		}
		super.setPrecioDeCosto(precioDeCostoTotal);
		super.setPrecioDeVenta(descuento * (precioDeVentaTotal / 100));
	}
	
}
