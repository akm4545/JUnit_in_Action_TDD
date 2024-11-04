package web;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestWebClientJMock {
//    확장을 등록하는 것으로 테스트 케이스를 시작
//    private으로 선언하지 않은 인스턴스 필드 context에는 @RegisterExtension 어노테이션이 달려 있다
    @RegisterExtension
    Mockery context = new JUnit5Mockery(){
//        JMock에서 인터페이스 말고 클래스에 대한 모의 객체를 생성해야 한다면 context에서
//        imposteriser 속성을 정의해야 한다
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    @Test
    public void testGetContentOk() throws Exception{
//        모의 객체를 프로그래밍 방식으로 초기화
        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);

//        모의 객체가 수행하길 원하는 동작을 기대
        context.checking(new Expectations(){
            {
                oneOf(factory).getData();
                will(returnValue(mockStream));

                atLeast(1).of(mockStream).read();
                will(onConsecutiveCalls(
                        returnValue(Integer.valueOf((byte) 'W')),
                        returnValue(Integer.valueOf((byte) 'o')),
                        returnValue(Integer.valueOf((byte) 'r')),
                        returnValue(Integer.valueOf((byte) 'k')),
                        returnValue(Integer.valueOf((byte) 's')),
                        returnValue(Integer.valueOf((byte) '!')),
                        returnValue(-1)));

                oneOf(mockStream).close();
            }
        });

        WebClient2 client = new WebClient2();

//        테스트 대상 메서드를 호출
        String workingContent = client.getContent(factory);

//        예상 결과를 단언
        assertEquals("Works!", workingContent);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception{
        ConnectionFactory factory = context.mock(ConnectionFactory.class);
        InputStream mockStream = context.mock(InputStream.class);

        context.checking(new Expectations(){
            {
                oneOf(factory).getData();
                will(returnValue(mockStream));
                oneOf(mockStream).read();
                will(returnValue(-1));
//                close메서드를 호출했을 때 예외를 던진다
                oneOf(mockStream).close();
                will(throwException(new IOException("cannot close")));
            }
        });

        WebClient2 client = new WebClient2();

        String workingContent = client.getContent(factory);

        assertNull(workingContent);
    }
}
