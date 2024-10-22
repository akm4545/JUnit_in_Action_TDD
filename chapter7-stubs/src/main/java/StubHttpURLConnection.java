import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class StubHttpURLConnection extends HttpURLConnection {
    private boolean isInput = true;

    protected StubHttpURLConnection(URL url){
        super(url);
    }

//    테스트 대상 메서드인 getInputStream 메서드를 구현
//    테스트할 코드가 HttpURLConnection에서 getInputStream 이외에 다른 API를 사용했다면 그 또한 스텁으로 만들어야 한다
//    이 부분에서 스텁이 복잡해진다 실제 HttpURLConnection을 대체하기 위해 계속 로직이 늘어나기 때문이다
    @Override
    public InputStream getInputStream() throws IOException{
//        입력에 URL 연결을 사용하는지 알려준다 (토글로 바꿀 수 있게 만든걸 가정한건가?)
        if(!isInput){
            throw new ProtocolException("Cannot read from URLConnection" + " if doInput=false (call setDoInput(true))");
        }
        ByteArrayInputStream readStream = new ByteArrayInputStream(new String("It works").getBytes());

        return readStream;
    }

    @Override
    public void connect() throws IOException{

    }

    @Override
    public void disconnect() {

    }

    @Override
    public boolean usingProxy() {
        return false;
    }
}
