package ru.secondmemory.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.secondmemory.model.Card;
import ru.secondmemory.model.CardList;
import ru.secondmemory.model.CardType;
import ru.secondmemory.model.CardWord;
import ru.secondmemory.service.CardService;
import ru.secondmemory.service.CardServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CardServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CardServlet.class);

    final String pathJsp = "/WEB-INF/jsp/";
    private CardService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = new CardServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typeName = request.getParameter("type");
        String action = request.getParameter("action");

        //go to cardFile
        if (typeName == null) {
            log.info("get cardFile");
            request.setAttribute("allTypes", Arrays.asList(CardType.values()));
            request.getRequestDispatcher(pathJsp + "cardsType.jsp").forward(request, response);
        }

        CardType type = CardType.valueOf(typeName);
        request.setAttribute("type", type);

        //go to Cards
        if (action == null) {
            log.info("get " + typeName);
            switchTypeDispatcher(request, response, type);
        } else {
            //Crud
            String key = "";
            switch (action) {
                case "delete":
                    key = Objects.requireNonNull(request.getParameter("key"));
                    service.deleteCard(type, key);
                    break;
                case "add":
                    request.setAttribute("action", action);
                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.getRequestDispatcher(pathJsp + "addListCard.jsp").forward(request, response);
                    }
                    break;
                case "update":
                    key = Objects.requireNonNull(request.getParameter("key"));
                    request.setAttribute("action", action);
                    request.setAttribute("key", key);

                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.setAttribute("card", service.getCardListDto(type, key));
                        request.getRequestDispatcher(pathJsp + "updateListCard.jsp").forward(request, response);
                    }
                    break;
                case "get":
                    key = Objects.requireNonNull(request.getParameter("key"));
                    request.setAttribute("card", service.getCardDto(type, key));
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
        String key = Objects.requireNonNull(request.getParameter("key"));
        String typeName = Objects.requireNonNull(request.getParameter("type"));
        CardType type = CardType.valueOf(typeName);
        request.setAttribute("type", type);
        String value;
        switch (type) {
            case WORDS:
                String transcript = Objects.requireNonNull(request.getParameter("transcript"));
                String translation = Objects.requireNonNull(request.getParameter("translation"));
                service.updateOrSaveCard(type, new CardWord(key, transcript, translation));
                break;
            case QUESTIONS:
            case CITES:
                value = Objects.requireNonNull(request.getParameter("value"));
                service.updateOrSaveCard(type, new Card(key, value, type));
                break;
            case ENUMERATION:
            case VERSE:
                value = Objects.requireNonNull(request.getParameter("value"));
                List<String> extra = Arrays.stream(
                        Objects.requireNonNull(
                                request.getParameter("extra"))
                                .split(","))
                        .collect(Collectors.toList());
                service.updateOrSaveCard(type, new CardList(key, value, type, extra));
                break;
        }
        log.info("add new " + key);
        switchTypeDispatcher(request, response, type);
    }

    private void switchTypeDispatcher(HttpServletRequest request, HttpServletResponse response, CardType type) throws ServletException, IOException {
        switch (type) {
            case WORDS:
                request.setAttribute("cards", service.getAllCardsWordDtoByType(type));
                request.getRequestDispatcher(pathJsp + "wordCards.jsp").forward(request, response);
            case QUESTIONS:
            case CITES:
                request.setAttribute("cards", service.getAllCardsDtoByType(type));
                request.getRequestDispatcher(pathJsp + "cards.jsp").forward(request, response);
            case ENUMERATION:
            case VERSE:
                request.setAttribute("cards", service.getAllCardsListDtoByType(type));
                request.getRequestDispatcher(pathJsp + "listCards.jsp").forward(request, response);
        }
    }
}
