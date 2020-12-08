import processing.core.PApplet;
import processing.core.PVector;

public class Meilenstein3Aufgabe3{

    private static final PVector p1 = new PVector(2, 3);
    private static final PVector p2 = new PVector(-4, -1);
    private static final PVector p3 = new PVector(3, -3);

    public static void main(String[] args) {
        System.out.println("3DCG: Aufgabenblatt 3 - Aufgabe 3\n");


        /*
         * a) Konstruiert folgende Vektoren
         * - v1To2: Vektor ausgehend vom Punkt p1 zum Punkt p2
         * - v1To3: Vektor ausgehend vom Punkt p1 zum Punkt p3
         */
        System.out.println("Aufgabe a)");

        System.out.print("v1To2: ");
        PVector v1To2 = PVector.sub(p2, p1);
        System.out.println(v1To2);

        System.out.print("v1To3: ");
        PVector v1To3 = PVector.sub(p3, p1);
        System.out.println(v1To3);

        System.out.println();

        /*
         * b) Bestimmt die Länge der Vektoren v1To2 und v1To3.
         */
        System.out.println("Aufgabe b)");
        System.out.print("|v1To2|: ");
        System.out.println(v1To2.mag());

        System.out.print("|v1To3|: ");
        System.out.println(v1To3.mag());

        System.out.println();

        /*
         * c) Bestimmt den Abstand der Punkte p2 und p3.
         */
        System.out.println("Aufgabe c)");
        //Vektor zwischen p2 und p3 konstruieren
        PVector v2To3 = PVector.sub(p3, p2);
        System.out.print("v2To3: ");
        System.out.println(v2To3);

        //Länge bestimmen
        System.out.print("|v2To3|: ");
        System.out.println(v2To3.mag());

        System.out.println();

        /*
         * d) Skaliert den Vektor v1To2 mit den Faktoren -2 und 3.
         */
        System.out.println("Aufgabe d)");
        System.out.print("v1To2 skaliert mit -2: ");
        System.out.println(PVector.mult(v1To2, -2));

        System.out.print("v1To2 skaliert mit 3: ");
        System.out.println(PVector.mult(v1To2, 3));

        System.out.println();


        /*
         * e) Normalisiert die Vektoren v1To2 und v1To3
         */
        System.out.println("Aufgabe e)");
        System.out.print("v1To2 normalisiert: ");
        System.out.println(PVector.div(v1To2, v1To2.mag()));


        System.out.print("v1To3 normalisiert: ");
        System.out.println(PVector.div(v1To3, v1To3.mag()));

        System.out.println();


        /*
         * f) Bestimmt den Winkel zwischen den Vektoren v1To2 und v1To3
         */
        System.out.println("Aufgabe e)");
        System.out.print("Cosinus des Winkels zwischen v1To2 und v1To3: ");
        System.out.println(v1To2.dot(v1To3) / (v1To2.mag() * v1To3.mag()));
        System.out.print("Winkel zwischen v1To2 und v1To3: ");

        System.out.println(PApplet.acos(v1To2.dot(v1To3) / (v1To2.mag() * v1To3.mag())));

        System.out.println();


        /*
         * g) Bestimmt die Normale der von den Vektoren v1To2 und v1To3 aufgespannten Ebene.
         */
        System.out.println("Aufgabe g)");
        System.out.print("Normale der Fläche aufgespannt zwischen v1To2 und v1To3: ");
        System.out.println(v1To2.cross(v1To3, null));

        System.out.println();

        /*
         * i) Ein Objekt befindet sich aktuell in p1 und bewegt sich jede Sekunde 2 Einheiten in Richtung des Vektors v1To2.
         * Bestimmt die Position des Objektes vor einer Sekunde und in drei Sekunden.
         */
        System.out.println("Aufgabe i)");
        System.out.print("Position vor 1 Sekunde: ");
        System.out.println(PVector.add(p1, PVector.mult(v1To2, (-1 * 2))));

        System.out.print("Position nach 3 Sekunden: ");
        System.out.println(PVector.add(p1, PVector.mult(v1To2, (3 * 2))));

        System.out.println();


        /*
         * j) Gebt einen Algorithmus an, um zu prüfen, ob zwei Vektoren orthogonal sind, d.h. im Winkel von 90 Grad stehen.
         */
        System.out.println("Aufgabe j)");
        PVector e1 = new PVector(1,0);
        PVector e2 = new PVector(0,1);
        System.out.println("e1: " + e1);
        System.out.println("e2: " + e2);
        System.out.print("e1 ist zu e2 orthogonal?: ");
        if (e1.dot(e2) == 0) {
            System.out.println("Ja");
        } else {
            System.out.println("Nein");
        }

    }
}
