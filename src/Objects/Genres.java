package Objects;

import java.util.List;

public class Genres {
    private String name;

    public Genres(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genres{" +
                "name='" + name + '\'' +
                '}';
    }
}
