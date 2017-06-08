package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Clases.Combo;
import Clases.ModuloPrecios;
import Clases.Producto;
import Clases.Producto.Categorias;
import Clases.ProductoUnico;
import Excepciones.CantidadDeProductosInvalidoException;
import Excepciones.DescuentoInvalidoException;
import Excepciones.PrecioDeCostoInvalidoException;
import Excepciones.PrecioDeVentaInvalidoException;
import Excepciones.ProductoADarDeBajaNoExistenteException;
import Excepciones.ProductoActualizadoYaExistenteException;
import Excepciones.ProductoNoEncontradoException;

public class ModuloPreciosTest {

	ModuloPrecios moduloPrecios = ModuloPrecios.getModuloPrecios();

	@Test
	public void productoUnico_getCategoria() throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 5, 25, Categorias.BebidaSinAlcohol);

		assertEquals(Categorias.BebidaSinAlcohol, cocacola.getCategoria());

	}

	@Test
	public void productoUnico_getDescripcion() throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa Napolitana", 10, 50, Categorias.Minutas);

		assertEquals("Milanesa Napolitana", milanesaNapolitana.getDescripcion());
	}

	@Test
	public void productoUnico_getPrecioDeCosto() throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa Napolitana", 10, 50, Categorias.Minutas);

		assertEquals(10, milanesaNapolitana.getPrecioDeCosto(), 0.0);

	}

	@Test
	public void productoUnico_getPrecioDeVenta() throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 20, 55, Categorias.Hamburguesas);

		assertEquals(55, hamburguesa.getPrecioDeVenta(), 0.0);

	}

	@Test
	public void combo_getDescuento()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, PrecioDeCostoInvalidoException {

		Combo combo = new Combo("Combo", 20);

		assertEquals(20, combo.getDescuento(), 0.0);

	}

	@Test
	public void combo_agregaUnProductoAlComboYVerificaQueSeAgrego() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException, DescuentoInvalidoException, CantidadDeProductosInvalidoException {

		Combo combo = new Combo("Combo dos pizzas", 20);

		ProductoUnico pizza = new ProductoUnico("Pizza", 15, 60, Categorias.Pizzas);

		combo.agregarProducto(pizza, 2);

		assertTrue(combo.contieneUnProducto(pizza));

	}

	@Test
	public void combo_borrarProductoDelComboYVerificarQueSeBorro() throws PrecioDeVentaInvalidoException,
			DescuentoInvalidoException, PrecioDeCostoInvalidoException, CantidadDeProductosInvalidoException {

		Combo combo = new Combo("Combo dos pizzas", 20);

		ProductoUnico pizza = new ProductoUnico("Pizza", 15, 60, Categorias.Pizzas);

		combo.agregarProducto(pizza, 2);
		combo.borrarProducto(pizza);

		assertFalse(combo.contieneUnProducto(pizza));

	}

	@Test
	public void combo_agregarVariosProductosYVerificarElPrecioDeCostoTotal() throws PrecioDeVentaInvalidoException,
			DescuentoInvalidoException, PrecioDeCostoInvalidoException, CantidadDeProductosInvalidoException {

		Combo menuInfantil = new Combo("Menu infantil", 10);

		ProductoUnico pizzaChica = new ProductoUnico("Pizza chica", 10, 40, Categorias.Pizzas);
		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 15, 55, Categorias.Hamburguesas);
		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 7, 30, Categorias.BebidaSinAlcohol);

		menuInfantil.agregarProducto(pizzaChica, 1);
		menuInfantil.agregarProducto(hamburguesa, 1);
		menuInfantil.agregarProducto(cocacola, 1);

		// El precio de costo total es la suma del costo de todos los productos
		// en el combo.
		assertEquals(32, menuInfantil.getPrecioDeCosto(), 0.0);

	}

	@Test
	public void combo_agregarVariosProductosYVerificarElPrecioDeVentaTotalAplicadoElDescuento()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, PrecioDeCostoInvalidoException,
			CantidadDeProductosInvalidoException {

		Combo menuInfantil = new Combo("Menu infantil", 10);

		ProductoUnico pizzaChica = new ProductoUnico("Pizza chica", 10, 30, Categorias.Pizzas);
		ProductoUnico hamburguesaConQueso = new ProductoUnico("Hamburguesa con queso", 15, 45, Categorias.Hamburguesas);
		ProductoUnico cocacola = new ProductoUnico("Coca Cola", 7, 25, Categorias.BebidaSinAlcohol);

		menuInfantil.agregarProducto(pizzaChica, 1);
		menuInfantil.agregarProducto(hamburguesaConQueso, 1);
		menuInfantil.agregarProducto(cocacola, 1);

		// El precio de venta total es la suma del precio de venta de todos los
		// productos en el combo aplicando el descuento.
		assertEquals(90, menuInfantil.getPrecioDeVenta(), 0.1);

	}

	@Test
	public void moduloPrecios_agregarProductoAlMenuYVerificarQueSeAgrego()
			throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException,
			ProductoADarDeBajaNoExistenteException, ProductoActualizadoYaExistenteException {

		ProductoUnico agua = new ProductoUnico("Agua", 5, 25, Categorias.BebidaSinAlcohol);

		moduloPrecios.altaProducto(agua);

		assertTrue(moduloPrecios.existeProducto(agua));

		moduloPrecios.bajaProducto(agua);

	}

	@Test
	public void moduloPrecios_borrarProductoDelMenuYVerificarQueSeBorro()
			throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException,
			ProductoADarDeBajaNoExistenteException, ProductoActualizadoYaExistenteException {

		ProductoUnico agua = new ProductoUnico("Agua", 5, 25, Categorias.BebidaSinAlcohol);

		moduloPrecios.altaProducto(agua);
		moduloPrecios.bajaProducto(agua);

		assertFalse(moduloPrecios.existeProducto(agua));

	}

	@Test
	public void moduloPrecios_getProductoConSuCodigo() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException, ProductoActualizadoYaExistenteException, ProductoNoEncontradoException {

		ProductoUnico cerveza = new ProductoUnico("Cerveza", 15, 40, Categorias.BebidaConAlcohol);

		moduloPrecios.altaProducto(cerveza);

		int codigo = cerveza.getCodigoDeProducto();

		moduloPrecios.getProducto(codigo);

	}

	@Test
	public void moduloPrecios_getProductoConSuDescripcion() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException, ProductoActualizadoYaExistenteException {

		ProductoUnico hamburguesa = new ProductoUnico("Hamburguesa", 20, 50, Categorias.Hamburguesas);

		moduloPrecios.altaProducto(hamburguesa);

		moduloPrecios.getProducto("Hamburguesa");

	}

	@Test
	public void moduloPrecios_actualizarProductoYVerificarQueSeReemplazaron() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException, ProductoActualizadoYaExistenteException,
			ProductoADarDeBajaNoExistenteException, ProductoNoEncontradoException {

		Producto cocacola = new ProductoUnico("Coca Cola", 10, 30, Categorias.BebidaSinAlcohol);

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
	public void moduloPrecios_imprimirMenu() throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException,
			DescuentoInvalidoException, CantidadDeProductosInvalidoException, ProductoActualizadoYaExistenteException {

		ProductoUnico gaseosa = new ProductoUnico("Gaseosa", 10, 30, Categorias.BebidaSinAlcohol);
		ProductoUnico aguaMineral = new ProductoUnico("Agua mineral", 5, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico aguaSaborizada = new ProductoUnico("Agua saborizada", 7, 25, Categorias.BebidaSinAlcohol);
		ProductoUnico cerveza = new ProductoUnico("Cerveza", 20, 55, Categorias.BebidaConAlcohol);
		ProductoUnico whisky = new ProductoUnico("Whisky", 40, 90, Categorias.BebidaConAlcohol);
		ProductoUnico hamburguesaSola = new ProductoUnico("Hamburguesa sola", 15, 50, Categorias.Hamburguesas);
		ProductoUnico hamburguesaJamonYQueso = new ProductoUnico("Hamburguesa con jamon y queso", 20, 60,
				Categorias.Hamburguesas);
		ProductoUnico hamburguesaTomateYLechuga = new ProductoUnico("Hamburguesa con tomate y lechuga", 20, 60,
				Categorias.Hamburguesas);
		ProductoUnico hamburguesaCompleta = new ProductoUnico("Hamburguesa completa", 30, 65, Categorias.Hamburguesas);
		ProductoUnico milanesa = new ProductoUnico("Milanesa", 20, 60, Categorias.Minutas);
		ProductoUnico milanesaNapolitana = new ProductoUnico("Milanesa napolitana", 25, 70, Categorias.Minutas);
		ProductoUnico pizzaMuzzarella = new ProductoUnico("Pizza de muzzarella", 25, 75, Categorias.Pizzas);
		ProductoUnico pizzaNapolitana = new ProductoUnico("Pizza napolitana", 30, 80, Categorias.Pizzas);
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

	@Test(expected = CantidadDeProductosInvalidoException.class)
	public void agregarProductoAlComboConCantidadIncorrecta() throws PrecioDeVentaInvalidoException,
			PrecioDeCostoInvalidoException, DescuentoInvalidoException, CantidadDeProductosInvalidoException {

		ProductoUnico producto = new ProductoUnico("Producto", 10, 30, Categorias.BebidaConAlcohol);

		Combo combo = new Combo("Combo", 30);

		combo.agregarProducto(producto, -2);

	}

	@Test(expected = DescuentoInvalidoException.class)
	public void crearComboConDescuentoInvalido()
			throws PrecioDeVentaInvalidoException, DescuentoInvalidoException, PrecioDeCostoInvalidoException {

		@SuppressWarnings("unused")
		Combo combo = new Combo("Combo", -10);

	}

	@Test(expected = PrecioDeCostoInvalidoException.class)
	public void crearProductoConPrecioDeCostoInvalido()
			throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		@SuppressWarnings("unused")
		ProductoUnico producto = new ProductoUnico("Producto", -5, 10, Categorias.BebidaSinAlcohol);

	}

	@Test(expected = PrecioDeVentaInvalidoException.class)
	public void crearProductoConPrecioDeVentaMenorAlPrecioDeCosto()
			throws PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		@SuppressWarnings("unused")
		ProductoUnico producto = new ProductoUnico("Producto", 20, 10, Categorias.BebidaSinAlcohol);

	}

	@Test(expected = ProductoNoEncontradoException.class)
	public void reemplazarUnProductoNoExistentePorOtro() throws ProductoNoEncontradoException,
			PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException, ProductoADarDeBajaNoExistenteException {

		ProductoUnico producto1 = new ProductoUnico("Producto 1", 5, 10, Categorias.BebidaSinAlcohol);

		moduloPrecios.actualizarProducto(producto1);

	}

	@Test(expected = ProductoADarDeBajaNoExistenteException.class)
	public void darDeBajaAUnProductoNoExistente() throws ProductoADarDeBajaNoExistenteException,
			PrecioDeVentaInvalidoException, PrecioDeCostoInvalidoException {

		ProductoUnico producto1 = new ProductoUnico("Producto 1", 5, 10, Categorias.BebidaSinAlcohol);

		moduloPrecios.bajaProducto(producto1);

	}

}
