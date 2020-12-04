package ru.secondmemory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.secondmemory.model.CardType;
import ru.secondmemory.util.MemoryUtilKt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CardServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CardServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
//        request.setAttribute("cards", CardType.values());
        request.getRequestDispatcher("/cards.jsp").forward(request, response);
    }
}
