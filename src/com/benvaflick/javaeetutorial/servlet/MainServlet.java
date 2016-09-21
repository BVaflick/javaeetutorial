package com.benvaflick.javaeetutorial.servlet;

import com.benvaflick.javaeetutorial.calculator.Caclulator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "MainServlet",
        urlPatterns = "/lox",
        initParams = {@WebInitParam(name = "n1", value = "v1")})
public class MainServlet extends HttpServlet {

    private List<String> history;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        if(req.getSession().isNew()) {
            history = new ArrayList<>();
        }
        else {
            history = (ArrayList) req.getSession().getAttribute("history");
        }
        PrintWriter responseStream = resp.getWriter();
        responseStream.print(
            "<html>" +
            "<head>" +
            "<title>lox ebaniy</title>" +
            "</head>" +
            "<body>"
        );
        try {
            int firstOperand = Integer.parseInt(req.getParameter("p1"));
            int secondOperand = Integer.parseInt(req.getParameter("p2"));
            String operation = req.getParameter("op");
            String calculation = Caclulator.calculate(firstOperand, secondOperand, operation);
            responseStream.print("<h2>" + calculation + "</h2>");
            history.forEach(oper -> responseStream.print("<p>" + oper + "</p>"));
            history.add(0, calculation);
            req.getSession().setAttribute("history",history);
        } catch (NumberFormatException ex) {
            resp.sendError(400);
        } finally {
            responseStream.print(
                "</body>" +
                "</html>"
            );
        }
    }

}
