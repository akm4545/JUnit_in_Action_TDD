package htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ManageWebClient {

//    protected 접근 제어자를 붙인 Webclient 타입 필드
//    이렇게 하면 자식 클래스에서 해당 필드를 상속할 수 있다
//    WebClient 객체는 웹 브라우저를 시뮬레이션하고 테스트를 실행하기 위해 사용한다
    protected WebClient webClient;

    @BeforeEach
    public void setUp() {
        webClient = new WebClient();
    }

    @AfterEach
    public void tearDown() {
        webClient.close();
    }
}
