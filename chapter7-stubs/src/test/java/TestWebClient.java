import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.util.ByteArrayISO8859Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {
    private WebClient client = new WebClient();

    @BeforeAll
    public static void setUp() throws Exception {
        Server server = new Server(8081);

        Context contentOkContext = new Context(server, "/testGetContentOk");
        contentOkContext.setHandler(new TestGetContentOkHandler());

        Context contentErrorContext = new Context(server, "/testGetContentError");
        contentErrorContext.setHandler(new TestGetContentServerErrorHandler());

        Context contentNotFoundContext = new Context(server, "/testGetContentNotFound");
        contentNotFoundContext.setHandler(new TestGetContentNotFoundHandler());

        server.setStopAtShutdown(true);
        server.start();
    }

    @AfterAll
    public static void tearDown() {
        //Empty
    }

    @Test
    public void testGetContentOk() throws MalformedURLException {
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));
        assertEquals("It works", workingContent);
    }
    
//  Jetty의 AbstractHandler 클래스를 상속하고 handle 메서드를 재정의하여 사용자 정의 핸들러 선언
    private static class TestGetContentOkHandler extends AbstractHandler {
//      리퀘스트를 핸들러에 전달하기 위해 handle 메서드 호출
//      Jetty의 ByteArrayISO8859Write 클래스를 사용하여 HTTP 응답으로 It works 문자열 서렂ㅇ
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException {
            OutputStream out = response.getOutputStream();
            ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer();
            writer.write("It works");
            writer.flush();
//            출력 스트림에 기록된 문자열 길이로 설정한 다음 응답을 전송
            response.setIntHeader(HttpHeaders.CONTENT_LENGTH, writer.size());
            writer.writeTo(out);
            out.flush();
        }
    }

    private static class TestGetContentServerErrorHandler extends AbstractHandler {
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException{
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }

    private static class TestGetContentNotFoundHandler extends AbstractHandler {
        public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException{
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
