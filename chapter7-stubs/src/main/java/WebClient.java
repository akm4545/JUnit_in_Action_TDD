import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient {
    public String getContent(URL url){
        StringBuffer content = new StringBuffer();

        try{
//            public한 추상 클래스인 java.net.URLConnection이므로 호출자와 페이지 구현을 깔끔하게 격리할 수 있다.
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[2048];
            int count;

            while (-1 != (count = is.read(buffer))){
                content.append(new String(buffer, 0, count));
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return content.toString();
    }
}
