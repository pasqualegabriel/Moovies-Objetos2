package moovies;

import java.util.ArrayList;
import java.util.List;

import buscadorDePeliculas.Filtro;
import generos.Genero;
import recomendaciones.Recomendacion;

public class SistemaMoovies {
	
	private String proximoIMDB;
	private List<UsuarioRegular> usuarios;
	private List<Pelicula> peliculas;
	private List<Administrador> administradores; 
	 
	public SistemaMoovies() {
		this.proximoIMDB = "001";
		this.usuarios = new ArrayList<UsuarioRegular>();
		this.peliculas = new ArrayList<Pelicula>();
		this.administradores = new ArrayList<Administrador>();
	} 

	// se agrega una pelicula al sistema
	public Pelicula agregarPelicula(String nombre, 
			String fechaDeEstreno, String imdb, List<Genero> generosPelicula) {
		
		Pelicula pelicula = new Pelicula(nombre, fechaDeEstreno, this.proximoIMDB, generosPelicula);
		int n = Integer.parseInt(this.proximoIMDB);
		n++;
		this.proximoIMDB = String.valueOf(n);
		this.proximoIMDB = Integer.toString(n);
		this.addPelicula(pelicula);
		return pelicula;
	}
	
	// se agrega un usuario regular al sistema
	public UsuarioRegular registrarUsuario (
			String usuario, String contrasenia, String nombre, String apellido,
			String genero, int edad, String ocupacion, int codigoPostal) throws RuntimeException {
		
		if(this.usuarioExistente(usuario)){
			throw new RuntimeException("El usuario ya esta en uso");
		}
		UsuarioRegular nuevoUsuario = new UsuarioRegular(usuario, contrasenia,	
				nombre, apellido, genero, edad, ocupacion, codigoPostal);
		this.usuarios.add(nuevoUsuario);
		return nuevoUsuario;
	}

	// retorna true si el nombre de usuario existe en el sistema
	private boolean usuarioExistente(String usuario) {
		//boolean res = false;
		for(Usuario u: this.usuarios){
			if(u.getUsuario()==usuario){
				return true;
			}
		}
		return false;
	}

	// retorna las 10 mejores peliculas
	public List<Pelicula> mejoresDiezPeliculas(){
		
		List<Pelicula> peliculas = this.getPeliculas();
		List<Pelicula> res = new ArrayList<Pelicula>();
		if(peliculas.isEmpty()){
			return peliculas;
		}
		int cont = 1;
		Pelicula mejorPelicula = this.mejorPelicula(peliculas);
		peliculas.remove(mejorPelicula);
		res.add(mejorPelicula);
		while(cont < 10 && !peliculas.isEmpty()){
			mejorPelicula = this.mejorPelicula(peliculas);
			peliculas.remove(mejorPelicula);
			res.add(mejorPelicula);
			cont++;
		}
		peliculas.addAll(res);
		return res;
	}

	// retorna la mejor pelicula
	// Precondicion: peliculas no es vacia
	private Pelicula mejorPelicula(List<Pelicula> peliculas) {
		
		Pelicula mejorPelicula = peliculas.get(0);
		for(Pelicula p: peliculas){
			if(p.promedioPuntajes()>mejorPelicula.promedioPuntajes()){
				mejorPelicula = p;
			}
		}
		return mejorPelicula;
	}
	
	// retorna una lista de los usuarios mas acrivos
	public List<UsuarioRegular> usuariosMasActivos() {
		
		List<UsuarioRegular> res = new ArrayList<UsuarioRegular>();
		List<UsuarioRegular> usuarios = this.getUsuarios();
		if(usuarios.isEmpty()){
			return usuarios;
		}
		int cont = 1;
		UsuarioRegular masActivo = this.usuarioMasActivo(usuarios);
		usuarios.remove(masActivo);
		res.add(masActivo);
		while(cont < 10 && !usuarios.isEmpty()){
			
			masActivo = this.usuarioMasActivo(usuarios);
			usuarios.remove(masActivo);
			res.add(masActivo);
			cont++;
		}
		usuarios.addAll(res);
		return res;
	}
	
