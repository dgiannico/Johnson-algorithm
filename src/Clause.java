import java.util.HashSet;
import java.util.Objects;

public class Clause {
    int id;
    HashSet<Literal> literals;

    public Clause(int id) {
        this.id = id;
        literals = new HashSet<Literal>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<Literal> getLiterals() {
        return literals;
    }

    public void setLiterals(HashSet<Literal> literals) {
        this.literals = literals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clause clause = (Clause) o;
        return id == clause.id;
    }

    @Override
    public String toString() {
        return "Clause{" +
                "id=" + id +
                ", literals=" + literals +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
