package htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebAssert;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavadocPageAllBrowserTest {

//    HtmlUnit이 지원 가능한 웹 브라우저 정보의 리스트를 반환
    private static Collection<BrowserVersion[]> getBrowserVersions() {
        return Arrays.asList(new BrowserVersion[][]{{BrowserVersion.FIREFOX_78},
                {BrowserVersion.EDGE}, {BrowserVersion.CHROME}, {BrowserVersion.BEST_SUPPORTED}});
    }

//    @ParameterizedTest 어노테이션이 달려 있으므로 파라미터를 사용한 테스트임을 알 수 있다
//    @MethodSource 어노테이션에 나타난 대로 BrowserVersion 객체가 getBrowserVersion 메서드를 통해 파라미터로
//    주입될 것이다
    @ParameterizedTest
    @MethodSource("getBrowserVersions")
    public void testClassNav(BrowserVersion browserVersion) throws IOException {
        WebClient webClient = new WebClient(browserVersion);

//        페이지가 존재하는지 검증
        HtmlPage mainPage = (HtmlPage) webClient.getPage("http://htmlunit.sourceforge.net/apidocs/index.html");
        WebAssert.notNull("Missing main page", mainPage);

        HtmlPage packagePage = (HtmlPage) mainPage.getFrameByName("packageFrame").getEnclosingPage();
        WebAssert.notNull("Missing package page", packagePage);

        HtmlListItem htmlListItem = (HtmlListItem) packagePage.getElementsByTagName("li").item(0);
        assertEquals("AboutURLConnection", htmlListItem.getTextContent());
    }
}
