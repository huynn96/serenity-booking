package booking.actions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class PickingDate implements Interaction {
    private Date startDate;
    private Date endDate;
    public PickingDate(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String convertDate(Date date) {
        String result = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        result += cal.get(Calendar.DAY_OF_MONTH) + " ";
        result += Month.of(cal.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL , Locale.ENGLISH) + " ";
        result += cal.get(Calendar.YEAR);
        return result;
    }

    @Override
    @Step("{0} books a room from #startDate to #endDate")
    public <T extends Actor> void performAs(T actor) {
        Target startDateTarget = Target.the("start date span")
                .locatedBy("//span[@aria-label='{0}']").of(convertDate(this.startDate));
        Target endDateTarget = Target.the("start date span")
                .locatedBy("//span[@aria-label='{0}']").of(convertDate(this.endDate));
        actor.attemptsTo(
                WaitUntil.the(endDateTarget, isClickable()),
                Click.on(startDateTarget),
                Click.on(endDateTarget)
        );
    }

    public static PickingDate between(Date startDate, Date endDate) {
        return instrumented(PickingDate.class, startDate, endDate);
    }
}
