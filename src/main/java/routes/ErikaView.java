package routes;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route("erikaView")
public class ErikaView extends DokoView
{
	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void init() throws IOException, URISyntaxException
	{
		H1 label = new H1("Erika");
		add(label);

	}
}
