package htmlunit;

import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HtmlUnitPageTest extends ManageWebClient{

    @Test
    public void homePage() throws IOException {
//        HtmlUnit 홈페이지에 접속하여 홈페이지의 타이틀 검증
        HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
        assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

//        홈페이지를 XML로 가져와서 특정 태그가 포함되어 있는지 검증
        String pageAsXml = page.asXml();
        assertTrue(pageAsXml.contains("<div class=\"container-fluid\">"));

//        홈페이지를 텍스트로 가져와서 특정 문자열이 포함되어 있는지 검증
        String pageAsText = page.asText();
        assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
    }

    @Test
    public void testClassNav() throws IOException {
        HtmlPage mainPage = webClient.getPage("http://htmlunit.sourceforge.net/apidocs/index.html");
//        packageFrame안에서 페이지를 가져온다
        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosingPage();
//        가져온 페이지 안에서 li 태그가 붙은 첫 번째 요소 찾기
        HtmlListItem htmlListItem = (HtmlListItem)packagePage.getElementsByTagName("li").item(0);

//        가져온 요소의 텍스트 검증
        assertEquals("AboutURLConnection", htmlListItem.getTextContent());
    }
}
