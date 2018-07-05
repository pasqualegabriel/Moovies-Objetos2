package moovies;

public class Calificacion {
	
	private UsuarioRegular usuario;
	private Pelicula pelicula;
	private int rating;

	public Calificacion(UsuarioRegular usuario, Pelicula pelicula, int puntaje) {

		this.setUsuario(usuario);
		this.setPelicula(pelicula);
		this.rating=puntaje;
	}
	
	// setter de usuario
	private void setUsuario(UsuarioRegular usuario) {
		this.usuario = usuario;
	}

	// retorna al usuario de la calificacion
	public UsuarioRegular getUsuario() {
		return usuario;
	}

	// retorna el puntaje de la calificacion
	public int getPuntaje() {
		return rating;
	}

	// retorna la pelicula de la calificacion
	public Pelicula getPelicula() {
		return pelicula;
	}

	// setter de pelicula
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

}
