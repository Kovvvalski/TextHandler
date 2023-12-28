package by.kovalski.texthandler.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TextComposite implements TextComponent {
  private static final Logger logger = LogManager.getLogger();
  private List<TextComponent> children;
  private ComponentType type;

  public TextComposite(List<TextComponent> children, ComponentType type) {
    setType(type);
    this.children = children;
  }

  public List<TextComponent> getChildren() {
    return children;
  }

  public void setChildren(List<TextComponent> children) {
    this.children = children;
  }

  @Override
  public ComponentType getType() {
    return type;
  }

  @Override
  public void setType(ComponentType type) {
    if (type == ComponentType.PUNCTUATION_MARK || type == ComponentType.LETTER) {
      logger.error("Invalid type");
      throw new UnsupportedOperationException("Unavailable type");
    }
    this.type = type;
  }

  @Override
  public void add(TextComponent component) {
    children.add(component);
  }

  @Override
  public void remove(TextComponent component) {
    children.remove(component);
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
    return 31 * children.hashCode() + type.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    for (TextComponent component : children) {
      out.append(component.toString());
    }
    return out.toString();
  }
}

