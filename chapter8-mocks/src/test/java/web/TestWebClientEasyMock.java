package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestWebClientEasyMock {

    private ConnectionFactory factory;

    private InputStream stream;

    @BeforeEach
    public void setUp() {
        factory = createMock("factory", ConnectionFactory.class);
        stream = createMock("stream", InputStream.class);
    }

    @Test
    public void testGetContentOk() throws Exception {
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(Integer.valueOf((byte)'W'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'o'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'r'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'k'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'s'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'!'));

        expect(stream.read()).andReturn(-1);
        stream.close();

        replay(factory);
        replay(stream);

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(factory);

        assertEquals("Works!", workingContent);
    }

    @Test
    public void testGetContentInputStreamNull() throws Exception{
        expect(factory.getData()).andReturn(null);

        replay(factory);
        replay(stream);

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(factory);

        assertNull(workingContent);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception{
        expect(factory.getData()).andReturn(stream);
        expect(stream.read()).andReturn(-1);
        stream.close();
        expectLastCall().andThrow(new IOException("cannot close"));

        replay(factory);
        replay(stream);

        WebClient2 client = new WebClient2();
        String workingContent = client.getContent(factory);

        assertNull(workingContent);
    }

    @AfterEach
    public void tearDown() {
        verify(factory);
        verify(stream);
    }
}
