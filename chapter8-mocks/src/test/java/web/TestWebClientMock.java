package web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientMock {
    @Test
    public void testGetContentOk() throws Exception{
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockConnection);

        String result = client.getContent(new URL("http://localhost"));

        assertEquals("It works", result);
    }

//    리팩토링 하기 전에 문제점은 URL의 모의 객체를 만들 수 없어 getContent 메서드에서 모의 connection 객체를 사용할 수 없는 문제가 있었다.
//    기존 getContent 메서드의 HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 부분에서 모의 객체로
//    바꿔치기 할 수 있는 구간이 없었다

//    HttpURLConnection을 얻어오는 코드를 메서드로 분리하고 WebClient를 상속받아 클래스를 만든다
//    결과적으로 사용해야할 모의 객체는 HttpURLConnection이므로 해당 부분을 외부에서 전달받을 수 있게 세터 메서드를 추가하고
//    URL은 모의할 수 없다 하더라도 connection을 얻어오는 코드를 재정의 하여 외부에서 전달받은 mock 객체를 리턴하게 한다
    private class TestableWebClient extends WebClient1 {
        private HttpURLConnection connection;

        public void setHttpURLConnection(HttpURLConnection connection){
            this.connection = connection;
        }

        public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
            return this.connection;
        }
    }
}
