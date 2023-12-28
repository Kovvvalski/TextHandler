package by.kovalski.texthandler.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SymbolLeaf implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private ComponentType type;
  private char symbol;

  public SymbolLeaf(char symbol, ComponentType type) {
    setType(type);
    this.symbol = symbol;
  }

  @Override
  public ComponentType getType() {
    return type;
  }

  @Override
  public void setType(ComponentType type) {
    if (!(type == ComponentType.PUNCTUATION_MARK || type == ComponentType.LETTER)) {
      logger.error("Invalid type");
      throw new UnsupportedOperationException("Invalid type");
    }
    this.type = type;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public char getSymbol() {
    return this.symbol;
  }

  @Override
  public void add(TextComponent component) {
    throw new UnsupportedOperationException("Symbol does not have children");
  }

  @Override
  public void remove(TextComponent component) {
    throw new UnsupportedOperationException("Symbol does not have children");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || obj.getClass() != getClass()) return false;
    TextComponent component = (TextComponent) obj;
    return type.equals(this, component);
  }

  @Override
  public int hashCode() {
    return 31 * type.hashCode() + symbol;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }

}
