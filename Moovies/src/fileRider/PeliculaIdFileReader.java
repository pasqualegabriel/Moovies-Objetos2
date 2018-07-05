package fileRider;

import moovies.Pelicula;

public class PeliculaIdFileReader {
	
	private int idPelicula;
	private Pelicula pelicula;
	
	public PeliculaIdFileReader(int idPelicula, Pelicula pelicula) {
		
		super();
		this.idPelicula=idPelicula;
		this.pelicula=pelicula;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

}
