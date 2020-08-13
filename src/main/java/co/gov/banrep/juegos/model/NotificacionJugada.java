package co.gov.banrep.juegos.model;

import java.util.List;

public class NotificacionJugada {

	public NotificacionJugada(List<List<Integer>> jugadas, String ultimaJugada) {
		super();
		this.jugadas = jugadas;
		this.ultimaJugada = ultimaJugada;
	}
	private List<List<Integer>> jugadas;
	private String ultimaJugada;
	public List<List<Integer>> getJugadas() {
		return jugadas;
	}
	public void setJugadas(List<List<Integer>> jugadas) {
		this.jugadas = jugadas;
	}
	public String getUltimaJugada() {
		return ultimaJugada;
	}
	public void setUltimaJugada(String ultimaJugada) {
		this.ultimaJugada = ultimaJugada;
	}
}
