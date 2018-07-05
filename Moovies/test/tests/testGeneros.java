package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import generos.Genero;
import generos.GeneroEspecifico;
import generos.GeneroGeneral;
import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class testGeneros {
	
	private Genero terror  ;
	private Genero suspenso;
	private Genero vampiros;
	private Genero intriga ;
	private Genero ficcion ;
	private Genero magia   ;
	private Genero espacio ;
	private UsuarioRegular usuario ;
	private UsuarioRegular usuario2;
	private SistemaMoovies moovies ;

	
	@Before
	public void setUp() throws Exception {
		
		terror    = new GeneroGeneral("Terror");
		suspenso  = new GeneroEspecifico("Suspenso", terror);
		vampiros  = new GeneroEspecifico("Vampiros", suspenso);
		intriga   = new GeneroEspecifico("Intriga" , terror);
		
		ficcion   = new GeneroGeneral("Ficcion");
		magia     = new GeneroEspecifico("Magia"  , ficcion );
		espacio   = new GeneroEspecifico("Espacio", ficcion );
		
		usuario   = new UsuarioRegular("usuario1", "contrasenia1", "name1","surname1","M", 10,"Estudiante", 1872);
		usuario2  = new UsuarioRegular("usuario2", "contrasenia2", "name2","surname2","M", 12,"Comerciante",1878);
		moovies   = new SistemaMoovies();
		
		ficcion.addObserver(usuario);
	}

	@Test
	public void testUnGeneroNuevoNoTieneNingunObserver() {
		
		GeneroGeneral accion = new GeneroGeneral("Accion");
		assertEquals(accion.countObservers(), 0);
	}

	@Test
	public void testUnUsuarioSeSuscribeAUnGenero() {

		terror.addObserver(usuario);
		assertEquals(terror.countObservers(), 1);
	}
	
	@Test
	public void testUnUsuarioRecienSuscriptoAUnGeneroNoCuentaConNingunaPeliculaEnNuevosLanzamientos() {
		
		ficcion.addObserver(usuario);
		assertEquals(usuario.getNuevosLanzamientos().size(), 0);
	}
	
	@Test
	public void testUnUsuarioSuscriptoAlGeneroGeneralFiccionRecibioUnNuevoLanzamientoDeLaPeliculaBatmanQueContieneDosGenerosUnoGeneralFiccionYElOtroEspecificoIntriga() {

		usuario.suscribirse(ficcion);
		
		Pelicula batman = mock(Pelicula.class);
		when(batman.getGeneros()).thenReturn(Arrays.asList(ficcion, intriga));
		
		moovies.addPelicula(batman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(usuario.getNuevosLanzamientos().contains(batman));
	}
	
	@Test
	public void testUnUsuarioSuscriptoAlGeneroGeneralFiccionRecibioUnNuevoLanzamientoDeLaPeliculaSupermanQueContieneElGeneroEspecificoIntrigaYElGeneroEspecificoEspacioQueEsSubGeneroDeFiccion() {

		ficcion.addObserver(usuario);
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(espacio, intriga));
		// espacio es subGenero de ficcion
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(usuario.getNuevosLanzamientos().contains(superman));
	}
	
	@Test
	public void testUnUsuarioSuscriptoAlGeneroEspecificoSuspensoRecibioUnNuevoLanzamientoDeLaPeliculaSupermanQueContieneAlGeneroEspecificoMagiaYAlGeneroEspecificoVampirosQueEsSubGeneroDeSuspenso() {

		suspenso.addObserver(usuario);
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(vampiros, magia));
		// vampiros es subGenero de suspenso, y suspenso es subGenero de terror
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(usuario.getNuevosLanzamientos().contains(superman));
	}
	
	@Test
	public void testUnUsuarioSuscriptoAlGeneroGeneralTerrorRecibioUnNuevoLanzamientoDeLaPeliculaSupermanQueContieneAlGeneroEspecificoMagiaYAlGeneroEspecificoVampiros() {

		terror.addObserver(usuario);
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(vampiros, magia));
		// vampiros es subGenero de suspenso, y suspenso es subGenero de terror
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(usuario.getNuevosLanzamientos().contains(superman));
	}
	
	@Test
	public void testDosUsuariosSuscriptosAUnMismoGeneroGeneralRecibieronUnNuevoLanzamientoDeUnaPeliculaQueContieneUnGeneroGeneral() {

		ficcion.addObserver(usuario );
		ficcion.addObserver(usuario2);
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(ficcion, intriga));
		// espacio es subGenero de ficcion
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(  usuario.getNuevosLanzamientos().contains(superman));
		
		assertEquals(usuario2.getNuevosLanzamientos().size(), 1);
		assertTrue(  usuario2.getNuevosLanzamientos().contains(superman));
	}
	
	@Test
	public void testDosUsuariosSuscriptosAUnGeneroRecibieronUnNuevoLanzamientoDeUnaPelicula() {

		ficcion.addObserver(usuario );
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(espacio, intriga));
		// espacio es subGenero de ficcion
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(  usuario.getNuevosLanzamientos().contains(superman));
		
		assertEquals(usuario2.getNuevosLanzamientos().size(), 0);
		assertFalse( usuario2.getNuevosLanzamientos().contains(superman));
	}
	
	@Test
	public void testDosUsuariosSuscriptosADistintosGenerosUnoRecibioUnNuevoLanzamientoDeUnaPeliculaYElOtroNo() {

		ficcion.addObserver(usuario);
		terror.addObserver(usuario2);
		
		Pelicula superman = mock(Pelicula.class);
		when(superman.getGeneros()).thenReturn(Arrays.asList(espacio, magia));
		// espacio es subGenero de ficcion
		
		moovies.addPelicula(superman);
		
		assertEquals(usuario.getNuevosLanzamientos().size(), 1);
		assertTrue(  usuario.getNuevosLanzamientos().contains(superman));
		
		assertEquals(usuario2.getNuevosLanzamientos().size(), 0);
		assertFalse( usuario2.getNuevosLanzamientos().contains(superman));
	}

}
