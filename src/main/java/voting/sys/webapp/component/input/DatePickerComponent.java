package voting.sys.webapp.component.input;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Locale;

@Getter
@CssImport("./styles/dialog-styles.css")
public class DatePickerComponent extends HorizontalLayout {

    private DatePicker datePicker;

    public DatePickerComponent(String datePickerLabel) {
        addClassName("right-center-align-input");
        add(new Span(datePickerLabel));
        add(buildDatePicker());
    }

    public LocalDate getDateFromPicker() {
        return datePicker.getValue();
    }

    private DatePicker buildDatePicker() {
        datePicker = new DatePicker();
        datePicker.addClassName("larger-text-field");
        datePicker.setI18n(createCustomDatePickerI18n());
        datePicker.setRequired(true);
        datePicker.setLocale(Locale.ENGLISH);
        return datePicker;
    }

    private static DatePicker.DatePickerI18n createCustomDatePickerI18n() {
        DatePicker.DatePickerI18n singleFormatI18n = new DatePicker.DatePickerI18n();
        singleFormatI18n.setDateFormat("dd/MM/yyyy");
        return singleFormatI18n;
    }

}
