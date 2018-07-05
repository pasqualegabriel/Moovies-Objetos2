package fileRider;

import moovies.UsuarioRegular;

public class UsuarioIdFileReader {
	
	private int idUsuario;
	private UsuarioRegular usuario;
	
	public UsuarioIdFileReader(int idUsuario, UsuarioRegular usuario){
		
		super();
		this.idUsuario=idUsuario;
		this.usuario=usuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public UsuarioRegular getUsuario() {
		return usuario;
	}

}
