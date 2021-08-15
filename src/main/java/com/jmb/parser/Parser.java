package com.jmb.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Класс разбивает содержимое на слова
 *
 * @author Иван Волощенко
 */
public class Parser {

    /** Ссылка на страницу/путь к файлу */
    public String url;

    /** Эмуляция запроса от браузера */
    private static final String userAgent = "Chrome/4.0.249.0";

    /**
     * Конструктор класса com.jmb.parser.Parser
     *
     * @param url Ссылка на страницу/путь к файлу
     */
    public Parser(String url) {
        this.url = url;
    }

    /**
     * Парсинг html по url страницы
     *
     * @throws IOException Исключение вызываемое библиотекой Jsoup у метода get
     */
    public String parseFromUrl() throws IOException {
        Document document = Jsoup.connect(url)
                .userAgent(userAgent)
                .get();

        return this.splitUniqueWords(document);
    }

    /**
     * Парсинг html из файла
     *
     * @throws FileNotFoundException Исключение, вызываемое при некорректном пути к файлу
     * @throws IOException Исключение, вызываемое библиотекой Jsoup у метода get
     */
    public String parseFromFile() throws IOException {
        File input = new File(url);
        Document document = Jsoup.parse(input, "UTF-8");

        return this.splitUniqueWords(document);
    }

    /**
     * Получение количества уникальных слов
     *
     * @param document Html документ
     */
    public String splitUniqueWords(Document document) {
        String textBody = document.body().text();

        List<String> list = Arrays.asList(textBody.split("[ ,.!\"?;:\\]\\[)(\n\r\t]+"));
        Set<String> uniqueWords = new HashSet<>(list);
        StringBuilder result = new StringBuilder();

        for (String word : uniqueWords) {
            System.out.println(word + " - " + Collections.frequency(list, word));
            result.append(word).append(" - ").append(Collections.frequency(list, word)).append(" \n");
        }

        return result.toString();
    }
}
