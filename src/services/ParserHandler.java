package services;

import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Details;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ParserHandler extends DefaultHandler {

    /*
    В данном классе описан принцип парсинга из XML-файла в объектную модель, библиотека StAX была выбрана не случайна, при помощи неё
    можно сразу избирать данные из тегов и помещать в конкретные классы, тем самым я добился не только десериализации XML-файла, но еще и изменил его структуру,
    поместив необходимые данные сразу классы измененной структуры.
    */

  private static final String TAG_LIBRARY = "library";
  private static final String TAG_BOOK = "book";
  private static final String TAG_AUTHOR = "author";
  private static final String TAG_TITLE = "title";
  private static final String TAG_DETAILS = "details";
  private static final String TAG_GENRES = "genres";
  private static final String TAG_PAGES = "pages";
  private static final String TAG_PUBLICATIONYEAR = "publicationYear";
  private static final String TAG_ABOUT = "about";

  // Выше представленны тэги из XML-файла для быстрой идентификации внутри десериализации.

  private boolean isBook = false;
  private boolean isDetails = false;

    /*
    Выше представлены логические переменные которые помогают алгоритму хандлера понимать где конкретно происходит
    извлечение данных из файла.
    */

  // Ниже представлены искомые внутри файла объекты для десериализации.
  private Authors authors = new Authors();
  private List<Author> authorList = new ArrayList<>();
  private Author author;

  // Ниже представлены автономные объекты не требующие изменения структуры.
  private List<Book> books = new ArrayList<>();
  private Book book = new Book();
  private Details details = new Details();

  // Ниже представлен необходимый для получения итогового объекта геттер.
  public Authors getAuthors() {
    return authors;
  }

  private String currentTagName;

  @Override
  public void startDocument() throws SAXException {
  }

  @Override
  public void endDocument() throws SAXException {

    authors.setAuthors(
        authorList); // Финальный этап работы алгоритма - присваивание значений коллекции авторов в объект Authors, который мы и должны получить.
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {

    currentTagName = qName; // Присваивание переменной название текущего тега.
    if (currentTagName != null) {
      // Проверка на null и ниже проверка на нахождения тега внутри внешних тегов.
      if (currentTagName.equals(TAG_BOOK)) {
        isBook = true;
      } else if (currentTagName.equals(TAG_DETAILS)) {
        isDetails = true;
      }
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) throws SAXException {

    currentTagName = qName; // Повторное присваивание значения необходимо, т.к когда идет закрытие нескольких тегов подряд, currentTagName не обновляется.
    if (currentTagName == null) {
      return; // Проверка на null
    } else {
      if (currentTagName.equals(TAG_BOOK)) {
        isBook = false;

        books.add(book);
        author.setBooks(books);
        authorList.add(author);

        // Выше алгоритм добавляет все необходимые данные в искомые и автономные объекты, ниже - обнуляет для присваивания данных из новых тегов.

        book = new Book();
        books = new ArrayList<>();
      } else if (currentTagName.equals(TAG_DETAILS)) {
        isDetails = false;

        // Выше алгоритм добавляет все необходимые данные в искомые и автономные объекты, ниже - обнуляет для присваивания данных из новых тегов.

        book.setDetails(details);
        details = new Details();
      }
      ;
      currentTagName = null; // Обнуление переменной в которой хранится текущий тег.
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    if (currentTagName == null) {
      return; // Проверка на null с целью исключения ошибок эксепшена.
    }

        /*
        Ниже представлен алгоритм создания и / или присваивания найденных значений внутри тега - созданным выше переменным.
        */

    if (isBook) {
      switch (currentTagName) {
        case (TAG_AUTHOR):
          author = new Author(new String(ch, start, length));
          break;
        case (TAG_TITLE):
          book.setTitle(new String(ch, start, length));
          break;
      }
      if (isDetails) {
        switch (currentTagName) {
          case (TAG_GENRES):
            details.setGenres(new String(ch, start, length));
            break;
          case (TAG_PAGES):
            details.setPages(Integer.parseInt(new String(ch, start, length)));
            break;
          case (TAG_PUBLICATIONYEAR):
            details.setPublicationYear(Integer.parseInt(new String(ch, start, length)));
            break;
          case (TAG_ABOUT):
            details.setAbout(new String(ch, start, length));
            break;
        }
      }
    }
  }
}
