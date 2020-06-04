package booking.actions;

import booking.ui.BookingForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class PickingCustomer implements Interaction {
    private int adult;
    private int children;
    private int rooms;
    public PickingCustomer(int adult, int children, int rooms) {
        this.adult = adult;
        this.children = children;
        this.rooms = rooms;
    }

    @Override
    @Step("{0} books #rooms rooms for #adult adults and #children childrent")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BookingForm.CUSTOMER_FORM),
                WaitUntil.the(BookingForm.ADULT_ADD_BUTTON, isClickable())
        );
        for (int i = 0; i < Math.abs(this.adult - 2); i++) {
            if (this.adult - 3 < 0) {
                actor.attemptsTo(
                        Click.on(BookingForm.ADULT_SUBTRACT_BUTTON)
                );
            } else {
                actor.attemptsTo(
                        Click.on(BookingForm.ADULT_ADD_BUTTON)
                );
            }
        }
        for (int i = 0; i < this.children; i++) {
            actor.attemptsTo(
                    Click.on(BookingForm.CHILDREN_ADD_BUTTON)
            );
        }
        for (int i = 0; i < Math.abs(this.rooms - 1); i++) {
            actor.attemptsTo(
                    Click.on(BookingForm.ROOMS_ADD_BUTTON)
            );
        }
    }

    public static class PickingCustomerBuilder {
        private int adult;
        private int children;

        public PickingCustomerBuilder(int adult) {
            this.adult = adult;
        }

        public PickingCustomerBuilder child(int children) {
            this.children = children;
            return this;
        }

        public PickingCustomer rooms(int rooms) {
            return instrumented(PickingCustomer.class, adult, children, rooms);
        }

    }

    public static PickingCustomerBuilder adult(int adult) {
        return new PickingCustomerBuilder(adult);
    }
}
