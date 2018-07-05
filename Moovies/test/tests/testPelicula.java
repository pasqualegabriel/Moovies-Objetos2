package tests;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import generos.Genero;
import generos.GeneroGeneral;
import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class testPelicula {
	
	private Pelicula matrix;
	private List<Genero> generosMatrix; 
	
	@Before
	public void setUp() throws Exception {

		generosMatrix = Arrays.asList(new GeneroGeneral("accion"),new GeneroGeneral("ficcion"));
		matrix = new Pelicula("Matrix","01-04-2002","001",generosMatrix);
	} 

	@Test
	public void testUnaPeliculaTieneUnNombre() {
		assertEquals("Matrix", matrix.getNombre());
	}
	
	@Test
	public void testUnaPeliculaTieneUnAnioDeEstreno() {
		assertEquals("01-04-2002", matrix.getFechaDeEstreno());
	}
	
	@Test
	public void testUnaPeliculaTieneUnIMDB() {
		assertEquals("001", matrix.getIMDB());
	}
	
	@Test
	public void testUnaPeliculaTieneUnDirector() {
		matrix.setDirector("Lucas");
		assertEquals("Lucas", matrix.getDirector());
	}
	
	@Test
	public void testUnaPeliculaTieneUnaListaDeGeneros() {
		assertEquals(2, matrix.getGeneros().size());
	}
	
	@Test
	public void testUnaNuevaPeliculaNoTieneCalificaciones() {
		assertEquals(0, matrix.getCalificaciones().size());
	}

	@Test
	public void testUnaPeliculaSinCalificarTieneUnPromedioDeCero() {
		assertEquals(0, matrix.promedioPuntajes());
	}
	
	@Test
	public void testUnaPeliculaCalificadaTieneUnRatingDistintoDeCero() {
		
		SistemaMoovies sistemaMoovies = new SistemaMoovies();
		UsuarioRegular usuario = sistemaMoovies.registrarUsuario("pepe", "1234", "Ricardo","Perez","M", 10,"Estudiante",1872);
		usuario.calificarPelicula(matrix, 5, sistemaMoovies);
		assertEquals(5, matrix.promedioPuntajes());
	}

}
