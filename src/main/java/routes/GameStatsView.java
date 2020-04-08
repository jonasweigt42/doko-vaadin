package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.Route;

@Route("gameStatsView")
public class GameStatsView extends DokoView
{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H2 headline = new H2("Gamestats");

		Label gameCount = new Label("Games played: " + getDataService().getGameCount());
		Label soloCount = new Label("Solos played: " + getDataService().getSoloCount());
		Label averageCrorePerGame = new Label("Average Score per Game: " + getDataService().getAverageScorePerGame());
		Label overallMoneyPaid = new Label("Money payd: " + getDataService().getOverallMoneyPaid() + "€");
		
		add(headline, gameCount, soloCount, averageCrorePerGame, overallMoneyPaid);

	}

}
