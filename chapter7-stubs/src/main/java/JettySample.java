import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;

public class JettySample {
    public static void main(String[] args) throws Exception {
//        Server 객체 생성 포트는 8081
        Server server = new Server(8081);

//        HTTP 요청을 처리하고 이를 적절한 핸들러에 전달하는 Context 객체 생성
//        서버 객체와 루트 URL(/)을 파라미터로 사용
        Context root = new Context(server, "/");
//        setResourceBase에서 리소스를 제공할 문서 루트를 설정
        root.setResourceBase("./pom.xml");
//        ResourceHandler 객체를 루트 경로에 연결해 파일 시스템에서 파일을 제공하게 만든다.
        root.setHandler(new ResourceHandler());

        server.setStopAtShutdown(true);
//        서버 구동
        server.start();
    }
}
