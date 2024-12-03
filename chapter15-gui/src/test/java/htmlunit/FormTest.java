package htmlunit;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

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
//        경고 핸들러 객체 생성
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlSubmitInput submitButton = form.getInputByName("submit");
//        버튼을 클릭하여 경고 메시지를 방생
//        버튼을 클릭한 결과를 resultPage에 저장
//        경고 메시지가 발생했고 페이지가 바뀌지 않았으므로 이 객체를 사용하면 현재 페이지와 이전 페이지의
//        제목을 비교하여 페이지가 변경되지 않았는지 검증할 수 있다
        HtmlPage resultPage = submitButton.click();

        WebAssert.assertTitleEquals(resultPage, page.getTitleText());
        WebAssert.assertTextPresent(resultPage, page.asText());

//        발생한 경고 메시지 리스트를 가져온다
        List<String> collectedAlerts = alertHandler.getCollectedAlerts();
//        예상되는 경고 메시지 리스트를 만든다
        List<String> expectedAlerts = Collections.singletonList("Please enter a value.");

        assertEquals(expectedAlerts, collectedAlerts);
    }

    @Test
    public void testFormNoAlert() throws IOException {
        CollectingAlertHandler alertHandler = new CollectingAlertHandler();
        webClient.setAlertHandler(alertHandler);

        HtmlPage page = webClient.getPage("file:src/main/webapp/formtest.html");
        HtmlForm form = page.getFormByName("validated_form");
        HtmlTextInput input = form.getInputByName("in_text");
        input.setValueAttribute("typing...");

        HtmlSubmitInput submitButton = form.getInputByName("submit");
        HtmlPage resultPage = submitButton.click();

        WebAssert.assertTitleEquals(resultPage, "Result");
        assertTrue(alertHandler.getCollectedAlerts().isEmpty(), "No alerts expected");
    }
}
