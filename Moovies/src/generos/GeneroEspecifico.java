package generos;

import moovies.Pelicula;

public class GeneroEspecifico extends Genero {
	
	private Genero genero;
	
	public GeneroEspecifico(String tema, Genero terror){
		super(tema);
		this.setGenero(terror);
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public void notificarObservers(Pelicula pelicula) {
		this.notifyObservers(pelicula);
		this.genero.notificarObservers(pelicula);
	}

}
