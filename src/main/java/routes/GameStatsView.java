package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.doko.jonas.DataService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("gameStatsView")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css")
public class GameStatsView extends VerticalLayout
{

	private static final long serialVersionUID = 1L;

	@Inject
	private DataService dataService;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H2 headline = new H2("Gamestats");
		Button backButton = new Button("back");
		backButton.addClickListener(e -> backButton.getUI().ifPresent(ui -> ui.navigate("")));

		Text gameCount = new Text("Games played: " + dataService.getGameCount());
		Text soloCount = new Text("Solos played: " + dataService.getSoloCount());
		Text averageCrorePerGame = new Text("Average Score per Game: " + dataService.getAverageScorePerGame());
		Text overallMoneyPaid = new Text("Money payd: " + dataService.getOverallMoneyPaid() + "€");
		
		
		addClassName("centered-content");

		add(headline, gameCount, soloCount, averageCrorePerGame, overallMoneyPaid, backButton);

	}

}
