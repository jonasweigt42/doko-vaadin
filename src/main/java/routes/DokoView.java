package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.doko.jonas.DataService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css")
public class DokoView extends VerticalLayout
{
	private static final long serialVersionUID = 1L;
	@Inject
	private DataService dataService;
	
	@PostConstruct
	public void initialize() throws IOException, URISyntaxException
	{
		Button backButton = new Button("back");
		backButton.addClickListener(e -> backButton.getUI().ifPresent(ui -> ui.navigate("")));
		backButton.addClassName("bottom-content");

		addClassName("centered-content");

		add(backButton);
	}

	protected DataService getDataService()
	{
		return dataService;
	}

}
