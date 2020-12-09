package ru.secondmemory.web;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.secondmemory.dto.CardDto;
import ru.secondmemory.model.CardType;
import ru.secondmemory.model.Cards;

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

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        cardFile = fillTestDataCardFile();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        final String pathJsp = "/WEB-INF/jsp/";
        if (action == null) {
            log.info("get cardFile");
            request.setAttribute("cardsType", Arrays.asList(CardType.values()));
            request.getRequestDispatcher(pathJsp + "cardsType.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "WORDS":
                setNameSetCards(request, CardType.WORDS);
                request.getRequestDispatcher(pathJsp + "wordCards.jsp").forward(request, response);
                break;
            case "QUESTIONS":
                setNameSetCards(request, CardType.QUESTIONS);
                request.getRequestDispatcher(pathJsp + "cards.jsp").forward(request, response);
                break;
            case "ENUMERATION":
                setNameSetCards(request, CardType.ENUMERATION);
                request.getRequestDispatcher(pathJsp + "listCards.jsp").forward(request, response);
                break;
            case "CITES":
                setNameSetCards(request, CardType.CITES);
                request.getRequestDispatcher(pathJsp + "cards.jsp").forward(request, response);
                break;
            case "VERSE":
                setNameSetCards(request, CardType.VERSE);
                request.getRequestDispatcher(pathJsp + "listCards.jsp").forward(request, response);
                break;
            default:
                log.info("get Home");
                request.getRequestDispatcher("index.html")
                        .forward(request, response);
        }
        log.info("get " + action);

    }

    private void setNameSetCards(HttpServletRequest request, CardType cardType) {
        request.setAttribute("name", cardType.getTitle());
        request.setAttribute("cards", getCardDto(cardType));
    }

    @NotNull
    private List<CardDto> getCardDto(CardType cardType) {

        Cards cardMap = cardFile.get(cardType);
        return cardMap
                .getCards()
                .keySet()
                .stream()
                .map(it -> new CardDto(it, cardMap.getCards().get(it)))
                .collect(Collectors.toList());
    }
}
