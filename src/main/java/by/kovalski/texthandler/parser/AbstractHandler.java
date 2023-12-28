package by.kovalski.texthandler.parser;

import by.kovalski.texthandler.entity.TextComponent;

public abstract class AbstractHandler {
  public abstract TextComponent handleRequest(String data);
}
