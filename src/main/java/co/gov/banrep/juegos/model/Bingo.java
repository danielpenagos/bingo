package co.gov.banrep.juegos.model;

public class Bingo {
	public final int TAMANIO_TABLERO=75;
	public int[] bingo = new int[TAMANIO_TABLERO];
	
	public Bingo() {
		super();
		for(int i=0;i<TAMANIO_TABLERO;i++) {
			bingo[i]=i+1;
		}
	}

	
	
	

}
