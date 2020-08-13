package co.gov.banrep.juegos.websockets.model;

import org.springframework.web.util.HtmlUtils;

import co.gov.banrep.juegos.model.NotificacionJugada;

public class WebSocketNotification {
	private String content;

	  public WebSocketNotification() {
	  }
	  
	  public WebSocketNotification(WebSocketHelloMessage content) {
		    this.content = "Bienvenido, " + HtmlUtils.htmlEscape(content.getName()) + "!";
	  }

	  public WebSocketNotification(String content) {
	    this.content = "Notificacion, " + HtmlUtils.htmlEscape(content) + "!";
	  }


	  public String getContent() {
	    return content;
	  }

}
