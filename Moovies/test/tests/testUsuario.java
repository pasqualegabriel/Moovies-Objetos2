package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import fileRider.AdministradorFileReader;
import generos.Genero;
import generos.GeneroGeneral;
import moovies.Administrador;
import moovies.Pelicula;
import moovies.SistemaMoovies;
import moovies.UsuarioRegular;

public class testUsuario {

	private UsuarioRegular usuarioPedro   ;
	private UsuarioRegular usuarioCarlos  ;
	private Administrador  administrador  ;
	@Mock   Pelicula       matrix         ;
	@Mock   Pelicula       narnia         ;
	@Mock   List<Genero>   generosDeMatrix; 
	@Mock   List<Genero>   generosDeNarnia; 
	@Mock   SistemaMoovies sistemaMoovies ;
	
	@Before
	public void setUp() throws Exception {
		
		// Se crea un sistema moovies
		sistemaMoovies = spy(new SistemaMoovies());
		
		// se registran dos usuarios en el sistema
		usuarioPedro  = sistemaMoovies.registrarUsuario("pedroPro","ordep","pedro","perez","M",20,"Estudiante",1876);
		usuarioCarlos = sistemaMoovies.registrarUsuario("carlosPro","solrac","carlos","perez","M",20,"Estudiante",1877);
		//se registra un administrador en el sistema
		administrador = sistemaMoovies.registrarAdministrador("AdminMoovies","123","M", "Juan", "Gonzalez",40,"Estudiante",4056);
		
		// Se crean los generos de matrix y narnia
		generosDeMatrix = (Arrays.asList(new GeneroGeneral("Ficcion"   ),new GeneroGeneral("Aventura")));
		generosDeNarnia = (Arrays.asList(new GeneroGeneral("Fantastica"),new GeneroGeneral("Ficcion" )));
		// se crean dos peliculas
		matrix = spy(new Pelicula("Matrix","10-6-1999","001",generosDeMatrix));
		narnia = spy(new Pelicula("Narnia","01-04-2005","002",generosDeNarnia));
		// se agregan las peliculas al sistema
		sistemaMoovies.addPelicula(matrix);
		sistemaMoovies.addPelicula(narnia);
	}
	 
	@Test
	public void testUnUsuarioTieneNombreDeUsuarioContraseniaNombreApellidoUsuarioContraseniaGeneroEdadOcupacionCodigoPostal() {
		
		assertEquals("pedro",usuarioPedro.getNombre());         // geNombre() retorna el nombre del usuario
		assertEquals("perez",usuarioPedro.getApellido());       // getApellido() retorna el apellido del usuario
		assertEquals("pedroPro",usuarioPedro.getUsuario());     // getUsuario() retorna el usuario del usuario
		assertEquals("ordep",usuarioPedro.getContrasenia());    // getContrasenia() retorna la contraseña del usuario
		assertEquals("M",usuarioPedro.getGenero());             // getGenero() retorna el genero del usuario
		assertEquals(20,usuarioPedro.getEdad());                // getEdad() retorna la edad del usuario
		assertEquals("Estudiante",usuarioPedro.getOcupacion()); // getOcupacion retorna la ocupacion del usuario
		assertEquals(1876,usuarioPedro.getCodigoPostal());      // getCodigoPostal retorna el codigo postal del usuario
	}
	
	@Test
	public void testUnAdministradorTieneNombreDeUsuarioContraseniaNomreApellidoGeneroEdadOcupacionCodigoPostal() {
		
		assertEquals("Juan",administrador.getNombre());          // geNombre() retorna el nombre del administrador
		assertEquals("Gonzalez",administrador.getApellido());    // getApellido() retorna el apellido del administrador
		assertEquals("AdminMoovies",administrador.getUsuario()); // getUsuario() retorna el usuario del administrador
		assertEquals("123",administrador.getContrasenia());      // getContrasenia() retorna la contraseña del administrador
		assertEquals("M",administrador.getGenero());             // getGenero() retorna el genero del administrador
		assertEquals(40,administrador.getEdad());                // getEdad() retorna la edad del administrador
		assertEquals("Estudiante",administrador.getOcupacion()); // getOcupacion retorna la ocupacion del administrador
		assertEquals(4056,administrador.getCodigoPostal());      // getCodigoPostal retorna el codigo postal del administrador
	}
	
	@Test
	public void testUnNuevoUsuarioNoTieneAmistades() {
		
		assertEquals(usuarioCarlos.getAmigos().size(),0);
	}	
	
	@Test
	public void testUnNuevoUsuarioNoTieneAmistadesPendientes() {
		
		assertEquals(usuarioCarlos.getAmistadesPendientes().size(),0);
	}	
	
	@Test  
	public void testCuandoUnUsuarioCalificaUnaPeliculaSeAgregaLaCalificacionEnLaPelicula() {

		// si un usuario vota una pelicula a la pelicula se le agregara el puntaje
		assertEquals(matrix.getCalificaciones().size(), 0);

		int puntaje = 3;
		usuarioPedro.calificarPelicula(matrix, puntaje, sistemaMoovies);
		
		verify(sistemaMoovies).agregarUnaCalificaicionA(usuarioPedro, matrix, puntaje);
		verify(matrix).agregarCalificacion(usuarioPedro,puntaje);
		
		// se comprueva que la calificacion del usuario fue 3
		assertEquals(matrix.getCalificaciones().size(), 1);
		assertEquals(usuarioPedro.puntajeDe(matrix, sistemaMoovies), puntaje);
	}
	
