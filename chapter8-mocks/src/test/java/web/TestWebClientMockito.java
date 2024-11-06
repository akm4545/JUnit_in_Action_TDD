package web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestWebClientMockito {
    @Mock
    private ConnectionFactory factory;

    @Mock
    private InputStream mockStream;

    @Test
    public void testGetContentOk() throws Exception{
        when(factory.getData()).thenReturn(mockStream);
        when(mockStream.read()).thenReturn((int) 'W')
                .thenReturn((int) 'o')
                .thenReturn((int) 'r')
                .thenReturn((int) 'k')
                .thenReturn((int) 's')
                .thenReturn((int) '!')
                .thenReturn(-1);

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(factory);

        assertEquals("Works!", workingContent);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception{
        when(factory.getData()).thenReturn(mockStream);
        when(mockStream.read()).thenReturn(-1);
//        스트림을 닫을 때 IOException을 던지도록 Mockito에 작성
        doThrow(new IOException("cannot close")).when(mockStream).close();

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(factory);

        assertNull(workingContent);
    }
}
