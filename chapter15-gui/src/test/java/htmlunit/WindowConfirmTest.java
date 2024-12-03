package htmlunit;

import com.gargoylesoftware.htmlunit.CollectingAlertHandler;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WindowConfirmTest extends ManageWebClient{

    @Test
    public void testWindowConfirm() throws FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title></head><body onload='confirm(\"Confirm Message\")'></body></html>";
        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockWebConnection = new MockWebConnection();
        final List<String> confirmMessage = new ArrayList<>();

//        테스트 셋업
//        람다식으로 사용자 정의한 컨펌 핸들러를 설정
//        이 핸들러는 컨펌 창이 떴을 경우 컨펌 메시지를 리스트에 추가한다
        webClient.setConfirmHandler((page, message) -> {
            confirmMessage.add(message);

            return true;
        });

        mockWebConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockWebConnection);

//        테스트 진행
        HtmlPage firstPage = webClient.getPage(testUrl);

        WebAssert.assertTitleEquals(firstPage, "Hello");
//        컨펌 메시지 배열 검증
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessage.toArray());
    }

    @Test
    public void testWindowConfirmAndAlert() throws FailingHttpStatusCodeException, IOException {
        String html = "<html><head><title>Hello</title><script>function go(){alert(confirm('Confirm Message'))}</script>\n" +
                "</head><body onload='go()'></body></html>";

        URL testUrl = new URL("http://Page1/");
        MockWebConnection mockWebConnection = new MockWebConnection();
        final List<String> confirmMessages = new ArrayList<>();

//        테스트 셋업
        webClient.setAlertHandler(new CollectingAlertHandler());
        webClient.setConfirmHandler((page, message) -> {
            confirmMessages.add(message);

            return true;
        });

        mockWebConnection.setResponse(testUrl, html);
        webClient.setWebConnection(mockWebConnection);

//        테스트 진행
        HtmlPage firstPage = webClient.getPage(testUrl);

        WebAssert.assertTitleEquals(firstPage, "Hello");
        assertArrayEquals(new String[]{"Confirm Message"}, confirmMessages.toArray());
        assertArrayEquals(new String[]{"true"}, ((CollectingAlertHandler) webClient.getAlertHandler()).getCollectedAlerts().toArray());
    }
}