	@Test
	public void testUnUsuarioSabeTodasLasPeliculasQueVio() {
		
		assertFalse(usuarioPedro.peliculasVistas(sistemaMoovies).contains(narnia));
		assertFalse(usuarioPedro.peliculasVistas(sistemaMoovies).contains(matrix));
		// si un usuario bota una pelicula se dara por entendido que vio la pelicula
		usuarioPedro.calificarPelicula(matrix,3,sistemaMoovies);
		usuarioPedro.calificarPelicula(narnia,3,sistemaMoovies);
		
		assertTrue(usuarioPedro.peliculasVistas(sistemaMoovies).contains(narnia));
		assertTrue(usuarioPedro.peliculasVistas(sistemaMoovies).contains(matrix));
	}
	
	@Test
	public void testSiUnUsuarioEnviaUnaSolicitudDeAmistadAOtroUsuarioEsteResiveUnaSolicitudDeAmistad() {
		// un usuario nuevo no tiene amistades pendientes
		assertEquals(usuarioCarlos.getAmistadesPendientes().size(),0);
		// un usuario le manda una solicitud de amistad a otro usuario
		usuarioPedro.enviarSolicitudDeAmistadA(usuarioCarlos);
		// si un usuario recive una solicitud de amistad aumentara el numero de solicitudes pendientes del usuario
		assertEquals(usuarioCarlos.getAmistadesPendientes().size(),1);
		// se verifica que la solicitud sea exactamente la del usuarioPedro
		assertTrue(usuarioCarlos.getAmistadesPendientes().contains(usuarioPedro));
	}
	
	@Test
	public void testCuandoUnUsuarioAceptaUnaSolicitudDeAmistadSeFormaUnaAmistad() {
		// Pedro envia una solicitud de amistad a Carlos 
		usuarioPedro.enviarSolicitudDeAmistadA(usuarioCarlos);
		// Carlos acepta la solicitud de amistad de Pedro
		usuarioCarlos.aceptarSolicitudDeAmistad(usuarioPedro);
		// Carlos y Pedro son amigos
		assertTrue(usuarioCarlos.esAmigoDe(usuarioPedro));
		assertTrue(usuarioPedro.esAmigoDe(usuarioCarlos));
	}
	
	@Test
	public void testCuandoUnUsuarioRechazaUnaSolicitudDeAmistadNoFormaUnaAmistad() {
		// Pedro envia una solicitud de amistad a Carlos 
		usuarioPedro.enviarSolicitudDeAmistadA(usuarioCarlos);
		// Carlos rechaza la solicitud de amistad de Pedro
		usuarioCarlos.rechazarSolicitudDeAmistad(usuarioPedro);
		// Carlos y Pedro no son amigos
		assertFalse(usuarioCarlos.esAmigoDe(usuarioPedro));
		assertFalse(usuarioPedro.esAmigoDe(usuarioCarlos));
	}
	
	@Test 
	public void testCuandoUnUsuarioEliminaAOtroUsuarioLaSePierdeLaAmistadEntreLosUsuarios() {
		// Pedro envia una solicitud de amistad a Carlos
		usuarioPedro.enviarSolicitudDeAmistadA(usuarioCarlos);
		// Carlos acepta la solicitud de amistad de Pedro
		usuarioCarlos.aceptarSolicitudDeAmistad(usuarioPedro);
		// Carlos y Pedro son amigos
		assertTrue(usuarioCarlos.esAmigoDe(usuarioPedro));
		assertTrue(usuarioPedro.esAmigoDe(usuarioCarlos));
		// Carlos elimina la amistad
		usuarioCarlos.eliminarAmistad(usuarioPedro);
		// Carlos y Pedro dejaron de ser amigos
		assertFalse(usuarioCarlos.esAmigoDe(usuarioPedro));
		assertFalse(usuarioPedro.esAmigoDe(usuarioCarlos));
	}
	
	@Test
	public void testUnUsuarioNuevoNoTieneGeneroFavorito() {
		
		// Carlos no califico ninguna pelicula		
		assertEquals(usuarioCarlos.generoFavorito(sistemaMoovies), "No vio ninguna pelicula");
	}
	
	@Test
	public void testSiUnUsuarioCalificaUnaPeliculaTieneUnGeneroFavorito() {
		// Pedro califica dos peliculas
		// generos de matrix: Ficcion, Aventura
		usuarioPedro.calificarPelicula(matrix, 3, sistemaMoovies);
		// generos de narnia: Fantastia, Ficcion
		usuarioPedro.calificarPelicula(narnia, 3, sistemaMoovies);
		
		assertEquals(usuarioPedro.generoFavorito(sistemaMoovies),"Ficcion");
	}
	
