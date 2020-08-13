package co.gov.banrep.juegos.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.gov.banrep.juegos.controllers.WebSocketEventNotifier;

@Component
public class BingoInstancia {
	Logger logger = LogManager.getLogger(BingoInstancia.class);
	private List<Integer> columnaB = new ArrayList<Integer>();
	private List<Integer> columnaI = new ArrayList<Integer>();
	private List<Integer> columnaN = new ArrayList<Integer>();
	private List<Integer> columnaG = new ArrayList<Integer>();
	private List<Integer> columnaO = new ArrayList<Integer>();
	private Bingo bingo = new Bingo();
	private List<Integer>balotas=new ArrayList<Integer>();
	private String jugada="";
	
	@Autowired
	private WebSocketEventNotifier notifier;
	
	public BingoInstancia() {
		super();
		for(int i: bingo.bingo) {
			balotas.add(i);
		}
	}
	public void reiniciarJuego() {
		columnaB = new ArrayList<Integer>();
		columnaI = new ArrayList<Integer>();
		columnaN = new ArrayList<Integer>();
		columnaG = new ArrayList<Integer>();
		columnaO = new ArrayList<Integer>();
		List<Integer>nuevasBalotas=new ArrayList<Integer>();
		for(int i: bingo.bingo) {
			nuevasBalotas.add(i);
		}
		balotas=nuevasBalotas;
		jugada="";
		notificarJugada(jugada);
	}
	
	private void agregarValor(int valor) {
		if(valor<=15) {
			columnaB.add(valor);
			jugada = "B"+valor;
		}else if(valor<=30) {
			columnaI.add(valor);
			jugada = "I"+valor;
		}else if(valor<=45) {
			columnaN.add(valor);
			jugada = "N"+valor;
		}else if(valor<=60) {
			columnaG.add(valor);
			jugada = "G"+valor;
		}else {
			columnaO.add(valor);
			jugada = "O"+valor;
		}
		notificarJugada(jugada);
	}
	
	/**
	 * Para websockets
	 * @param jugada
	 */
	private void notificarJugada(String jugada) {
		notifier.notify(new NotificacionJugada(retornarJugadas(), jugada));
	}
	
	public NotificacionJugada notificarJugadas() {
		return new NotificacionJugada(retornarJugadas(), jugada);
	}
	
	public void jugarAleatorio() {
		Random rand = new Random();
		if(balotas.size()>0) {
			
			int posicionBalota =rand.nextInt(balotas.size());
			logger.info("tamaño del arreglo: {}, posicionBalota: {} ",balotas.size(), posicionBalota);
			Integer balota = balotas.get(posicionBalota);
			agregarValor(balota);
			balotas.remove(posicionBalota);
			logger.info("balota jugada {}",balota);
		}else {
			logger.info("ya no hay jugadas posibles. fin del juego");
		}
	}
	
	public List<List<Integer>>retornarJugadas(){
		List<List<Integer>> retorno = new ArrayList<List<Integer>>();
		retorno.add(balotas);
		retorno.add(columnaB);
		retorno.add(columnaI);
		retorno.add(columnaN);
		retorno.add(columnaG);
		retorno.add(columnaO);
		return retorno;
	}
	
	
	public List<Integer> getColumnaB() {
		return columnaB;
	}
	
	public List<Integer> getColumnaI() {
		return columnaI;
	}
	
	public List<Integer> getColumnaN() {
		return columnaN;
	}
	
	public List<Integer> getColumnaG() {
		return columnaG;
	}
	
	public List<Integer> getColumnaO() {
		return columnaO;
	}
	
	

}
