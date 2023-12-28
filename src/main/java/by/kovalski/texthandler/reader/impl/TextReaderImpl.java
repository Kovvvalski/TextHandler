package by.kovalski.texthandler.reader.impl;

import by.kovalski.texthandler.exception.TextHandlerException;
import by.kovalski.texthandler.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public String readText(String path) throws TextHandlerException {
    try {
      return String.join("\n", Files.readAllLines(Paths.get(path)));
    } catch (IOException e) {
      logger.error("Can nor read the file " + path, e);
      throw new TextHandlerException("Can nor read the file " + path, e);
    }
  }
}
