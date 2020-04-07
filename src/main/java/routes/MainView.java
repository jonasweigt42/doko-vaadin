package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.doko.jonas.DataService;
import com.doko.jonas.dataTypes.Game;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * The main view contains a simple label element and a template element.
 */
@Route("")
@PWA(name = "Doko Tracker", shortName = "Doko")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout
{

	private static final long serialVersionUID = 1L;

	@Inject
	private DataService dataService;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H1 label = new H1("Welcome to Doko Tracker!");
		Button playersButton = new Button("Players");
		playersButton.addClickListener(e -> playersButton.getUI().ifPresent(ui->ui.navigate("playerView")));
		
		Button statsButton = new Button("Stats");
		statsButton.addClickListener(e -> statsButton.getUI().ifPresent(ui->ui.navigate("gameStatsView")));
		// Button click listeners can be defined as lambda expressions
		// Button button = new Button("Say hello", e -> Notification.show(greetService.greet(textField.getValue())));
		
		Grid<Game> gameGrid = new Grid<>(Game.class);
		gameGrid.setItems(dataService.getAllGames());

		Notification.show("Daten für " + dataService.getGameCount() + " Spiele geladen...");

		addClassName("centered-content");

		add(label, playersButton, statsButton);
	}

}
