package by.kovalski.texthandler.reader;

import by.kovalski.texthandler.exception.TextHandlerException;

@FunctionalInterface
public interface TextReader {
  String readText(String path) throws TextHandlerException;
}
