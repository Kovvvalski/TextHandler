package by.kovalski.texthandler.service.impl;

import by.kovalski.texthandler.entity.SymbolLeaf;
import by.kovalski.texthandler.entity.TextComponent;
import by.kovalski.texthandler.entity.ComponentType;
import by.kovalski.texthandler.entity.TextComposite;
import by.kovalski.texthandler.exception.TextHandlerException;
import by.kovalski.texthandler.service.TextHandlerService;
import by.kovalski.texthandler.util.Alphabet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextHandlerServiceImpl implements TextHandlerService {
  private static final Logger logger = LogManager.getLogger();

  @Override
  public List<TextComponent> sortParagraphs(TextComponent component) throws TextHandlerException {
    if (component.getType() != ComponentType.TEXT) {
      logger.error("Not correct type of component");
      throw new TextHandlerException("Not correct type of component");
    }
    List<TextComponent> paragraphs = new ArrayList<>(((TextComposite) component).getChildren().stream().filter(o -> o.getType() == ComponentType.PARAGRAPH).toList());
    paragraphs.sort((o1, o2) -> getSentencesNumber(o1) - getSentencesNumber(o2));
    return paragraphs;
  }

  @Override
  public List<TextComponent> sentencesWithTheBiggestWord(TextComponent component) throws TextHandlerException {
    if (component.getType() != ComponentType.TEXT) {
      logger.error("Not correct type of component");
      throw new TextHandlerException("Not correct type of component");
    }
    List<TextComponent> paragraphs = ((TextComposite) component).getChildren().stream().filter(o -> o.getType() == ComponentType.PARAGRAPH).toList();
    int currentLength = 0;
    List<TextComponent> out = new ArrayList<>();
    for (TextComponent paragraph : paragraphs) {
      for (TextComponent sentence : ((TextComposite) paragraph).getChildren().stream().filter(o -> o.getType() == ComponentType.SENTENCE).toList()) {
        int length = lengthOfTheBiggestWordInSentence(sentence);
        if (length > currentLength) {
          out.clear();
          out.add(sentence);
          currentLength = length;
        } else if (length == currentLength) {
          out.add(sentence);
        }
      }
    }
    return out;
  }

  @Override
  public List<Integer> countNumberOfVowelAndConsonantLetters(TextComponent component) throws TextHandlerException {
    if (component.getType() != ComponentType.TEXT) {
      logger.error("Not correct type of component");
      throw new TextHandlerException("Not correct type of component");
    }
    TextComposite text = (TextComposite) component;
    List<TextComponent> paragraphs = text.getChildren().stream().filter(o -> o.getType() == ComponentType.PARAGRAPH).toList();
    List<TextComponent> sentences = new ArrayList<>();
    List<TextComponent> words = new ArrayList<>();
    List<TextComponent> letters = new ArrayList<>();
    for (TextComponent paragraph : paragraphs) {
      sentences.addAll(((TextComposite) paragraph).getChildren().stream().filter(o -> o.getType() == ComponentType.SENTENCE).toList());
    }
    for (TextComponent sentence : sentences) {
      words.addAll(((TextComposite) sentence).getChildren().stream().filter(o -> o.getType() == ComponentType.WORD).toList());
    }

    for (TextComponent word : words) {
      letters.addAll(((TextComposite) word).getChildren());
    }

    int vowelCounter = 0;
    int consonantCounter = 0;

    for (TextComponent letter : letters) {
      Character character = ((SymbolLeaf) letter).getSymbol();
      if (Alphabet.VOWEL_ENG.getLetters().contains(character) || Alphabet.VOWEL_RUS.getLetters().contains(character)) {
        vowelCounter++;
      } else if (Alphabet.CONSONANT_ENG.getLetters().contains(character) || Alphabet.CONSONANT_RUS.getLetters().contains(character)) {
        consonantCounter++;
      }
    }
    return List.of(vowelCounter, consonantCounter);
  }

  @Override
  public Map<TextComponent, Integer> findEqualsWords(TextComponent component) throws TextHandlerException {//FIXME
    if (component.getType() != ComponentType.TEXT) {
      logger.error("Not correct type of component");
      throw new TextHandlerException("Not correct type of component");
    }
    TextComposite text = (TextComposite) component;
    List<TextComponent> paragraphs = text.getChildren().stream().filter(o -> o.getType() == ComponentType.PARAGRAPH).toList();
    List<TextComponent> sentences = new ArrayList<>();
    List<TextComponent> words = new ArrayList<>();
    for (TextComponent paragraph : paragraphs) {
      sentences.addAll(((TextComposite) paragraph).getChildren().stream().filter(o -> o.getType() == ComponentType.SENTENCE).toList());
    }
    for (TextComponent sentence : sentences) {
      words.addAll(((TextComposite) sentence).getChildren().stream().filter(o -> o.getType() == ComponentType.WORD).toList());
    }

    Map<TextComponent, Integer> out = new HashMap<>();
    for (TextComponent word1 : words) {
      for (TextComponent word2 : words) {
        if (word1 != word2) {
          TextComposite word1WithoutReg = new TextComposite(new ArrayList<>(), ComponentType.WORD);
          for (TextComponent letter : ((TextComposite) word1).getChildren()) {
            word1WithoutReg.add(new SymbolLeaf(Character.toLowerCase(((SymbolLeaf) letter).getSymbol()), ComponentType.LETTER));
          }
          TextComposite word2WithoutReg = new TextComposite(new ArrayList<>(), ComponentType.WORD);
          for (TextComponent letter : ((TextComposite) word2).getChildren()) {
            word2WithoutReg.add(new SymbolLeaf(Character.toLowerCase(((SymbolLeaf) letter).getSymbol()), ComponentType.LETTER));
          }
          if (word1WithoutReg.equals(word2WithoutReg)) {
            if (out.containsKey(word1WithoutReg)) {
              int value = out.get(word1WithoutReg);
              out.remove(word1WithoutReg);
              out.put(word1WithoutReg, ++value);
            } else {
              out.put(word1WithoutReg, 2);
            }
          }
        }
      }
    }
    return out;
  }

  @Override
  public List<TextComponent> sentencesWithWordsMoreThan(TextComponent component, int number) throws TextHandlerException {
    if (component.getType() != ComponentType.TEXT) {
      logger.error("Not correct type of component");
      throw new TextHandlerException("Not correct type of component");
    }
    TextComposite text = (TextComposite) component;
    List<TextComponent> paragraphs = text.getChildren().stream().filter(o -> o.getType() == ComponentType.PARAGRAPH).toList();
    List<TextComponent> sentences = new ArrayList<>();
    for (TextComponent paragraph : paragraphs) {
      sentences.addAll(((TextComposite) paragraph).getChildren().stream().filter(o -> o.getType() == ComponentType.SENTENCE).toList());
    }
    return sentences.stream().filter(o -> ((TextComposite) o).getChildren().stream().filter(o1 -> o1.getType() == ComponentType.WORD).toList().size() >= number).toList();
  }

  private int lengthOfTheBiggestWordInSentence(TextComponent component) {
    if (component.getClass() != TextComposite.class || ((TextComposite) component).getType() != ComponentType.SENTENCE) {
      logger.error("Not correct type of component");
      throw new UnsupportedOperationException("Not correct type of component");
    }
    int maxLength = 0;
    List<TextComponent> words = ((TextComposite) component).getChildren().stream().filter(o -> o.getType() == ComponentType.WORD).toList();
    for (TextComponent word : words) {
      int length = ((TextComposite) word).getChildren().size();
      if (length > maxLength) {
        maxLength = length;
      }
    }
    return maxLength;
  }

  private int getSentencesNumber(TextComponent component) {
    if (component.getClass() != TextComposite.class || ((TextComposite) component).getType() != ComponentType.PARAGRAPH) {
      logger.error("Not correct type of component");
      throw new UnsupportedOperationException("Not correct type of component");
    }
    return ((TextComposite) component).getChildren().stream().filter(o -> o.getType() == ComponentType.SENTENCE).toList().size();
  }

}
