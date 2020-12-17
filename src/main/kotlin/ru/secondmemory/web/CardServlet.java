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
                    service.deleteCard(type, key);
                    break;
                case "add":
                    request.setAttribute("action", action);
                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.setAttribute("type", type);
                        request.getRequestDispatcher(pathJsp + "addListCard.jsp").forward(request, response);
                    }
                    break;
                case "update":
                    request.setAttribute("action", action);
                    request.setAttribute("key", key);

                    if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                        request.setAttribute("type", type);
                        request.setAttribute("card", service.getCardListDto(type, key));
                        request.getRequestDispatcher(pathJsp + "updateListCard.jsp").forward(request, response);
                    }
                    break;
                case "get":
                    request.setAttribute("type", type);
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
        String key = request.getParameter("key");
        String typeName = request.getParameter("type");
        CardType type = CardType.valueOf(typeName);
        String value;
        switch (type) {
            case WORDS:
                String transcript = request.getParameter("transcript");
                String translation = request.getParameter("translation");
                service.updateOrSaveCard(type, new CardWord(key, transcript, translation));
                break;
            case QUESTIONS:
            case CITES:
                value = request.getParameter("value");
                service.updateOrSaveCard(type, new Card(key, value, type));
                break;
            case ENUMERATION:
            case VERSE:
                value = request.getParameter("value");
                List<String> extra = Arrays.stream(request.getParameter("extra").split(","))
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
                setPropertiesAndDispatcher(request, response, type, "wordCards.jsp");
            case QUESTIONS:
            case CITES:
                request.setAttribute("cards", service.getAllCardsDtoByType(type));
                setPropertiesAndDispatcher(request, response, type, "cards.jsp");
            case ENUMERATION:
            case VERSE:
                request.setAttribute("cards", service.getAllCardsListDtoByType(type));
                setPropertiesAndDispatcher(request, response, type, "listCards.jsp");
        }
    }

    private void setPropertiesAndDispatcher(HttpServletRequest request, HttpServletResponse response,
                                            CardType type, String nameJsp) throws ServletException, IOException {
        request.setAttribute("type", type);
        request.getRequestDispatcher(pathJsp + nameJsp).forward(request, response);
    }
}
