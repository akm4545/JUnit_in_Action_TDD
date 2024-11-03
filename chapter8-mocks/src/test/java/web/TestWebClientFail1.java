package web;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientFail1 {
    @Test
    public void testGetContentOk() {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        MockInputStream mockStream = new MockInputStream();
        mockStream.setBuffer("It works");

        mockConnectionFactory.setData(mockStream);

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(mockConnectionFactory);

        assertEquals("It works", workingContent);
        mockStream.verify();
    }
}
