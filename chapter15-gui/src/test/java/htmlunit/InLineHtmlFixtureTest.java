package htmlunit;

import com.gargoylesoftware.htmlunit.MockWebConnection;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

public class InLineHtmlFixtureTest extends ManageWebClient{

    @Test
    public void testInLineHtmlFixture() throws IOException{
//        HTML 페이지 제목과 예상되는 HTML 페이지 응답을 미리 생성
        final String expectedTitle = "Hello 1!";
        String html = "<html><head><title>" + expectedTitle + "</title></head></html>";

//        MockWebConnection 객페를 만들고 모의 HTTP 연결을 위한 디폴트 응답 HTML 페이지 서렂ㅇ
        MockWebConnection connection = new MockWebConnection();
        connection.setDefaultResponse(html);

        webClient.setWebConnection(connection);
//        웹 클라이언트에서 임시 페이지를 호출 
//        사전 설정한 HTML 응답을 반환하므로 어떤 URL을 사용해도 상관없다
        HtmlPage page = webClient.getPage("http://page");
//        <title> 태그 값 검증
        WebAssert.assertTitleEquals(page, expectedTitle);
    }

//    각 URL에 맞는 응답을 설정 후 테스트
    @Test
    public void testInLineHtmlFixtures() throws IOException {
        final URL page1Url = new URL("http://Page1/");
        final URL page2Url = new URL("http://Page2/");
        final URL page3Url = new URL("http://Page3/");

        MockWebConnection connection = new MockWebConnection();
        connection.setResponse(page1Url, "<html><head><title>Hello 1!</title></head></html>");
        connection.setResponse(page2Url, "<html><head><title>Hello 2!</title></head></html>");
        connection.setResponse(page3Url, "<html><head><title>Hello 3!</title></head></html>");

        webClient.setWebConnection(connection);

        HtmlPage page1 = webClient.getPage(page1Url);
        WebAssert.assertTitleEquals(page1, "Hello 1!");

        HtmlPage page2 = webClient.getPage(page2Url);
        WebAssert.assertTitleEquals(page2, "Hello 2!");

        HtmlPage page3 = webClient.getPage(page3Url);
        WebAssert.assertTitleEquals(page3, "Hello 3!");
    }
}
