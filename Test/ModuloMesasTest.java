package Test;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import Clases.Mesa;
import Clases.Mesa.Estados;
import Clases.ModuloMesa;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ModuloMesasTest {

	@Test
	public void a_Mesa_NumeroDeMesa() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(1,mesa.getNumeroDeMesa());
	}
	@Test
	public void b_Mesa_Estado() {
		Mesa mesa = new Mesa(1,Estados.Cerrada);
		assertEquals(Estados.Cerrada,mesa.getEstado());
	}
	
	@Test
	public void c_ModuloMesa_CantidadDeMesas() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.generarMesas(10);
		assertEquals(10,moduloMesa.getCantidadMesas());
	}
		
	@Test
	public void d_ModuloMesa_OcuparMesa() { // la mesa 5 quedo ocupada
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.ocuparMesa(5);
		assertEquals(Estados.Ocupada, moduloMesa.getMesa(5).getEstado());
	}
	
	@Test(expected = Error.class) // la mesa 6 quedo cerrada
	public void e_ModuloMesa_OcuparMesaEnEstadoCerrada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(5);
		moduloMesa.ocuparMesa(5);
	}
	
	@Test
	public void f_ModuloMesa_CerrarMesa() { // la mesa 7 quedo cerrada
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(7);
		assertEquals(Estados.Cerrada, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test(expected = Error.class) // la mesa 5 sigue ocupada
	public void g_ModuloMesa_CerrarMesaEnEstadoNoDisponible() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.cerrarMesa(5);
	}
	
	@Test
	public void h_ModuloMesa_PasarADisponibleEstandoCerrada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.pasarMesaADisponible(7);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(7).getEstado());
	}
	
	@Test
	public void i_ModuloMesa_PasarADisponibleEstandoOcupada() {
		ModuloMesa moduloMesa = ModuloMesa.getModuloMesa();
		moduloMesa.pasarMesaADisponible(5);
		assertEquals(Estados.Disponible, moduloMesa.getMesa(5).getEstado());
	}
	

	

	

	
	
	
	
	
	
	
	
	
	
	
	

}
