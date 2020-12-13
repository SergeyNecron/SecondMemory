package ru.secondmemory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.secondmemory.dto.CardDto;
import ru.secondmemory.model.Card;
import ru.secondmemory.model.CardType;
import ru.secondmemory.repository.CardRepository;
import ru.secondmemory.repository.InMemoryCardRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CardServlet.class);

    final String pathJsp = "/WEB-INF/jsp/";
    private CardRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryCardRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName = request.getParameter("type");
        String action = request.getParameter("action");

        //go to cardFile
        if (typeName == null && action == null) {
            log.info("get cardFile");
            request.setAttribute("allTypes", Arrays.asList(CardType.values()));
            request.getRequestDispatcher(pathJsp + "cardsType.jsp").forward(request, response);
            return;
        }
        CardType type = CardType.valueOf(typeName);

        //go to Cards
        if (action == null) {
            log.info("get " + typeName);
            switchTypeDispatcher(request, response, type);
        } else {
            //Crud
            String key = request.getParameter("key");
            switch (action) {
                case "delete":
                    repository.delete(type, key);
                    break;
                case "add":
                    request.setAttribute("action", action);
                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.setAttribute("type", type);
                        request.getRequestDispatcher(pathJsp + "addCard.jsp").forward(request, response);
                    }
                    break;
                case "update":
                    request.setAttribute("action", action);
                    request.setAttribute("key", key);
                    Card value = repository.get(type, key);
                    request.setAttribute("card", new CardDto(key, value));

                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.setAttribute("type", type);
                        request.getRequestDispatcher(pathJsp + "updateCard.jsp").forward(request, response);
                    }
                    break;
                case "get":
                    request.setAttribute("type", type);
                    request.setAttribute("card", new CardDto(key, repository.get(type, key)));
                    request.getRequestDispatcher(pathJsp + "getCard.jsp").forward(request, response);
                    break;
                case "study":
                    break;
            }
            switchTypeDispatcher(request, response, type);
            log.info(action + key);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String typeName = request.getParameter("type");
        log.info("add new " + key + ": " + value);
        CardType type = CardType.valueOf(typeName);
        final Card card = new Card(key, value, type);
        CardDto cardDto = new CardDto(key, card);
//        CardDto cardDto = new CardDto(key , new CardWord(transcript, translation));
//        CardDto cardDto = new CardDto(key , new CardList(value1, value2, value3));
        repository.save(type, cardDto);
        switchTypeDispatcher(request, response, type);
    }

    private void switchTypeDispatcher(HttpServletRequest request, HttpServletResponse response, CardType type) throws ServletException, IOException {
        switch (type) {
            case WORDS:
                setPropertiesAndDispatcher(request, response, type, "wordCards.jsp");
            case QUESTIONS:
            case CITES:
                setPropertiesAndDispatcher(request, response, type, "cards.jsp");
            case ENUMERATION:
            case VERSE:
                setPropertiesAndDispatcher(request, response, type, "listCards.jsp");
        }
    }

    private void setPropertiesAndDispatcher(HttpServletRequest request, HttpServletResponse response,
                                            CardType type, String nameJsp) throws ServletException, IOException {
        request.setAttribute("type", type);
        request.setAttribute("cards", repository.getAllByType(type));
        request.getRequestDispatcher(pathJsp + nameJsp).forward(request, response);
    }
}
