package com.doko.jonas.routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@RouteAlias(value = "", layout = MainView.class)
@Route(value = "startView", layout = MainView.class)
@PageTitle("Start")
@CssImport("./styles/shared-styles.css")
public class StartView extends VerticalLayout
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H2 headline = new H2("DokoTracker");
		addClassName("centered-content");
		add(headline);
	}
}
