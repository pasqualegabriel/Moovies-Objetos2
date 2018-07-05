package generos;

import java.util.Observable;

import moovies.Pelicula;

public abstract class Genero extends Observable{
	
	private String tema;
	
	public Genero(String tema){
		super();
		this.setTema(tema);
	}

	// retorna el tema
	public String getTema() {
		return tema;
	}

	// setter del tema
	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public abstract void notificarObservers(Pelicula pelicula);

}
