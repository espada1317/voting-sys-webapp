package voting.sys.webapp.component.tab;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import voting.sys.webapp.api.dto.request.ElectionItemRequestDto;
import voting.sys.webapp.api.dto.response.ElectionItemResponseDto;
import voting.sys.webapp.api.service.ElectionService;

import java.util.List;

import static voting.sys.webapp.util.DialogValuesConstant.SAVE_BUTTON_TEXT;

@CssImport("./styles/button-styles.css")
public class ElectionTab extends Div {

    private static final ElectionService electionService = new ElectionService();

    private static RadioButtonGroup<ElectionItemResponseDto> radioGroup;

    private static Button saveButton;

    private static Div statusDiv = new Div();

    public ElectionTab() {
        add(getRadioGroup());
        add(new Div(getSaveButton()));

        statusDiv.setVisible(true);
        add(statusDiv);

        if (electionService.checkElectionVote(getIdnpOfCurrentLoggedUsers())) {
            radioGroup.setEnabled(false);
            statusDiv.setText("Already voted!");
            saveButton.setVisible(false);
        }
    }

    public static void refreshRadioGroup() {
        if (electionService.checkElectionVote(getIdnpOfCurrentLoggedUsers())) {
            radioGroup.setEnabled(false);
            statusDiv.setText("Voted successfully!");
            saveButton.setVisible(false);
        }
    }

    private static RadioButtonGroup<ElectionItemResponseDto> getRadioGroup() {
        radioGroup = new RadioButtonGroup<>();
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

        return radioGroup;
    }

    private static Button getSaveButton() {
        saveButton = new Button(SAVE_BUTTON_TEXT);
        saveButton.addClickListener(buttonClickEvent -> {
            ElectionItemRequestDto electionItemRequestDto =
                    ElectionItemRequestDto.builder()
                            .idnp(getIdnpOfCurrentLoggedUsers())
                            .electionId(radioGroup.getValue().getId())
                            .build();

            if (electionService.saveElectionVote(electionItemRequestDto)) {
                refreshRadioGroup();
            }
        });
        return saveButton;
    }

    private static String getIdnpOfCurrentLoggedUsers() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
