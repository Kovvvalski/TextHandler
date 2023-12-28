package by.kovalski.texthandler.parser;

import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.ComponentType;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.entity.SymbolLeaf;

import java.util.ArrayList;

public class SentenceHandler extends AbstractHandler {
  private static final String WORD_DELIMITER_REGEX = "[, \\-\\(\\)\\\"\\\"=\n]";
  private static AbstractHandler handler = new WordHandler();

  @Override
  public TextComponent handleRequest(String data) {
    String[] parsed = data.split(ComponentType.WORD.getDelimiter());
    TextComponent component = new TextComposite(new ArrayList<>(), ComponentType.SENTENCE);
    for (String s : parsed) {
      if (s.matches(WORD_DELIMITER_REGEX)) {
        component.add(new SymbolLeaf(s.charAt(0), ComponentType.PUNCTUATION_MARK));
      } else component.add(handler.handleRequest(s));
    }
    return component;
  }
}
