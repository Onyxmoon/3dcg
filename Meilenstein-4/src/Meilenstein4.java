import Figure.CoordinateSystem;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Meilenstein4 extends PApplet {


    public static void main(String[] args) {
        PApplet.main(Meilenstein4.class);
    }

    CoordinateSystem c = new CoordinateSystem(this);

    @Override
    public void settings() {
        super.settings();
        size(1000, 1000, PConstants.P3D);
    }

    @Override
    public void setup() {
        super.setup();
        surface.setResizable(true);

        //Setup coordinate system
        c.resolution = 24;
        c.axisThickness = 0.3f;
        c.axisLength = 10f;
        c.dimension = CoordinateSystem.Dimension.D3D;
    }

    @Override
    public void draw() {
        background(255);

        c.rotationX = radians(-20);
        c.rotationY = radians(frameCount);
        c.scale = min(width, height) / 25f;
        c.position = new PVector(width / 2f, height / 2f, 0);

        c.render();
        System.out.println(frameRate);
    }
}
