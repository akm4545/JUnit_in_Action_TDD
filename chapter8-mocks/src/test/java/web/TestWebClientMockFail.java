package web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientMockFail {

    @Test
    public void testGetContentOk() throws Exception {
//        모의 객체 생성 후 반환할 스트림 객체 설정
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));
        
//        모의 객체 생성 후 모의 연결 설정
//        MockURL mockURL = new MockURL();
//        mockURL.setupOpenConnection(mockConnection);
//        WebClient client = new WebClient();
//
//        getContent 메서드를 테스트
//        String workingContent = client.getContent(mockURL);
//
//        검증
//        assertEquals("It works", workingContent);
    }
}
