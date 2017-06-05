package Clases;

import java.util.HashMap;
import java.util.Map.Entry;

import Excepciones.CantidadDeProductosInvalidoException;
import Excepciones.DescuentoInvalidoException;
import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;

public class Combo extends Producto {

	private int descuento;
	HashMap<Producto, Integer> productos;

	/**
	 * pre: Debe ingresarse una descripción valida y un descuento comprendido
	 * entre 0 y 100. post: Crea el combo.
	 */
	public Combo(String descripcion, int descuento)
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException,
			PrecioDeCostoInvalidoException {
		super(descripcion, 1, 2, Categorias.Combos);
		this.setDescuento(descuento);
		this.productos = new HashMap<Producto, Integer>();
	}

	/**
	 * post: Devuelve el valor del descuento.
	 */
	public int getDescuento() {
		return descuento;
	}

	/**
	 * pre: El valor del descuento debe estar comprendido entre 0 y 100. post:
	 * Cambia el porcentaje del descuento.
	 */
	public void setDescuento(int descuento) throws DescuentoInvalidoException {
		if (descuento > 0 && descuento < 100) {
			this.descuento = descuento;
		} else {
			throw new DescuentoInvalidoException();
		}
	}

	/**
	 * pre: El producto no debe existir ya en la lista y la cantidad debe ser
	 * mayor a cero. post: Agrega el producto al combo.
	 */
	public void agregarProducto(Producto producto, int cantidad)
			throws CantidadDeProductosInvalidoException,
			PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {
		if (!productos.containsKey(producto) && cantidad > 0) {
			productos.put(producto, cantidad);
			aplicarPrecios();
		} else {
			throw new CantidadDeProductosInvalidoException();
		}
	}

	/**
	 * pre: El producto debe existir en la lista. post: Borra el producto del
	 * combo.
	 */
	public void borrarProducto(Producto producto)
			throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException {
		if (productos.containsKey(producto)) {
			productos.remove(producto);
			aplicarPrecios();
		}
	}
	
	/**
	 * post: Devuelve si el combo contiene un producto.
	 */
	public boolean contieneUnProducto(Producto p) {
		return productos.containsKey(p);
	}

	/**
	 * pre: Se deben haber ingresado los productos que estarán en el combo.
	 * post: Se actualiza el precio del combo.
	 */
	public void aplicarPrecios() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException {
		int precioDeVentaTotal = 0;
		int precioDeCostoTotal = 0;
		for (Entry<Producto, Integer> p : productos.entrySet()) {
			precioDeVentaTotal += (p.getValue() * p.getKey().getPrecioDeVenta());
			precioDeCostoTotal += (p.getValue() * p.getKey().getPrecioDeCosto());
		}
		if(precioDeVentaTotal == 0) {
			super.setPrecioDeCosto(1);
			super.setPrecioDeVenta(2);
		} else {
			super.setPrecioDeCosto(precioDeCostoTotal);
			super.setPrecioDeVenta(precioDeVentaTotal
					- (descuento * precioDeVentaTotal / 100));
		}
	}

	public void imprimir() {
		System.out.println(getCodigoDeProducto() + "..." + getDescripcion()
				+ leftPad(getPrecioDeVenta(), 50, '.', getDescripcion()));
		for (Entry<Producto, Integer> p : productos.entrySet()) {
			System.out.println("- " + p.getValue() + " "
					+ p.getKey().getDescripcion());
		}
	}

	private String leftPad(Object original, int length, char padCharacter,
			Object descripcion) {
		String paddedString = original.toString();
		String descripcionDelProducto = descripcion.toString();
		while (paddedString.length() < length - descripcionDelProducto.length()
				- 1) {
			paddedString = padCharacter + paddedString;
		}
		return paddedString + "$";
	}

}
