package booking.features.booking;

import booking.abilities.MakeABooking;
import booking.questions.BookingResult;
import booking.tasks.Booking;
import booking.tasks.OpenTheApplication;
import booking.ui.BookingForm;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SerenityRunner.class)
public class BookingStory {

    Actor anna = Actor.named("Anna");

    @Managed(uniqueSession = true, clearCookies = BeforeEachTest)
    public WebDriver herBrowser;

    @Steps
    OpenTheApplication openTheApplication;

    @Before
    public void annaCanBrowseTheWeb() {
        anna.can(BrowseTheWeb.with(herBrowser))
            .can(Objects.requireNonNull(MakeABooking.LoadTestData(anna.getName())));
        herBrowser.manage().window().maximize();
    }

    @After
    public void annaQuitTheWeb() {
        herBrowser.quit();
    }

    @Test
    public void booking_with_data_test() {
        givenThat(anna).wasAbleTo(openTheApplication);

        when(anna).attemptsTo(
                Booking.ToBook(anna.abilityTo(MakeABooking.class))
        );

        then(anna).should(
                seeThat("The number of matched rooms", BookingResult.of(BookingForm.RESULT_TEXT), equalTo(279))
        );
    }
}
