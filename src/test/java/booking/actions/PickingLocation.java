package booking.actions;

import booking.ui.BookingForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class PickingLocation implements Interaction {
    private String location;
    public PickingLocation(String location) {
        this.location = location;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Target selectLocation = Target.the("select location in suggestion").locatedBy("(//ul[contains(@class, 'sb-autocomplete__list') and contains(@class, 'visible')]//li)[1]");
        actor.attemptsTo(
                Click.on(BookingForm.SEARCH_FIELD),
                Enter.theValue(this.location).into(BookingForm.SEARCH_FIELD),
                WaitUntil.the(selectLocation, isClickable()),
                Click.on(selectLocation)
        );
    }

    public static PickingLocation in(String value) {
        return instrumented(PickingLocation.class, value);
    }
}
