package buscadorDePeliculas;

import java.util.List;

public class CondicionEsIgual extends Condicion {
	
	public CondicionEsIgual(String unCampo){
		super(unCampo);
	}

	@Override
	public boolean resolver(String campo) {
		return campo == this.getCampo();
	}

	@Override
	public boolean resolver(List<String> campos) {
		for(String campo: campos){
			if(campo == this.getCampo()){
				return true;
			}
		}
		return false;
	}

}
