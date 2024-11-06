package servlet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.easymock.EasyMock.eq;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSampleServletWithEasyMock {
    private SampleServlet servlet;

    private HttpServletRequest mockHttpServletRequest;

    private HttpSession mockHttpSession;

    @BeforeEach
    public void setUp() {
        servlet = new SampleServlet();
        mockHttpServletRequest = createStrictMock(HttpServletRequest.class);
        mockHttpSession = createStrictMock(HttpSession.class);
    }

    @AfterEach
    public void tearDown() {
        verify(mockHttpServletRequest);
        verify(mockHttpSession);
    }

    @Test
    public void testIsAuthenticatedAutenticated() {
        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(mockHttpSession);
        expect(mockHttpSession.getAttribute(eq("authenticated"))).andReturn("true");

        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertTrue(servlet.isAuthenticated(mockHttpServletRequest));
    }

    @Test
    public void testIsAuthenticatedNotAuthenticated() {
        expect(mockHttpSession.getAttribute(eq("authenticated"))).andReturn("false");
        replay(mockHttpSession);

        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(mockHttpSession);
        replay(mockHttpServletRequest);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }

    @Test
    public void testIsAuthenticatedNoSession() {
        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(null);

        replay(mockHttpServletRequest);
        replay(mockHttpSession);

        assertFalse(servlet.isAuthenticated(mockHttpServletRequest));
    }
}
