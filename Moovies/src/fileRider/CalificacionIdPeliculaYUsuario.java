package fileRider;

public class CalificacionIdPeliculaYUsuario {
	
	private int idUsuario;
	private int idPelicula;
	private int calificacion;
	
	public CalificacionIdPeliculaYUsuario(int idUsuario, int idPelicula, int calificacion){
		
		this.idUsuario=idUsuario;
		this.idPelicula=idPelicula;
		this.calificacion=calificacion;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public int getCalificacion() {
		return calificacion;
	}

}
