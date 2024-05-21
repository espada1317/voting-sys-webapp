package voting.sys.webapp.component.tab;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import voting.sys.webapp.api.dto.response.ElectionItemResponseDto;
import voting.sys.webapp.api.service.ElectionService;

import java.util.List;

@CssImport("./styles/button-styles.css")
public class ElectionTab extends Div {

    private static final ElectionService electionService = new ElectionService();

    public ElectionTab() {
        RadioButtonGroup<ElectionItemResponseDto> radioGroup = new RadioButtonGroup<>();
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        List<ElectionItemResponseDto> elections = electionService.getElectionItems();
        radioGroup.setItems(elections);
        radioGroup.setValue(elections.getFirst());
        radioGroup.setRenderer(new ComponentRenderer<>(electionItem -> {
            Span number = new Span(new Text(electionItem.getId() + ". " + electionItem.getName()));
            Text candidate = new Text("Candidate: " + electionItem.getPerson());
            return new Div(new HorizontalLayout(number),
                    new Div(candidate));
        }));

        add(radioGroup);
    }

}
