package routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.doko.jonas.dataCollect.PlayerPool;
import com.doko.jonas.dataTypes.Player;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("playerView")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css")
public class PlayerView extends VerticalLayout
{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		
		Button button = new Button("back");
		button.addClickListener(e -> button.getUI().ifPresent(ui->ui.navigate("")));

		List<Player> players = PlayerPool.getAllPlayers();
		Grid<Player> playerGrid = new Grid<>(Player.class);
		playerGrid.setItems(players);

		addClassName("centered-content");

		add(button, playerGrid);

	}
	
}
