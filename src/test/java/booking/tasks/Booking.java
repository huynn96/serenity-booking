package booking.tasks;

import booking.abilities.MakeABooking;
import booking.actions.*;
import booking.ui.BookingForm;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Booking implements Task {
    private MakeABooking makeABooking;
    protected Booking(MakeABooking makeABooking){
        this.makeABooking = makeABooking;
    }

    public static Booking ToBook(Ability makeABooking) {
        return instrumented(Booking.class, makeABooking);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                PickingLocation.in(makeABooking.getDestination()),
                PickingDate.between(makeABooking.getStartTrip(), makeABooking.getEndTrip()),
                PickingCustomer.adult(makeABooking.getAdult()).child(makeABooking.getChildren()).rooms(makeABooking.getRooms()),
                Click.on(BookingForm.SEARCH_BUTTON)
        );
    }
}
