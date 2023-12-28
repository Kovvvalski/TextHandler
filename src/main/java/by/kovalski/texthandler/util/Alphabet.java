package by.kovalski.texthandler.util;

import java.util.ArrayList;
import java.util.List;

public enum Alphabet {
  VOWEL_ENG(List.of('a', 'e', 'i', 'o', 'u', 'y')),
  CONSONANT_ENG(List.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z')),
  VOWEL_RUS(List.of('а', 'о', 'у', 'э', 'ы', 'я', 'ё', 'е', 'ю', 'и')),
  CONSONANT_RUS(List.of('б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ъ'));

  Alphabet(List<Character> list) {
    this.letters = list;
  }

  private final List<Character> letters;

  public List<Character> getLetters() {
    return new ArrayList<>(letters);
  }

}
