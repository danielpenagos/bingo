package co.gov.banrep.juegos.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.gov.banrep.juegos.model.BingoInstancia;

@Component
public class ModelUpdateService {
	Logger logger = LogManager.getLogger(ModelUpdateService.class);
	
	@Autowired
	private BingoInstancia juego;


	public void executeUpdateJob() {
		/*
		logger.info("Ejecucion de tarea programada");
		for(int i=0; i<65; i++) {
			juego.jugarAleatorio();
		}
		*/
	}
	
	
}
