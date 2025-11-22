import java.util.Objects;

public class RequestClassInfo {
    private String url;

    public RequestClassInfo(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.url);
    }

    @Override
    public boolean equals(Object obj) {

        // Вариант 1 - когато сравнявам себе си
        if(this == obj) return true;

        // Вариант 2 - проверка за NULL стойности
        if(obj == null) return false;

        // Вариант 3 - правим същинско сравнение на стойности
        RequestClassInfo comparable = (RequestClassInfo) obj;
        return Objects.equals(this.url, comparable.url);
    }
}