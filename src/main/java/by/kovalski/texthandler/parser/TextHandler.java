package by.kovalski.texthandler.parser;

import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.ComponentType;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.entity.SymbolLeaf;

import java.util.ArrayList;

public class TextHandler extends AbstractHandler {
  private static final String PARAGRAPH_DELIMITER_REGEX = "\\n| {4}";
  private static final AbstractHandler handler = new ParagraphHandler();

  @Override
  public TextComponent handleRequest(String data) {
    TextComponent component = new TextComposite(new ArrayList<>(), ComponentType.TEXT);
    String[] parsed = data.split(ComponentType.PARAGRAPH.getDelimiter());
    for (String s : parsed) {
      if (s.matches(PARAGRAPH_DELIMITER_REGEX)) {
        for (int i = 0; i < s.length(); i++) {
          component.add(new SymbolLeaf(s.charAt(i), ComponentType.PUNCTUATION_MARK));
        }
      } else {
        component.add(handler.handleRequest(s));
      }
    }
    return component;
  }
}
