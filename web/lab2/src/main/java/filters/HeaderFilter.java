package filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "HeaderFilter")
public class HeaderFilter implements Filter {
    private Map<String, Long> headersMap;

    public void init(FilterConfig config) throws ServletException {
        headersMap = new HashMap<>();
    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Enumeration<String> headers = ((HttpServletRequest) request).getHeaderNames();

        while (headers.hasMoreElements())
            countHeader(headers.nextElement());

        printMap();

        chain.doFilter(request, response);
    }

    private synchronized void countHeader(String headerName) {
        if (!headersMap.containsKey(headerName))
            headersMap.put(headerName, 1L);
        else
            headersMap.replace(headerName, headersMap.get(headerName)+1);
    }

    private void printMap() {
        System.out.println("NEW REQUEST!");
        headersMap.forEach((key, value) -> {
            System.out.println(key+": "+value);
        });
    }
}
