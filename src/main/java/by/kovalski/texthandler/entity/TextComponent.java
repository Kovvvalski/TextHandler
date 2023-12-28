package by.kovalski.texthandler.entity;

public interface TextComponent {
  void add(TextComponent component);

  void remove(TextComponent component);

  void setType(ComponentType type);

  ComponentType getType();

}
