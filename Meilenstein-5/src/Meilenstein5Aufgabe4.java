import processing.core.PConstants;
import processing.core.PMatrix3D;
import processing.core.PVector;

public class Meilenstein5Aufgabe4 {

    public static void main(String[] args) {
        /*
          a) Listet auf welche Transformationen in der Matrix vereinigt sind.
          Das Processing-Scetch soll die Einheitsmatrix (erzeugt durch new
          PMatrix3D()) durch verschiedene Transformation in die oben gegebene
          Matrix umformen und diese auf der Konsole ausgeben.
         */

        System.out.println("Aufgabe a)");
        PMatrix3D m1 = new PMatrix3D();
        System.out.println("Verschiebe um (5, 3, 4)");
        m1.translate(5, 3, 4);
        m1.print();
        System.out.println("Skaliere alle Achsen um 2");
        m1.scale(2);
        m1.print();
        System.out.println("Rotation um z um asin(1) = PI/2");
        m1.rotateZ(PConstants.PI / 2f);
        m1.print();

        /*
          b) Multipliziert den Vektor (1, 2, 3) mit der oben gegeben Matrix. Für die
          Berechnung müsst ihr den Vektor um die homogene Komponente erweitern.
          Im Endergebnis soll die homogene Komponente wieder entfernt sein.
         */
        System.out.println("Aufgabe b) - Fehler in Aufgabenstellung!");
        System.out.println("Multipliziere Matrix m1 mit Vektor (1, 2, 3)");
        PVector vm1 = m1.mult(new PVector(1, 2, 3), null);
        System.out.println(vm1);

        /*
        c) Multipliziert folgende Matrix mit der oben gegebenen Matrix.
         */
        System.out.println("Aufgabe a)");
        PMatrix3D m2 = new PMatrix3D();
        m2.m00 = 0;m2.m01 = 2;m2.m02 = 0;m2.m03 = 4;
        m2.m10 = -2;m2.m11 = 0;m2.m12 = 0;m2.m13 = 5;
        m2.m20 = 0;m2.m21 = 0;m2.m22 = 2;m2.m23 = 3;
        m2.m30 = 0;m2.m31 = 0;m2.m32 = 0;m2.m33 = 1;
        System.out.println("Ausgangsmatrix: ");
        m2.print();
        System.out.println("Multiplizieren mit m1 ergibt: ");
        m2.apply(m1);
        m2.print();
    }
}
