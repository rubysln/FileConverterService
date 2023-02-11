package services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import objects.Author;
import objects.Book;
import objects.Details;
import objects.Library;
import org.junit.Test;

public class ParseToXMLTest {

  @Test
  public void parseFromJson() throws IOException {
    //act
    List<Book> bookListExpected = new ArrayList<>();

    //Достоевский
    Book idiot = new Book();
    idiot.setAuthor("Достоевский Роман Фёдорович");
    idiot.setTitle("Идиот");
    Details idiotDetails = new Details();
    idiotDetails.setGenres("Классика, Роман");
    idiotDetails.setPages(567);
    idiotDetails.setPublicationYear(1868);
    idiot.setDetails(idiotDetails);
    bookListExpected.add(idiot);

    //Лермонтов
    Book heroOfOurTime = new Book();
    heroOfOurTime.setTitle("Герой нашего времени");
    heroOfOurTime.setAuthor("Михаил Юрьевич Лермонтов");
    Details heroOfOurTimeDetails = new Details();
    heroOfOurTimeDetails.setGenres("Роман, Романтизм, Психологический реализм");
    heroOfOurTimeDetails.setPages(152);
    heroOfOurTimeDetails.setPublicationYear(1840);
    heroOfOurTime.setDetails(heroOfOurTimeDetails);
    bookListExpected.add(heroOfOurTime);

    //Пушкин
    Book fairyTales = new Book();
    fairyTales.setTitle("Сказки");
    fairyTales.setAuthor("Пушкин Александр Сергеевич");
    Details fairyTalesDetails = new Details();
    fairyTalesDetails.setPublicationYear(1981);
    fairyTalesDetails.setPages(132);
    fairyTalesDetails.setGenres("Классика, Сказки");
    fairyTales.setDetails(fairyTalesDetails);
    bookListExpected.add(fairyTales);

    Book evgeniyOnegin = new Book();
    evgeniyOnegin.setAuthor("Пушкин Александр Сергеевич");
    evgeniyOnegin.setTitle("Евгений Онегин");
    Details evgeniyOneginDetails = new Details();
    evgeniyOneginDetails.setGenres("Классика, Роман");
    evgeniyOneginDetails.setPages(256);
    evgeniyOneginDetails.setPublicationYear(1833);
    evgeniyOnegin.setDetails(evgeniyOneginDetails);
    bookListExpected.add(evgeniyOnegin);

    //Expected
    Library libraryExpected = new Library(bookListExpected);

    //Actual
    Library libraryActual = ParseToXML.parseFromJson(new File("output.json"));

    assertEquals(libraryExpected, libraryActual);
  }
}