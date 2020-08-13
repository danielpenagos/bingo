package co.gov.banrep.juegos.model;

import java.util.ArrayList;
import java.util.List;

public class Carton {
	private Integer id;
	private Integer[][] carton;
	public Carton(Integer id, Integer[][] carton) {
		super();
		this.id = id;
		this.carton = carton;
	}
	public Integer getId() {
		return id;
	}
	/*public Integer[][] getCarton() {
		return carton;
	}*/
	
	public List<FilaCarton> getCarton() {
		List<FilaCarton> filas = new ArrayList<FilaCarton>();
		for(int i=0;i<5;i++) {
			Integer valB=carton[i][0];
			Integer valI=carton[i][1];
			Integer valN=carton[i][2];
			Integer valG=carton[i][3];
			Integer valO=carton[i][4];
			FilaCarton fila = new FilaCarton(valB,valI,valN,valG,valO);
			filas.add(fila);
		}
		return filas;
	}
	

}
