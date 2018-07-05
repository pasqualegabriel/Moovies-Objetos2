package generos;

import moovies.Pelicula;

public class GeneroGeneral extends Genero{
	
	public GeneroGeneral(String tema){
		super(tema);
	}

	@Override
	public void notificarObservers(Pelicula pelicula) {
		this.setChanged();
		this.notifyObservers(pelicula);
		this.clearChanged();
	}

}
