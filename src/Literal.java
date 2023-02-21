import java.util.HashSet;
import java.util.Objects;

public class Literal {
    int id;
    HashSet<Clause> involvedClauses;

    public Literal(int id) {
        this.id = id;
        involvedClauses = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet<Clause> getInvolvedClauses() {
        return involvedClauses;
    }

    public void setInvolvedClauses(HashSet<Clause> involvedClauses) {
        this.involvedClauses = involvedClauses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return id == literal.id;
    }

    @Override
    public String toString() {
        return "Literal: " + id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
