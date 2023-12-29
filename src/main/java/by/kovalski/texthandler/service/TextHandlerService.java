package by.kovalski.texthandler.service;

import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.exception.TextHandlerException;

import java.util.List;
import java.util.Map;

public interface TextHandlerService {
  void sortParagraphs(TextComponent component) throws TextHandlerException;

  List<TextComponent> sentencesWithTheBiggestWord(TextComponent component) throws TextHandlerException;

  /**
   * @param component any component
   * @return List of 2 Integers: 1-st is number of vowel letters, 2-nd is number of consonant letters
   * @throws TextHandlerException if component type is not TEXT
   */
  List<Integer> countNumberOfVowelAndConsonantLetters(TextComponent component) throws TextHandlerException;

  Map<TextComponent, Integer> findEqualsWords(TextComponent component) throws TextHandlerException;

  void deleteSentencesWithWordsLessThan(TextComponent component, int number) throws TextHandlerException;
}
