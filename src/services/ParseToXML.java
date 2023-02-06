package services;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.val;
import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Library;

public class ParseToXML {
  public static Library parseFromJson(File file) throws IOException {
            /*
            Для десериализации была выбрана та же библиотека GSON по тем же причинам, идеальная работа с .json файлами!
            */

    val gson = new Gson();
    val fileReader = new FileReader(file);
    Authors authors = gson.fromJson(
        fileReader, Authors.class);
    List<Book> books = new ArrayList<>();
    for (Author element : authors.getAuthors()) {
      for (Book book : element.getBooks()) {
        book.setAuthor(element.getName());
        books.add(book);
      }
    }
    return new Library(books);
  }
}
