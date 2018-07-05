package buscadorDePeliculas;

import java.util.List;

public abstract class Condicion {
	
	private String campo;
	
	public Condicion(String unCampo){
		super();
		this.campo = unCampo;
	}

	public String getCampo() {
		return campo;
	}

	public abstract boolean resolver(String campo); 

	public abstract boolean resolver(List<String> campos);

}
