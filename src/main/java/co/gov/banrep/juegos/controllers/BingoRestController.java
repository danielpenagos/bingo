package co.gov.banrep.juegos.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.gov.banrep.juegos.model.BingoInstancia;
import co.gov.banrep.juegos.model.Carton;
import co.gov.banrep.juegos.model.NotificacionJugada;

@RestController
@CrossOrigin(origins = "*")
public class BingoRestController {

	@Autowired
	private BingoInstancia bingo;
	
	@Autowired
	private CartonesService cartonesService;
	
	@Autowired
	private WebSocketEventNotifier notifier;
	
	@RequestMapping("/jugadas")		
	public NotificacionJugada jugadas() {
		return bingo.notificarJugadas();
	}
	
	@RequestMapping("/nuevaJugada")		
	public String nuevaJugada() {
		bingo.jugarAleatorio();
		return "jugadaEjecutada";
	}
	
	@RequestMapping("/reiniciarJuego")		
	public String reiniciarJuego() throws IOException {
		bingo.reiniciarJuego();
		return "reinicio ejecutado";
	}
	@RequestMapping("/validarCarton")
	public Carton validarCarton(@RequestParam(value = "cartonid")Integer carton){
		Integer[][] cartonRetorno = cartonesService.getCartonesArray().get(carton-1);
		Carton cartonObj = new Carton(carton,cartonRetorno); 
		notifier.notify(cartonObj);
		return cartonObj;
	}
	@RequestMapping("/generarCartones")
	public List<Integer[][]> generarCartones(){
		Random rand = new Random();
		List<Integer[][]> cartones = new ArrayList<Integer[][]>();;
		for(int numCartones =0; numCartones<20;numCartones++) {
			List<Integer>[] listaArreglos = new List[5]; 
			List<Integer> colB = new ArrayList<Integer>();
			List<Integer> colI = new ArrayList<Integer>();
			List<Integer> colN = new ArrayList<Integer>();
			List<Integer> colG = new ArrayList<Integer>();
			List<Integer> colO = new ArrayList<Integer>();
			for(int i=1;i<=15;i++) {
				colB.add(i);
				colI.add(i+15);
				colN.add(i+30);
				colG.add(i+45);
				colO.add(i+60);
			}
			listaArreglos[0]=colB;
			listaArreglos[1]=colI;
			listaArreglos[2]=colN;
			listaArreglos[3]=colG;
			listaArreglos[4]=colO;
			Integer[][] carton = new Integer[5][5];
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					int posicion = rand.nextInt(listaArreglos[i].size());
					int valorAIncluir = listaArreglos[i].get(posicion); 
					carton[i][j]=valorAIncluir;
					listaArreglos[i].remove(new Integer(valorAIncluir));
				}
			}
			cartones.add(carton);
		}
		for(Integer[][] c: cartones) {
			System.out.println("B,I,N,G,O,");
			for(int i=0;i<5;i++) {
				StringBuffer sb = new StringBuffer();
				for(int j=0;j<5;j++) {
					sb.append(c[j][i]);
					sb.append(",");
				}
				System.out.println(sb.toString());
			}
		}
		return cartones;
	}
}
