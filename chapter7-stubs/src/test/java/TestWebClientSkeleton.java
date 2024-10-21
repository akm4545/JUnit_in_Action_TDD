import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientSkeleton {

//    @BeforeAll과 @AfterAll 메서드를 구현하는 방법은 두 가지다.
//    첫 번째 방법은 루트 경로에 It works 문자열을 포함한 정적 페이지를 만들어 놓는 것이다
//    두 번째 방법은 파일에서 가져오지 않고 It works 문자열을 반환하는 사용자 정의 핸들러를 사용하도록 Jetty를 구성하는 것이다
//    두 번째 방법이 선호되는데 이렇게 했을 때 HTTP 서버가 WebClient 객체로 만든 애플리케이션에 에러 코드를 반환하는 경우도 테스트할 수 있
//    기 때문이다
    @BeforeAll
    public static void setUp() {
//        Jetty 서버를 시작하고
//        http://localhost:8081/testGetContentOk 경로로
//        접근했을 때 "It works"를 반환하도록 설정한다.
    }

    @AfterAll
    public static void tearDown() {
//        Jetty 서버를 중지한다.
    }

    @Test
    @Disabled(value = "단순한 테스트 스켈레톤이므로 현재 이 테스트를 실행하면 실패한다")
    public void testGetContentOk() throws MalformedURLException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URL("http://localhost:8081/testGetContentOk"));

        assertEquals("It works", workingContent);
    }
}
