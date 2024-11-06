package servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestSampleServletWithMockito {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private SampleServlet servlet;

    @BeforeEach
    public void setUp() {
        servlet = new SampleServlet();
    }

    @Test
    public void testIsAuthenticatedAuthenticated() {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("authenticated")).thenReturn("true");

        assertTrue(servlet.isAuthenticated(request));
    }

    @Test
    public void testIsAuthenticatedNotAuthenticated() {
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("authenticated")).thenReturn("false");
        assertFalse(servlet.isAuthenticated(request));
    }

    @Test
    public void testIsAuthenticatedNoSession() {
        when(request.getSession(false)).thenReturn(null);
        assertFalse(servlet.isAuthenticated(request));
    }
}
