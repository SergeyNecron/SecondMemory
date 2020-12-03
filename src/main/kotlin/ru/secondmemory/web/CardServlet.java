package ru.secondmemory.web;

import ru.secondmemory.util.MemoryUtilKt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cards", MemoryUtilKt.fillTestDataCardFile());
        request.getRequestDispatcher("/cards.jsp").forward(request, response);
    }
}
