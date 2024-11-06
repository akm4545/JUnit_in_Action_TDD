package servlet;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSampleServletWithJMock {
    @RegisterExtension
    Mockery context = new JUnit5Mockery();

    private HttpServletRequest request;

    private HttpSession session;

    private SampleServlet servlet;

    @BeforeEach
    public void setUp() {
        request = context.mock(HttpServletRequest.class);
        session = context.mock(HttpSession.class);

        servlet = new SampleServlet();
    }

    @Test
    public void testIsAuthenticatedAuthenticated() {
        context.checking(new Expectations(){
            {
                oneOf(request).getSession(false);
                will(returnValue(session));
            }
        });

        context.checking(new Expectations(){
            {
                oneOf(session).getAttribute("authenticated");
                will(returnValue("true"));
            }
        });

        assertTrue(servlet.isAuthenticated(request));
    }

    @Test
    public void testIsAuthenticatedNotAuthenticated() {
        context.checking(new Expectations() {
            {
                oneOf(request).getSession(false);
                will(returnValue(session));
            }
        });

        context.checking(new Expectations(){
            {
                oneOf(session).getAttribute("authenticated");
                will(returnValue("false"));
            }
        });

        assertFalse(servlet.isAuthenticated(request));
    }

    @Test
    public void testIsAuthenticatedNoSession() {
        context.checking(new Expectations(){
            {
                oneOf(request).getSession(false);
                will(returnValue(null));
            }
        });

        assertFalse(servlet.isAuthenticated(request));
    }
}
