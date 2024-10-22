import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient1 {
//    사용자 정의 URL 프로토콜 핸들러를 구현하기 위해 URL 클래스의 정적 메서드인 setURLStreamHandlerFactory 호출
//    사용자가 정의한 URLStreamHandlerFactory를 만들어 파라미터로 전달
//    URL 스트림 핸들러를 스텁으로 구현

//    URL 클래스의 openConnection 메서드가 호출될 때마다 URLStreamHandlerFactory 클래스가 호출되어
//    URLStreamHandler 반환

    @BeforeAll
    public static void setUp() {
        URL.setURLStreamHandlerFactory(new StubStreamHandlerFactory());
    }

//    StubHttpURLStreamHandler를 사용하기 위해 구현
//    URL.setURL... 의 매개변수가 URLStreamHandlerFactory 타입이기 때문
    private static class StubStreamHandlerFactory implements URLStreamHandlerFactory {
        @Override
        public URLStreamHandler createURLStreamHandler(String protocol){
            return new StubHttpURLStreamHandler();
        }
    }

//    openConnection 메서드 재정의
    private static class StubHttpURLStreamHandler extends URLStreamHandler {
        @Override
        protected URLConnection openConnection(URL url){
            return new StubHttpURLConnection(url);
        }
    }

    @Test
    public void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost/"));

        assertEquals("It works", workingContent);
    }
}
