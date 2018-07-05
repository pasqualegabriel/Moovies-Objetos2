package moovies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import buscadorDePeliculas.Filtro;
import generos.Genero;
import recomendaciones.Recomendacion;
import recomendaciones.ReocomendacionMasQueridasPorAmigos; 

public class UsuarioRegular extends Usuario implements Observer{
	
	private List<UsuarioRegular> amigos;
	private List<UsuarioRegular> amistadesPendientes;
	private Set<Pelicula> nuevosLanzamientos;
	private Recomendacion recomendacion;

    public UsuarioRegular(String usuario, String contrasenia, String nombre, 
    		String apellido, String genero, int edad, String ocupacion, int codigoPostal) {
    	
    	super(edad,genero,ocupacion,codigoPostal,nombre,apellido);
    	this.setUsuario(usuario);
    	this.setContrasenia(contrasenia);
		this.setAmigos(new ArrayList<UsuarioRegular>());
		this.setAmistadesPendientes(new ArrayList<UsuarioRegular>());
		this.setNuevosLanzamientos(new HashSet<Pelicula>());
		this.setRecomendacion(new ReocomendacionMasQueridasPorAmigos());
	}
    
    // setter de las recomendaciones del usuario
    public void setRecomendacion(Recomendacion reocomendacion) {
		this.recomendacion = reocomendacion;
	}

	// retorna los amigos del usuario
    public List<UsuarioRegular> getAmigos() {
		return this.amigos;
	}
    
    // settea una lista de amigos al usuario regular
	public void setAmigos(List<UsuarioRegular> amigos) {
		this.amigos = amigos;
	}

	// retorna las amistades pendientes del usuario
	public List<UsuarioRegular> getAmistadesPendientes() {
		return this.amistadesPendientes;
	}

	// settea una lista de amistades pendientes al usuario regular
	public void setAmistadesPendientes(List<UsuarioRegular> amistadesPendientes) {
		this.amistadesPendientes = amistadesPendientes;
	}
	
	// el usuario califica una pelicula
	public void calificarPelicula(Pelicula pelicula, int puntaje, SistemaMoovies sistemaMoovies) {
		sistemaMoovies.agregarUnaCalificaicionA(this, pelicula, puntaje);
	}
	
	// retorna el puntaje de una pelicula
	public int puntajeDe(Pelicula pelicula, SistemaMoovies sistemaMoovies) {
		return sistemaMoovies.puntajeDe(this, pelicula);	
	}

	//un usuario envia a otro usuario una solicitud de amistad
	public void enviarSolicitudDeAmistadA(UsuarioRegular usuario) {
		usuario.amistadesPendientes.add(this);		
	}

	// un usuario acepta una solicitud de amistad
	// precondicion: el usuario a aceptar tiene que estar en la lista de amistades pendientes
	public void aceptarSolicitudDeAmistad(UsuarioRegular usuario) {
		
		this.amistadesPendientes.remove(usuario);
		this.amigos.add(usuario);
		usuario.amigos.add(this);
	}

	//un usuario rechaza una solicitud de amistad y esta solicitud se borra de la lista de amistades pendientes
	// precondicion: el usuario a remover de la lista tiene que estar en la lista
	public void rechazarSolicitudDeAmistad(Usuario usuario) {
		
		this.amistadesPendientes.remove(usuario);
	}

	//se borra la amistad reciprocamente entre los usuarios
	//precondicion: la amistad tiene que existir
	public void eliminarAmistad(UsuarioRegular usuario) {
		
		this.amigos.remove(usuario);
		usuario.amigos.remove(this);
	}

	// retorna true si un usuario es amigo de otro
	public boolean esAmigoDe(Usuario usuario) {
		
		return this.amigos.contains(usuario);
	}
	
	// retorna las 10 mejores peliculas que se encuentran en el sistema moovies
	public List<Pelicula> mejoresDiezPeliculas(SistemaMoovies moovies){
		return moovies.mejoresDiezPeliculas();
	}

	// retorna el genero favorito del usuario
	public String generoFavorito(SistemaMoovies sistemaMoovies) {

		return sistemaMoovies.generoFavorito(this);
	}

	// retorna todas las peliculas que vio el usuario
	public List<Pelicula> peliculasVistas(SistemaMoovies sistemaMoovies) {
		
		return sistemaMoovies.peliculasVistasPor(this);
	}

	// un usuario puede ver el genero favorito de otro usuario
	public String generoFavoritoDe(UsuarioRegular usuario, SistemaMoovies sistemaMoovies) {
		
		return usuario.generoFavorito(sistemaMoovies);
	}

	// Agrega una nueva amistad
	public void agregarAmistad(UsuarioRegular usuario) {
		this.amigos.add(usuario);
	}

	// retorna true si un usuario califico la pelicula
	public boolean califico(Pelicula pelicula) {
		
		return pelicula.calificoLaPelicula(this);
	}

	// retorna una lista de calificaciones de una pelicula
	public List<Calificacion> calificacionesDeLaPelicula(Pelicula pelicula) {
		
		return pelicula.getCalificaciones();
	}

	// retorna el raiting de una pelicula
	public int raitingDeLaPelicula(Pelicula pelicula) {

		return pelicula.promedioPuntajes();
	}

	// retorna una lista de usuarios que vieron la pelicula
	public List<Usuario> usuariosQueVieron(Pelicula pelicula) {
		
		return pelicula.usuariosQueLaVieron();
	}

	// retorna las peliculas del sistemaMoovies que cumplen con el filtro
	public List<Pelicula> buscarPeliculas(SistemaMoovies sistemaMoovies, Filtro filtro) {
		return sistemaMoovies.filtrarPeliculas(filtro);
	}
	
	// retorna los nuevosLanzamientos
	public Set<Pelicula> getNuevosLanzamientos() {
		return nuevosLanzamientos;
	}

	// setter de nuevosLanzamientos
	public void setNuevosLanzamientos(Set<Pelicula> nuevosLanzamientos) {
		this.nuevosLanzamientos = nuevosLanzamientos;
	}
	
	// el usuario se suscribe a un genero
	public void suscribirse(Genero genero){
		genero.addObserver(this);
	}

	// el usuario recibe una notificacion de nuevo lanzamiento
	//  y agrega la pelicula sus nuevos lanzamientos
	@Override
	public void update(Observable genero, Object pelicula) {
		this.nuevosLanzamientos.add((Pelicula)pelicula);
	}

	// retorna la recomendacion
	public Recomendacion getRecomendacion() {
		return this.recomendacion;
	}

}
