package com.jmb.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

/**
 * Класс разбивает содержимое страницы сайта на слова
 *
 * @author Иван Волощенко
 */
public class Parser {

    /** Адрес страницы сайта */
    public String url;

    /** Эмуляция запроса от браузера */
    private static final String userAgent = "Chrome/4.0.249.0";

    /**
     * Конструктор класса com.jmb.parser.Parser
     *
     * @param url Адрес страницы сайта
     */
    public Parser(String url) {
        this.url = url;
    }

    /**
     * Метод по разбивке содержимого на слова
     *
     * @throws IOException исключение вызываемое библиотекой Jsoup
     */
    public StringBuilder run() throws IOException {
        Document document = Jsoup.connect(url)
                .userAgent(userAgent)
                .get();
        String textBody = document.body().text();

        List<String> list = Arrays.asList(textBody.split("[ ,.!\"?;:\\]\\[()\n\r\t]+"));
        Set<String> uniqueWords = new HashSet<>(list);
        StringBuilder result = new StringBuilder(" ");

        for (String word : uniqueWords) {
            System.out.println(word + ": " + Collections.frequency(list, word));
            result.append(word).append(": ").append(Collections.frequency(list, word)).append(" \n");
        }

        return result;
    }
}
