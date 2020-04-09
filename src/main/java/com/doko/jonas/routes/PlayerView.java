package com.doko.jonas.routes;

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
		H2 label = new H2(parameter);
		Player player = dataService.getPlayerByName(parameter);
		
		Label score = new Label("overallScore: " + player.getOverallScore());
		
		Button backButton = new Button("back");
		backButton.addClickListener(e -> backButton.getUI().ifPresent(ui -> ui.navigate("playersView")));
		backButton.addClassName("bottom-content");

		addClassName("centered-content");

		add(label, score, backButton);
	}

}
