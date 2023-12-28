package by.kovalski.texthandler.entity;


public enum ComponentType {
  TEXT(null) {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      throw new UnsupportedOperationException("This operation is unsupported yet");
    }
  },
  PARAGRAPH("((?=\\n? {4})|(?<=\\n? {4}))") {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      throw new UnsupportedOperationException("This operation is unsupported yet");
    }
  },
  SENTENCE("(?<=[….!?]( |$))|(?=[….!?]( |$))") {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      throw new UnsupportedOperationException("This operation is unsupported yet");
    }
  },
  WORD("(?=[, \\-\\(\\)\\\"\\\"=\\n])|(?<=[, \\-\\(\\)\\\"\\\"=\\n])") {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      if (component1.getType() != WORD || component2.getType() != WORD) {
        throw new UnsupportedOperationException("Not correct enum method for input arguments: " + component1.getType() + " " + component2.getType());
      }

      TextComposite word1 = (TextComposite) component1;
      TextComposite word2 = (TextComposite) component2;
      if (word1.getChildren().size() != word2.getChildren().size()) {
        return false;
      }
      for (int i = 0; i < word1.getChildren().size(); i++) {
        if (!word1.getChildren().get(i).equals(word2.getChildren().get(i))) {
          return false;
        }
      }
      return true;
    }
  },
  PUNCTUATION_MARK(null) {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      throw new UnsupportedOperationException("This operation is unsupported yet");
    }
  },
  LETTER("") {
    @Override
    public boolean equals(TextComponent component1, TextComponent component2) {
      if (component1.getType() != LETTER || component2.getType() != LETTER) {
        throw new UnsupportedOperationException("Not correct enum method for input arguments: " + component1.getType() + " " + component2.getType());
      }
      SymbolLeaf letter1 = (SymbolLeaf) component1;
      SymbolLeaf letter2 = (SymbolLeaf) component2;
      return letter1.getSymbol() == letter2.getSymbol();
    }
  };
  private final String delimiter;

  ComponentType(String delimiter) {
    this.delimiter = delimiter;
  }

  public String getDelimiter() {
    return this.delimiter;
  }

  public abstract boolean equals(TextComponent component1, TextComponent component2);

}
