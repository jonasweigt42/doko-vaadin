package com.doko.jonas.routes;

import java.text.DecimalFormat;

import javax.inject.Inject;

import com.doko.jonas.dataTypes.Player;
import com.doko.jonas.service.DataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("playerView")
@PageTitle("ViewForPlayer")
@CssImport("./styles/shared-styles.css")
public class PlayerView extends VerticalLayout implements HasUrlParameter<String>
{
	private static final long serialVersionUID = 1L;
	@Inject
	private DataService dataService;
	
	@Override
	public void setParameter(BeforeEvent event, String parameter)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		H2 label = new H2(parameter);
		Player player = dataService.getPlayerByName(parameter);
		
		int anzahlSpiele = dataService.getGameCount();
		Label score = new Label("overallScore: " + player.getOverallScore());
		
		Label label2 = new Label(dataService.getWonPercentagePerPlayer(player) + " % gewonnen");
		Label label3 = new Label(dataService.getWonPercentagePerPlayerWhenStart(player) + " % gewonnen und rausgekommen");
		Label label4 = new Label("Punkte pro Spiel: " + df.format((double) player.getOverallScore() / (double) anzahlSpiele));
		Label label5 = new Label("Anzahl Solos: " + dataService.getSolosForPlayer(player).size() + " - " + dataService.getSoloWonPercentagePerPlayer(player) + "% gewonnen");

		Label label6 = new Label("Spiele gegeben: " + dataService.getGamesDealtByPlayer(player).size());

		Button backButton = new Button("back");
		backButton.addClickListener(e -> backButton.getUI().ifPresent(ui -> ui.navigate("playersView")));
		backButton.addClassName("bottom-content");

		addClassName("centered-content");

		add(label, score, label2, label3, label4, label5, label6, backButton);
	}

}