	@Test
	public void testUnUsuarioPreguntaElGeneroFavoritoDeOtroUsuario() {
		// Pedro califica dos peliculas
		// generos de matrix: Ficcion, Aventura
		usuarioPedro.calificarPelicula(matrix, 3, sistemaMoovies);
		// generos de narnia: Ficcion, Fantastia
		usuarioPedro.calificarPelicula(narnia, 3, sistemaMoovies);
		
		assertEquals(usuarioCarlos.generoFavoritoDe(usuarioPedro,sistemaMoovies),"Ficcion");
	}
	
	@Test
	public void testUnUsuarioCalificoUnaPelicula() {
		
		usuarioPedro.calificarPelicula(matrix, 4, sistemaMoovies);
		
		assertTrue( usuarioPedro.califico(matrix));
		assertFalse(usuarioPedro.califico(narnia));
	}
	
	@Test
	public void testSiUnSistemaNoTieneNingunaPeliculaAlObtenerLasDiezMejoresPeliculasRetornaUnaListaVacia(){

		SistemaMoovies sistemaMooviesAux = new SistemaMoovies();

		assertTrue(sistemaMoovies.getUsuarios().get(0).mejoresDiezPeliculas(sistemaMooviesAux).isEmpty());
	}
	
	@Test
	public void testSiUnSistemaTieneSoloUnaPeliculaAlObtenerLasDiezMejoresPeliculasRetornaUnaListaConDichaPelicula(){

		SistemaMoovies sistemaMooviesAux = new SistemaMoovies();
		sistemaMooviesAux.addPelicula(matrix);

		assertEquals(1,sistemaMoovies.getUsuarios().get(0).mejoresDiezPeliculas(sistemaMooviesAux).size());
	}
		
	@Test
	public void testMejoresDiezPeliculas(){

		AdministradorFileReader afr = new AdministradorFileReader();
		afr.importarFileReader(sistemaMoovies);

		assertEquals(10,sistemaMoovies.getUsuarios().get(0).mejoresDiezPeliculas(sistemaMoovies).size());
	}
	
	@Test
	public void testUnUsuarioPreguntaLasCalificacionesDeUnaPelicula(){
		
		usuarioPedro.calificarPelicula( matrix, 3, sistemaMoovies);
		usuarioCarlos.calificarPelicula(matrix, 5, sistemaMoovies);
		
		assertEquals(usuarioPedro.calificacionesDeLaPelicula(matrix).get(0).getPuntaje(),3);
		assertEquals(usuarioPedro.calificacionesDeLaPelicula(matrix).get(1).getPuntaje(),5);
	}
	
	@Test
	public void testPeliculasVistasDeUnUsuario(){
		
		usuarioPedro.calificarPelicula(matrix, 3, sistemaMoovies);
		
		assertEquals(usuarioPedro.peliculasVistas(sistemaMoovies).get(0).getNombre(),"Matrix");
	}
	
	@Test
	public void testUnUsuarioAveriguaElRatingDeUnaPelicula(){
		
		usuarioPedro.calificarPelicula( matrix, 3, sistemaMoovies);
		usuarioCarlos.calificarPelicula(matrix, 5, sistemaMoovies);
		
		assertEquals(usuarioPedro.raitingDeLaPelicula(matrix),4);
	}
	
	@Test
	public void testUnUsuarioAveriguaQueUsuariosVieronUnaPelicula(){
		
		usuarioPedro.calificarPelicula( matrix, 3, sistemaMoovies);
		usuarioCarlos.calificarPelicula(matrix, 5, sistemaMoovies);
		
		assertTrue(usuarioPedro.usuariosQueVieron(matrix).contains(usuarioPedro));
		assertTrue(usuarioPedro.usuariosQueVieron(matrix).contains(usuarioCarlos));
	}

	@Test
	public void testAmistadesPendientesDeUnUsuario() {
		
		usuarioPedro.enviarSolicitudDeAmistadA(usuarioCarlos);
		
		assertTrue(usuarioCarlos.getAmistadesPendientes().contains(usuarioPedro));
	}
	
	@Test
	public void testSiUnSistemaNoTieneNingunUsuarioAlObtenerLosDiezUsuariosMasActivosRetornaUnaListaVacia(){
		
		SistemaMoovies sistemaMooviesAux = new SistemaMoovies();
		
		assertTrue(administrador.usuariosMasActivos(sistemaMooviesAux).isEmpty());
	}
	
	@Test
	public void testUnSistemaConSoloUnUsuarioAlObtenerLosDiezUsuariosMasActivosRetornaUnaListaConDichoUsuario(){
		
		SistemaMoovies sistemaMooviesAux = new SistemaMoovies();
		sistemaMooviesAux.addUsuario(usuarioCarlos);
		
		assertEquals(1, administrador.usuariosMasActivos(sistemaMooviesAux).size());
	}
	
	@Test
	public void testDiezUsuariosMasActivos(){

		AdministradorFileReader afr = new AdministradorFileReader();
		afr.importarFileReader(sistemaMoovies);
		
		assertEquals(10, administrador.usuariosMasActivos(sistemaMoovies).size());
	}

}
