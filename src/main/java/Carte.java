import java.util.Objects;

public record Carte(String titlul, String autorul, int anul)
{
    @Override
    public String toString()
    {
        return "Carte{" + "titlu='" + titlul + '\'' + ", autor='" + autorul + '\'' + ", anAparitie=" + anul + '}';
    }
}
