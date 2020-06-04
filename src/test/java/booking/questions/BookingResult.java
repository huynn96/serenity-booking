package booking.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingResult implements Question<Integer> {
    private Target resultTarget;
    private int items;

    public BookingResult(Target target) {
        this.resultTarget = target;
    }

    @Override
    public Integer answeredBy(Actor actor) {
        String bookingResult = Text.of(this.resultTarget).viewedBy(actor).asString();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(bookingResult);
        String theGroup = "0";
        if (m.find()) {
            theGroup = m.group(0);
        }
        items = Integer.parseInt(theGroup);
        return items;
    }

    public static BookingResult of(Target target) {
        return new BookingResult(target);
    }
}
