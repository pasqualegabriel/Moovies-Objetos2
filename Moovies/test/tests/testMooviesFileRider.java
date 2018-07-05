package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fileRider.AdministradorFileReader;
import moovies.SistemaMoovies;

public class testMooviesFileRider {
	
	private AdministradorFileReader administradorFileReader;
	private SistemaMoovies sistemaMoovies;
	
	@Before
	public void setUp(){
		
		administradorFileReader = new AdministradorFileReader();
		sistemaMoovies = new SistemaMoovies();
		
		// Se importan todos los csv al sistema moovies 
		administradorFileReader.importarFileReader(sistemaMoovies);
	}
	
	@Test
	public void testUnSistemaTieneA943Usuarios() {
		
		assertEquals(sistemaMoovies.getUsuarios().size(), 943);
	}
	
	@Test
	public void testUnSistemaTieneA1682Peliculas() {
		
		assertEquals(sistemaMoovies.getPeliculas().size(), 1682);
	}
	
	@Test
	public void testUnUsuarioDelSistemaTiene8Amigos() {
		
		assertEquals(sistemaMoovies.getUsuarios().get(0).getAmigos().size(), 8);
	}
	
	@Test
	public void testUnaPeliculaDelSistemaTiene452Calificaciones() {
		
		assertEquals(sistemaMoovies.getPeliculas().get(0).getCalificaciones().size(), 452);
	}

}
