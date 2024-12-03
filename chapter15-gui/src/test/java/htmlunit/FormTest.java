package htmlunit;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FormTest extends ManageWebClient{

    @Test
    public void testForm() throws IOException {
        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");

        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();

        WebAssert.assertTitleEquals(resultPage, "Result");
    }

    @Test
    public void testFormAlert() throws IOException {
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);


    }
}
