package ru.secondmemory.web;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.secondmemory.dto.CardDto;
import ru.secondmemory.model.Card;
import ru.secondmemory.model.CardType;
import ru.secondmemory.model.Cards;
import ru.secondmemory.repository.CardRepository;
import ru.secondmemory.repository.InMemoryCardRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import static ru.secondmemory.util.MemoryUtilKt.fillTestDataCardFile;

public class CardServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CardServlet.class);
    private EnumMap<CardType, Cards> cardFile;
    final String pathJsp = "/WEB-INF/jsp/";
    private CardRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cardFile = fillTestDataCardFile();
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
        }

        //Crud
        String key = request.getParameter("key");
        switch (action) {
            case "delete":
//                repository.delete(type, key);
                cardFile.get(type).getCards().remove(key);
                break;
            case "add":
                request.setAttribute("action", action);
                request.setAttribute("newCard", new CardDto("", new Card()));

                if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                    request.setAttribute("type", type);
                    request.getRequestDispatcher(pathJsp + "addCard.jsp").forward(request, response);
                }
                break;
            case "update":
                request.setAttribute("action", action);
                request.setAttribute("key", key);

                Card value = cardFile.get(type).getCards().get(key);
                request.setAttribute("newCard", new CardDto(key, value));

                if (type == CardType.ENUMERATION || type == CardType.VERSE) {
                    request.setAttribute("type", type);
                    request.getRequestDispatcher(pathJsp + "addCard.jsp").forward(request, response);
                }
//                repository.get(getId(request));
                break;
            case "study":
                break;
        }
        switchTypeDispatcher(request, response, type);
        log.info(action + key);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String typeName = request.getParameter("type");
        log.info("add new " + key + ": " + value);
        CardType type = CardType.valueOf(typeName);
        //        repository.save(card);
        cardFile.get(type).addCard(key, value);
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
        request.setAttribute("cards", getListCardDto(type));
        request.getRequestDispatcher(pathJsp + nameJsp).forward(request, response);
    }

    @NotNull
    private List<CardDto> getListCardDto(CardType type) {

        Cards cardMap = cardFile.get(type);
        return cardMap
                .getCards()
                .keySet()
                .stream()
                .map(it -> new CardDto(it, cardMap.getCards().get(it)))
                .collect(Collectors.toList());
    }
}
