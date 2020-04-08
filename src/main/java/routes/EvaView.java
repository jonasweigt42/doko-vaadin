package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("evaView")
public class EvaView extends DokoView
{
	private static final long serialVersionUID = 1L;
	
	public Integer exampleId;
	
	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H1 label = new H1("Eva");
		add(label);
		
		
//		System.out.println("Gesamtpunkte: " + p.getOverallScore() + ", "
//				+ dataCollector.getWonPercentagePerPlayer(p) + " % gewonnen, "
//				+ dataCollector.getWonPercentagePerPlayerWhenStart(p) + " % gewonnen und rausgekommen");
//
//		System.out.println("Punkte pro Spiel: " + df.format((double) p.getOverallScore() / (double) anzahlSpiele)
//				+ ", Anzahl Solos: " + dataCollector.getSolosForPlayer(p).size() + " - "
//				+ dataCollector.getSoloWonPercentagePerPlayer(p) + "% gewonnen");
//
//		System.out.println("Spiele gegeben: " + dataCollector.getGamesDealtByPlayer(p).size());


	}
}
