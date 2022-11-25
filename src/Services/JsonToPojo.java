package Services;

import Objects.Genres;
import Objects.Library;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonToPojo {
    public Library parse(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fileReader = new FileReader(file);
        Library library = gson.fromJson(fileReader, Library.class);

        return library;

    }
}
