import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class JohnsonSingleInstance {

    final static String fileName = "instances/" + "max2sat-120v/s2v120c1200-1.cnf";

    public static void johnson(HashSet<Literal> L, HashSet<Clause> S) {
        // 1.
        HashSet<Clause> SUB = new HashSet<>();
        HashSet<Literal> TRUE = new HashSet<>();
        HashSet<Clause> LEFT = new HashSet<>(S);
        HashSet<Literal> LIT = new HashSet<>(L);

        while (check(LEFT, LIT)) { // 5. -> 2.
            // 3.
            Literal y = new Literal(1);
            int bestNClausesInvolved = 0;
            for (Literal literal: LIT) {
                if (literal.getInvolvedClauses().size() > bestNClausesInvolved) {
                    bestNClausesInvolved = literal.getInvolvedClauses().size();
                    y = literal;
                }
            }
            HashSet<Clause> YT = y.getInvolvedClauses();

            Literal notY = new Literal(-y.id);
            // 4.
            SUB.addAll(YT);
            LEFT.removeAll(YT);
            TRUE.add(y);
            LIT.remove(y);
            LIT.remove(notY);
        }

        System.out.println("Solution: " + SUB.size());
        System.out.println("Literals fixed:\n" + TRUE);
    }

    public static boolean check(HashSet<Clause> LEFT, HashSet<Literal> LIT) { // 2.
        for(Clause clause: LEFT)
            for (Literal literal: clause.getLiterals())
                if(LIT.contains(literal))
                    return true;
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));

        String s = scanner.nextLine();
        while (s.charAt(0) == 'c')
            s = scanner.nextLine();

        String[] parts = s.split("\s+");
        int nVariables = Integer.parseInt(parts[2]);
        int nClauses = Integer.parseInt(parts[3]);

        System.out.println("Variables: " + nVariables + "\nClauses: " + nClauses);

        // INIZIALITAZION

        HashMap<Integer, Literal> L = new HashMap<>();
        HashSet<Clause> S = new HashSet<>();

        for (int i = 1; i <= nVariables; i++) {
            Literal literal = new Literal(i);
            Literal notLiteral = new Literal(-i);
            L.put(i, literal);
            L.put(-i, notLiteral);
        }

        for (int i = 1; i <= nClauses; i++) {
            Clause clause = new Clause(i);
            int v = scanner.nextInt();
            while (v != 0) {
                Literal literal = L.get(v);
                clause.getLiterals().add(literal);
                literal.getInvolvedClauses().add(clause);
                v = scanner.nextInt();
            }
            S.add(clause);
            scanner.nextLine();
        }

        johnson(new HashSet<>(L.values()), S);
        scanner.close();
    }

}
