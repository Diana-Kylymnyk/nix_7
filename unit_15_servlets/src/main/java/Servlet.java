import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@WebServlet(name = "sample-servlet", urlPatterns = "/sample")
public class Servlet extends HttpServlet {

    private static final long serialVersionUID = -8948379822734246956L;

    private final Queue<String> linkedQueue = new ConcurrentLinkedQueue<>();

    @Override
    public void init() {
        log.info(getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        String val = req.getRemoteHost() + " :: " + req.getHeader("User-Agent");
        linkedQueue.add(val);

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">List of users</h1>");

        linkedQueue.forEach(u -> {
            if (!u.equals(val)) {
                responseBody.println("<br>" + u + "<br>");
            } else {
                responseBody.println("<br><b>" + u + "</b></br>");
            }
        });
    }

    @Override
    public void destroy() {
        log.info(getServletName() + " destroyed");
    }
}