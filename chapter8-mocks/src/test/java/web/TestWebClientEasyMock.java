package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
//EasyMock의 클래스 확장을 사용하므로 org.easymock.EasyMock이 아니라
//org.easymock.classextension.EasyMock을 가져온다
//클래스 확장을 위한 정적 메서드를 사용하여 클래스와 인터페이스의 모의 객체를 만들 수 있다
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
//        stream.read() 문장을 실행할 때의 기대를 정의
//        stream은 마지막에 -1을 읽어 들여야 한다
//        InputStream 같은 저수준 스트림은 바이트 단위로 데이터를 읽어 오므로 한 번에
//        한 바이트씩 읽는 방법을 정의한다
        expect(stream.read()).andReturn(Integer.valueOf((byte)'W'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'o'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'r'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'k'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'s'));
        expect(stream.read()).andReturn(Integer.valueOf((byte)'!'));

        expect(stream.read()).andReturn(-1);
        
//        stream.close 메서드가 호출될 것을 기대한다
        stream.close();

//        replay 메서드를 호출하기 전에는 모의 객체가 수행해야 하는 작업을 기록만 할 뿐
//        모의 객체를 호출하더도 동작을 수행하지 않는다
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
//        stream을 닫을 수 없을 때의 조건을 모사
        stream.close();
//        호출이 발생했을 경우 IOException이 발생해야 한다고 선언
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
