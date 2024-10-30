package web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebClient1 {
//    getContent 메서드는 HttpURLConnection 객체를 가져온 다음 이 객체에서 콘텐츠를 읽어 들이는 두 가지 작업을
//    수행한다
    public String getContent(URL url){
        StringBuffer content = new StringBuffer();

        try{
//            리팩토링 부분
            HttpURLConnection connection = createHttpURLConnection(url);
            InputStream is = connection.getInputStream();

            int count;
            while (-1 != (count = is.read())){
                content.append(new String(Character.toChars(count)));
            }
        }catch (IOException e){
            return null;
        }

        return content.toString();
    }

    protected HttpURLConnection createHttpURLConnection(URL url) throws IOException{
        return (HttpURLConnection) url.openConnection();
    }
}
