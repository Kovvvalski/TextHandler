package by.kovalski.texthandler.parser;

import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.ComponentType;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.entity.SymbolLeaf;

import java.util.ArrayList;

public class WordHandler extends AbstractHandler {
  @Override
  public TextComponent handleRequest(String data) {
    String[] parsed = data.split(ComponentType.LETTER.getDelimiter());
    TextComponent component = new TextComposite(new ArrayList<>(), ComponentType.WORD);
    for (String letter : parsed)
      component.add(new SymbolLeaf(letter.charAt(0), ComponentType.LETTER));
    return component;
  }
}
