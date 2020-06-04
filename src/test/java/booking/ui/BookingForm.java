package booking.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BookingForm {
    public static Target SEARCH_FIELD = Target.the("search location field").located(By.id("ss"));
    public static Target ADULT_ADD_BUTTON = Target.the("adult add button").located(By.xpath("//button[@aria-describedby='group_adults_desc' and @data-bui-ref='input-stepper-add-button']"));
    public static Target ADULT_SUBTRACT_BUTTON = Target.the("adult subtract button").located(By.xpath("//button[@aria-describedby='group_adults_desc' and @data-bui-ref='input-stepper-subtract-button']"));
    public static Target CHILDREN_ADD_BUTTON = Target.the("children add button").located(By.xpath("//button[@aria-describedby='group_children_desc' and @data-bui-ref='input-stepper-add-button']"));
    public static Target ROOMS_ADD_BUTTON = Target.the("rooms add button").located(By.xpath("//button[@aria-describedby='no_rooms_desc' and @data-bui-ref='input-stepper-add-button']"));
    public static Target SEARCH_BUTTON = Target.the("search button").located(By.xpath("//button[contains(@class, 'sb-searchbox__button')]"));
    public static Target CUSTOMER_FORM = Target.the("customer form").located(By.id("xp__guests__toggle"));
    public static Target RESULT_TEXT = Target.the("result search").located(By.cssSelector(".sr_header h1"));
}
