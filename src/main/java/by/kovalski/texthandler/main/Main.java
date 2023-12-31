package by.kovalski.texthandler.main;


import by.kovalski.texthandler.parser.AbstractHandler;
import by.kovalski.texthandler.parser.TextHandler;
import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.reader.TextReader;
import by.kovalski.texthandler.reader.impl.TextReaderImpl;
import by.kovalski.texthandler.service.TextHandlerService;
import by.kovalski.texthandler.service.impl.TextHandlerServiceImpl;
import by.kovalski.texthandler.util.Alphabet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Main {
  private static final Logger logger = LogManager.getLogger();
  private static final String PATH_TO_TEXT1 = "src/main/resources/data1.txt";
  private static final String PATH_TO_TEXT2 = "src/main/resources/data2.txt";

  public static void main(String[] args) throws Exception {

    TextReader reader = new TextReaderImpl();
    String text = reader.readText(PATH_TO_TEXT2);
    AbstractHandler handler = new TextHandler();
    TextComponent component = handler.handleRequest(text);
    TextHandlerService service = new TextHandlerServiceImpl();
    Map<TextComponent, Integer> map = service.findEqualsWords(component);
    service.sortParagraphs(component);
    System.out.println(service.countNumberOfVowelAndConsonantLetters(component));
    System.out.println(component);
  }
}
