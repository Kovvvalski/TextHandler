package by.kovalski.texthandler.parser;

import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.ComponentType;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.entity.SymbolLeaf;

import java.util.ArrayList;

public class ParagraphHandler extends AbstractHandler {
  public static final String SENTENCE_DELIMITER_REGEX = "[….!?]( |$)";
  private static final AbstractHandler handler = new SentenceHandler();

  @Override
  public TextComponent handleRequest(String data) {
    String[] parsed = data.split(ComponentType.SENTENCE.getDelimiter());
    TextComponent component = new TextComposite(new ArrayList<>(), ComponentType.PARAGRAPH);
    for (String s : parsed) {
      if (s.matches(SENTENCE_DELIMITER_REGEX)) {
        for (int i = 0; i < s.length(); i++) {
          component.add(new SymbolLeaf(s.charAt(i), ComponentType.PUNCTUATION_MARK));
        }
      } else
        component.add(handler.handleRequest(s));
    }
    return component;
  }
}
