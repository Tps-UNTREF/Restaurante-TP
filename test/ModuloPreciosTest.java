package test;

import static org.junit.Assert.*;

import org.junit.Test;

import clases.Combo;
import clases.ModuloPrecios;
import clases.Producto;
import clases.ProductoUnico;
import clases.Producto.Categoria;
import excepciones.CantidadDeProductosInvalidoExcepcion;
import excepciones.DescuentoInvalidoExcepcion;
import excepciones.PrecioDeCostoInvalidoExcepcion;
import excepciones.PrecioDeVentaInvalidoExcepcion;
import excepciones.ProductoADarDeBajaNoExistenteExcepcion;
import excepciones.ProductoNoEncontradoExcepcion;
import excepciones.ProductoYaExistenteExcepcion;

public class ModuloPreciosTest {

	ModuloPrecios moduloPrecios = ModuloPrecios.getModuloPrecios();

	@Test
	public void productoUnicogetCategoria() throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 5, 25, Categoria.BEBIDASINALCOHOL);

		assertEquals(Categoria.BEBIDASINALCOHOL, cocacola.getCategoria());

	}

	@Test
	public void productoUnicogetDescripcion() throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa Napolitana", 10, 50, Categoria.MUNUTAS);

		assertEquals("Milanesa Napolitana", milanesaNapolitana.getDescripcion());
	}

	@Test
	public void productoUnicogetPrecioDeCosto() throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa Napolitana", 10, 50, Categoria.MUNUTAS);

		assertEquals(10, milanesaNapolitana.getPrecioDeCosto(), 0.0);

	}

	@Test
	public void productoUnicogetPrecioDeVenta() throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 20, 55, Categoria.HAMBURGUESAS);

		assertEquals(55, hamburguesa.getPrecioDeVenta(), 0.0);

	}

	@Test
	public void combogetDescuento()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		Combo combo = new Combo("Combo", 20);

		assertEquals(20, combo.getDescuento(), 0.0);

	}

	@Test
	public void comboagregaUnProductoAlComboYVerificaQueSeAgrego() throws PrecioDeVentaInvalidoExcepcion,
			PrecioDeCostoInvalidoExcepcion, DescuentoInvalidoExcepcion, CantidadDeProductosInvalidoExcepcion {

		Combo combo = new Combo("Combo dos pizzas", 20);

		ProductoUnico pizza = new ProductoUnico("Pizza", 15, 60, Categoria.PIZZAS);

		combo.agregarProducto(pizza, 2);

		assertTrue(combo.contieneUnProducto(pizza));

	}

	@Test
	public void comboborrarProductoDelComboYVerificarQueSeBorro() throws PrecioDeVentaInvalidoExcepcion,
			DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, CantidadDeProductosInvalidoExcepcion {

		Combo combo = new Combo("Combo dos pizzas", 20);

		ProductoUnico pizza = new ProductoUnico("Pizza", 15, 60, Categoria.PIZZAS);

		combo.agregarProducto(pizza, 2);
		combo.borrarProducto(pizza);

		assertFalse(combo.contieneUnProducto(pizza));

	}

	@Test
	public void comboagregarVariosProductosYVerificarElPrecioDeCostoTotal() throws PrecioDeVentaInvalidoExcepcion,
			DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, CantidadDeProductosInvalidoExcepcion {

		Combo menuInfantil = new Combo("Menu infantil", 10);

		ProductoUnico pizzaChica = new ProductoUnico("Pizza chica", 10, 40, Categoria.PIZZAS);
		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 15, 55, Categoria.HAMBURGUESAS);
		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 7, 30, Categoria.BEBIDASINALCOHOL);

		menuInfantil.agregarProducto(pizzaChica, 1);
		menuInfantil.agregarProducto(hamburguesa, 1);
		menuInfantil.agregarProducto(cocacola, 1);

		// El precio de costo total es la suma del costo de todos los productos
		// en el combo.
		assertEquals(32, menuInfantil.getPrecioDeCosto(), 0.0);

	}

	@Test
	public void comboagregarVariosProductosYVerificarElPrecioDeVentaTotalAplicadoElDescuento()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion,
			CantidadDeProductosInvalidoExcepcion {

		Combo menuInfantil = new Combo("Menu infantil", 10);

		ProductoUnico pizzaChica = new ProductoUnico("Pizza chica", 10, 30, Categoria.PIZZAS);
		ProductoUnico hamburguesaConQueso = new ProductoUnico("Hamburguesa con queso", 15, 45, Categoria.HAMBURGUESAS);
		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 7, 25, Categoria.BEBIDASINALCOHOL);

		menuInfantil.agregarProducto(pizzaChica, 1);
		menuInfantil.agregarProducto(hamburguesaConQueso, 1);
		menuInfantil.agregarProducto(cocacola, 1);

		// El precio de venta total es la suma del precio de venta de todos los
		// productos en el combo aplicando el descuento.
		assertEquals(90, menuInfantil.getPrecioDeVenta(), 0.1);

	}

	@Test
	public void moduloPreciosagregarProductoAlMenuYVerificarQueSeAgrego() throws PrecioDeVentaInvalidoExcepcion,
			PrecioDeCostoInvalidoExcepcion, ProductoADarDeBajaNoExistenteExcepcion, ProductoYaExistenteExcepcion {

		ProductoUnico agua = new ProductoUnico("Agua", 5, 25, Categoria.BEBIDASINALCOHOL);

		moduloPrecios.altaProducto(agua);

		assertTrue(moduloPrecios.existeProducto(agua));

		moduloPrecios.bajaProducto(agua);

	}

	@Test
	public void moduloPreciosborrarProductoDelMenuYVerificarQueSeBorro() throws PrecioDeVentaInvalidoExcepcion,
			PrecioDeCostoInvalidoExcepcion, ProductoADarDeBajaNoExistenteExcepcion, ProductoYaExistenteExcepcion {

		ProductoUnico agua = new ProductoUnico("Agua", 5, 25, Categoria.BEBIDASINALCOHOL);

		moduloPrecios.altaProducto(agua);
		moduloPrecios.bajaProducto(agua);

		assertFalse(moduloPrecios.existeProducto(agua));

	}

	@Test
	public void moduloPreciosgetProductoConSuCodigo() throws PrecioDeVentaInvalidoExcepcion,
			PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion, ProductoNoEncontradoExcepcion {

		ProductoUnico cerveza = new ProductoUnico("Cerveza", 15, 40, Categoria.BEBIDACONALCOHOL);

		moduloPrecios.altaProducto(cerveza);

		int codigo = cerveza.getCodigoDeProducto();

		moduloPrecios.getProducto(codigo);

	}

	@Test
	public void moduloPreciosgetProductoConSuDescripcion() throws ProductoNoEncontradoExcepcion,
			PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion {

		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 20, 50, Categoria.HAMBURGUESAS);

		moduloPrecios.altaProducto(hamburguesa);

		moduloPrecios.getProducto("Hamburguesa");

	}

	@Test
	public void moduloPreciosactualizarProductoYVerificarQueSeReemplazaron()
			throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoYaExistenteExcepcion,
			ProductoADarDeBajaNoExistenteExcepcion, ProductoNoEncontradoExcepcion {

		Producto cocacola = new ProductoUnico("Coca Cola", 10, 30, Categoria.BEBIDASINALCOHOL);

		moduloPrecios.altaProducto(cocacola);

		cocacola.setDescripcion("Pepsi");
		cocacola.setPrecioDeCosto(7);
		cocacola.setPrecioDeVenta(28);

		cocacola = moduloPrecios.getProducto(cocacola.getCodigoDeProducto());
		assertEquals("Pepsi", cocacola.getDescripcion());
		assertEquals(7, cocacola.getPrecioDeCosto(), 1);
		assertEquals(28, cocacola.getPrecioDeVenta(), 1);
	}

	@Test
	public void moduloPreciosimprimirMenu() throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion,
			DescuentoInvalidoExcepcion, CantidadDeProductosInvalidoExcepcion, ProductoYaExistenteExcepcion {

		ProductoUnico gaseosa = new ProductoUnico("Gaseosa", 10, 30, Categoria.BEBIDASINALCOHOL);
		ProductoUnico aguaMineral = new ProductoUnico("Agua mineral", 5, 25, Categoria.BEBIDASINALCOHOL);
		ProductoUnico aguaSaborizada = new ProductoUnico("Agua saborizada", 7, 25, Categoria.BEBIDASINALCOHOL);
		ProductoUnico cerveza = new ProductoUnico("Cerveza", 20, 55, Categoria.BEBIDACONALCOHOL);
		ProductoUnico whisky = new ProductoUnico("Whisky", 40, 90, Categoria.BEBIDACONALCOHOL);
		ProductoUnico hamburguesaSola = new ProductoUnico("Hamburguesa sola", 15, 50, Categoria.HAMBURGUESAS);
		ProductoUnico hamburguesaJamonYQueso = new ProductoUnico("Hamburguesa con jamon y queso", 20, 60,
				Categoria.HAMBURGUESAS);
		ProductoUnico hamburguesaTomateYLechuga = new ProductoUnico("Hamburguesa con tomate y lechuga", 20, 60,
				Categoria.HAMBURGUESAS);
		ProductoUnico hamburguesaCompleta = new ProductoUnico("Hamburguesa completa", 30, 65, Categoria.HAMBURGUESAS);
		ProductoUnico milanesa = new ProductoUnico("Milanesa", 20, 60, Categoria.MUNUTAS);
		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa napolitana", 25, 70, Categoria.MUNUTAS);
		ProductoUnico pizzaMuzzarella = new ProductoUnico("Pizza de muzzarella", 25, 75, Categoria.PIZZAS);
		ProductoUnico pizzaNapolitana = new ProductoUnico("Pizza napolitana", 30, 80, Categoria.PIZZAS);
		Combo combo1 = new Combo("Combo pizza con dos cervezas", 20);
		Combo combo2 = new Combo("Combo hamburguesa con gaseosa", 15);
		Combo combo3 = new Combo("Combo Familiar", 10);

		combo1.agregarProducto(pizzaMuzzarella, 1);
		combo1.agregarProducto(cerveza, 2);

		combo2.agregarProducto(hamburguesaCompleta, 1);
		combo2.agregarProducto(gaseosa, 1);

		combo3.agregarProducto(combo1, 1);
		combo3.agregarProducto(combo2, 4);

		moduloPrecios.altaProducto(gaseosa);
		moduloPrecios.altaProducto(aguaMineral);
		moduloPrecios.altaProducto(aguaSaborizada);
		moduloPrecios.altaProducto(cerveza);
		moduloPrecios.altaProducto(whisky);
		moduloPrecios.altaProducto(hamburguesaSola);
		moduloPrecios.altaProducto(hamburguesaJamonYQueso);
		moduloPrecios.altaProducto(hamburguesaTomateYLechuga);
		moduloPrecios.altaProducto(hamburguesaCompleta);
		moduloPrecios.altaProducto(milanesa);
		moduloPrecios.altaProducto(milanesaNapolitana);
		moduloPrecios.altaProducto(pizzaMuzzarella);
		moduloPrecios.altaProducto(pizzaNapolitana);
		moduloPrecios.altaProducto(combo1);
		moduloPrecios.altaProducto(combo2);
		moduloPrecios.altaProducto(combo3);

		moduloPrecios.listarMenu();

	}

	@Test(expected = CantidadDeProductosInvalidoExcepcion.class)
	public void agregarProductoAlComboConCantidadIncorrecta() throws PrecioDeVentaInvalidoExcepcion,
			PrecioDeCostoInvalidoExcepcion, DescuentoInvalidoExcepcion, CantidadDeProductosInvalidoExcepcion {

		ProductoUnico producto = new ProductoUnico("Producto", 10, 30, Categoria.BEBIDACONALCOHOL);

		Combo combo = new Combo("Combo", 30);

		combo.agregarProducto(producto, -2);

	}

	@Test(expected = DescuentoInvalidoExcepcion.class)
	public void crearComboConDescuentoInvalido()
			throws PrecioDeVentaInvalidoExcepcion, DescuentoInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		@SuppressWarnings("unused")
		Combo combo = new Combo("Combo", -10);

	}

	@Test(expected = PrecioDeCostoInvalidoExcepcion.class)
	public void crearProductoConPrecioDeCostoInvalido()
			throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		@SuppressWarnings("unused")
		ProductoUnico producto = new ProductoUnico("Producto", -5, 10, Categoria.BEBIDASINALCOHOL);

	}

	@Test(expected = PrecioDeVentaInvalidoExcepcion.class)
	public void crearProductoConPrecioDeVentaMenorAlPrecioDeCosto()
			throws PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		@SuppressWarnings("unused")
		ProductoUnico producto = new ProductoUnico("Producto", 20, 10, Categoria.BEBIDASINALCOHOL);

	}

	@Test(expected = ProductoNoEncontradoExcepcion.class)
	public void reemplazarUnProductoNoExistentePorOtro() throws ProductoNoEncontradoExcepcion,
			PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion, ProductoADarDeBajaNoExistenteExcepcion {

		ProductoUnico producto1 = new ProductoUnico("Producto 1", 5, 10, Categoria.BEBIDASINALCOHOL);

		moduloPrecios.actualizarProducto(producto1);

	}

	@Test(expected = ProductoADarDeBajaNoExistenteExcepcion.class)
	public void darDeBajaAUnProductoNoExistente() throws ProductoADarDeBajaNoExistenteExcepcion,
			PrecioDeVentaInvalidoExcepcion, PrecioDeCostoInvalidoExcepcion {

		ProductoUnico producto1 = new ProductoUnico("Producto 1", 5, 10, Categoria.BEBIDASINALCOHOL);

		moduloPrecios.bajaProducto(producto1);

	}

}
