package web;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MockHttpURLConnection extends HttpURLConnection {

    private InputStream stream;

    public MockHttpURLConnection() {
        super(null);
    }

    protected MockHttpURLConnection(URL url){
        super(url);
    }

    public void setExpectedInputStream(InputStream stream){
        this.stream = stream;
    }

    public InputStream getInputStream() throws IOException {
        return this.stream;
    }

    public void disconnect() {

    }

    public void connect() throws IOException{

    }

    public boolean usingProxy() {
        return false;
    }
}
