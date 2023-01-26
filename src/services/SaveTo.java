package services;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import lombok.val;
import objects.Authors;
import objects.Library;

public class SaveTo {

  public static void toXML(Library library, String output) throws JAXBException {
            /*
            Сериализация была сделана при помощи JAXB
            */

    val context = JAXBContext.newInstance(Library.class);
    val marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(library, new File(output + ".xml"));
  }

  public static void toJson(Authors authors, String output) throws IOException {
            /*
            Для сериализации была выбрана библиотека GSON, удобство, простота, отсутствие багов после тестирования в созданном файле.
            */

    val gson = new Gson();
    Files.write(Path.of(output + ".json"), gson.toJson(authors).getBytes());
  }
}
