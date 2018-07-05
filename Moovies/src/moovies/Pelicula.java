package moovies;

import java.util.ArrayList;
import java.util.List;

import generos.Genero;

public class Pelicula {
	
	private String nombre;
	private String fechaDeEstreno;
	private String imdb;
	private String director;
	private List<Genero> generos;
	private List<Calificacion> calificaciones; 
	
	public Pelicula(String nombre, String fechaDeEstreno, 
			String imdb, List<Genero> generos) {
		
		this.setNombre(nombre);
		this.setFechaDeEstreno(fechaDeEstreno);
		this.setIMDB(imdb); 
		this.setGeneros(generos);
		this.setDirector("unknow");
		this.calificaciones= new ArrayList<Calificacion>();
	}
	
	//retorna el nombre de la pelicula
	public String getNombre() {
		return this.nombre;
	}
	
	// cambia el nombre de la pelicula
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// retorna el anio de estreno de la pelicula
	public String getFechaDeEstreno() {
		return this.fechaDeEstreno;
	}
	
	// cambia el anio de estreno de la pelicula
	public void setFechaDeEstreno(String anioDeEstreno) {
		this.fechaDeEstreno = anioDeEstreno;
	}
	
	// retorna el imdb de la pelicula
	public String getIMDB() {
		return this.imdb;
	}
	
	// cambia el imdb de la pelicula
	public void setIMDB(String imdb) {
		this.imdb = imdb;
	}
	
	// retorna los generos de las peliculas
	public List<Genero> getGeneros() {
		return this.generos;
	}
	
	// cambia la lista de generos de una pelicula
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	// retorna el director de la pelicula
	public String getDirector() {
		return director;
	}

	// cambia el director de la pelicula
	public void setDirector(String director) {
		this.director = director;
	}

	// retorna una lista de usuarios que vieron la pelicula
	public List<Usuario> usuariosQueLaVieron() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for(Calificacion c: calificaciones){
			usuarios.add(c.getUsuario());
		}
		return usuarios;
	}

	// retorna la lista de calificaciones de la pelicula
	public List<Calificacion> getCalificaciones(){
		return this.calificaciones;
	}

	// retorna una lista con todos los temas de sus generos
	public List<String> getGenerosStrings() {
		List<String> resGeneros = new ArrayList<String>();
		for(Genero g: this.generos){
			resGeneros.add(g.getTema());
		}
		return resGeneros;
	}

	//retorna el promedio de un puntaje
	public int promedioPuntajes() {
		int total = 0;
		int longitud = 0;
		if(this.puntajes().size()==0){
			return 0;
		}
		for(int puntaje: this.puntajes()){
			total += puntaje;
			longitud += 1;
		}
		return  total / longitud;
	}

	// retorna todos los puntajes que le dieron a la pelicula
	private List<Integer> puntajes() {
		List<Integer> calificaciones = new ArrayList<Integer>();
		for(Calificacion c: this.calificaciones){
			calificaciones.add(c.getPuntaje());
		}
		return calificaciones;
	}

	// dado un usuario y un puntaje agrega una calificacion a la pelicula
	public void agregarCalificacion(UsuarioRegular usuario, int puntaje) {
		
		this.calificaciones.add(new Calificacion(usuario,this,puntaje));
	}

	// retorna la calificacion que le dio el usuario a la pelicula
	// Precondicion: Debe existir al menos una calificacion del <usuario>
	public int puntajeDe(UsuarioRegular usuario) throws RuntimeException {
		for(Calificacion c: this.calificaciones){
			if(c.getUsuario().getUsuario()==usuario.getUsuario()){
				return c.getPuntaje();
			}
		}
		throw new RuntimeException("el usuario no califico la pelicula");
	}

	// retorna true si la pelicula fue calificada por el usuario
	public boolean calificoLaPelicula(UsuarioRegular usuario) {
		for(Calificacion c: this.calificaciones){
			if(c.getUsuario().equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	// Dada una calificacion la pelicula la agrega
	public void agregarCalificacion(Calificacion calificacion) {
		
		this.calificaciones.add(calificacion);		
	}
    
}

