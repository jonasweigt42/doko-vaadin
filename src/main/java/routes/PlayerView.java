package routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import com.doko.jonas.dataCollect.PlayerPool;
import com.doko.jonas.dataTypes.Player;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;

@Route("playerView")
public class PlayerView extends DokoView
{

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		List<Player> players = PlayerPool.getAllPlayers();
		Grid<Player> playerGrid = new Grid<>(Player.class);
		playerGrid.setItems(players);
		playerGrid
				.addItemClickListener(e -> playerGrid.getUI()
						.ifPresent(ui -> ui.navigate(e.getItem().getName().toLowerCase() + "View")));

		add(playerGrid);
	}

}