	// Dada una lista retorna al usuario mas activo
	// Precondicion: usuarios no es vacia
	public UsuarioRegular usuarioMasActivo(List<UsuarioRegular> usuarios) {
		
		UsuarioRegular masActivo = usuarios.get(0);
		for(UsuarioRegular u: usuarios){
			if(this.peliculasVistasPor(u).size() > this.peliculasVistasPor(masActivo).size()){
				masActivo = u;
			}
		}
		return masActivo;
	}

	// retorna la lista de administradores del sistema
	public List<Administrador> getAdministradores() {
		return administradores;
	}

	// registra a un administrador al sistema
	public Administrador registrarAdministrador(String usuario, String contrasenia, String genero, 
			String nombre, String apellido, int edad, String ocupacion, int codigoPostal) {
		
		// No verifica que el codigo de admin sea correcto
		Administrador admin = new Administrador(usuario, genero,contrasenia,
				nombre, apellido, edad , ocupacion, codigoPostal);
		this.administradores.add(admin);
		return admin;
	}
	
	// retorna la lista de usuarios del sistema
	public List<UsuarioRegular> getUsuarios(){
		return this.usuarios;
	}

	// retorna la lista de peliculas del sistema
	public List<Pelicula> getPeliculas(){
		return this.peliculas;
	}

	// agrega nueva una calificacion a una pelicula 
	public void agregarUnaCalificaicionA(UsuarioRegular usuario, Pelicula pelicula, int puntaje) {
		
		pelicula.agregarCalificacion(usuario,puntaje);
	}

	//retorna el puntaje que le dio un usuario a una pelicula
	public int puntajeDe(UsuarioRegular usuario, Pelicula pelicula) {

		return pelicula.puntajeDe(usuario);
	}

	// retorna las peliculas que vio el usuario
	public List<Pelicula> peliculasVistasPor(UsuarioRegular usuario) {
		List<Pelicula> res = new ArrayList<Pelicula>();
		for(Pelicula p: this.peliculas){
			if(p.calificoLaPelicula(usuario)){
				res.add(p);
			}
		} 
		return res;
	}

	// agrega una pelicula al sistema
	public void addPelicula(Pelicula p) {
		this.peliculas.add(p);
		for(Genero g: p.getGeneros()){
			g.notificarObservers(p);
		}
	} 

	// agrega un usuario regular al sistema
	public void addUsuario(UsuarioRegular u) {
		this.usuarios.add(u);		
	}
	
	// retorna el genero favorito del usuario
	public String generoFavorito(UsuarioRegular usuario) {
		List<String> listaGeneros = new ArrayList<String>();
	    for (Pelicula p: usuario.peliculasVistas(this)) {
	        listaGeneros.addAll(p.getGenerosStrings());
	    }
		return this.mayorGenero(listaGeneros);
	}

	// retorna el genero mas visto del usuario
	private String mayorGenero(List<String> listaGeneros) {
		if(listaGeneros.isEmpty()){
			return "No vio ninguna pelicula";
		}
		String res = listaGeneros.get(0);
		listaGeneros.remove(0);
		for(String g: listaGeneros){
			if(this.cantApariciones(listaGeneros, g)>
			this.cantApariciones(listaGeneros, res)){
				res = g;
	    	}
		}
		return res;
	}
	
	private int cantApariciones(List<String> generos, String genero) {
		
		int res = 0;
		for(String g: generos){
			if(g==genero){
				res++;
			}
		}
		return res;
	}

	public List<Pelicula> filtrarPeliculas(Filtro filtro) {
		List<Pelicula> peliculasFiltradas = new ArrayList<Pelicula>();
		for(Pelicula p: this.peliculas){
			if(filtro.filtrarPelicula(p)){
				peliculasFiltradas.add(p);
			}
		}
		return peliculasFiltradas;
	}

	public void cambiarRecomendacion(UsuarioRegular usuario, Recomendacion recomendacion) {
		usuario.setRecomendacion(recomendacion);
	}

	public List<Pelicula> recomendarPeliculas(UsuarioRegular usuario) {
		
		return usuario.getRecomendacion().recomendarPeliculas(usuario, this);
	}

	public int ratingPromedio() {
		int puntajesTotales = 0;
		for(Pelicula p: this.peliculas){
			puntajesTotales += p.promedioPuntajes();
		}
		return puntajesTotales / this.peliculas.size();
	}
	
}

