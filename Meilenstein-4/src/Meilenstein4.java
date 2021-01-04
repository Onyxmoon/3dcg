import Figure.CoordinateSystem;
import Shape.CircularCone;
import Shape.Cylinder;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Meilenstein4 extends PApplet {


    public static void main(String[] args) {
        PApplet.main(Meilenstein4.class);
    }

    //Programm data
    ShapeMode shapeMode = ShapeMode.NONE;
    CircularConeManipulationMode circularConeMode = CircularConeManipulationMode.SEGMENTS;
    CylinderManipulationMode cylinderMode = CylinderManipulationMode.BASE_SEGMENTS;

    //Coordinate system data
    CoordinateSystem c = new CoordinateSystem(this);
    PVector originVertex;
    float xOriginRotation = -20;
    float yOriginRotation = 0;
    float zoom = 0;

    //Mouse data
    float xOffset = 0.0f;
    float yOffset = 0.0f;

    //Screen data
    int w, h;

    //Model data
    public int circularConeSegments = 20;
    public float circularConeRadius = 1.5f;
    public float circularConeHeight = 3;

    public int cylinderBaseSegments = 20;
    public int cylinderSideSegments = 10;
    public float cylinderRadius = 1.5f;
    public float cylinderHeight = 3;

    @Override
    public void settings() {
        super.settings();
        size(1000, 1000, PConstants.P3D);
    }

    @Override
    public void setup() {
        super.setup();
        surface.setResizable(true);

        //Setup origin
        originVertex = new PVector(width / 2f,height / 2f);

        //Preserves current screen properties
        w = width;
        h = height;

        //Setup coordinate system
        c.resolution = 12;
        c.axisThickness = 0.3f;
        c.axisLength = 10f;
        c.dimension = CoordinateSystem.Dimension.D3D;
    }

    private void adaptScreen() {
        if (w != width || h != height) {
            w = width;
            h = height;
            originVertex = new PVector(width / 2f,height / 2f);
        }
    }



    @Override
    public void draw() {
        adaptScreen();
        //Reduce clipping of near plane to minimum
        perspective(PI/3.0f,(float)width/height,1,100000);

        background(255);

        pushMatrix();
        translate(originVertex.x, originVertex.y, zoom);
        rotateX(radians(xOriginRotation));
        rotateY(radians(yOriginRotation));
        rotateZ(radians(180));

        c.scale = min(width, height) / 25f;
        c.render();

        //Solid
        pushMatrix();
        switch (shapeMode) {
            case CIRCULAR_CONE:
                CircularCone cone = new CircularCone(this);
                cone.position = new PVector(150,150,150);
                cone.scale = min(width, height) / 25f;
                cone.segments = circularConeSegments;
                cone.radius = circularConeRadius;
                cone.height = circularConeHeight;

                fill(255);
                stroke(0);
                cone.render();
                break;
            case CYLINDER:
                Cylinder cylinder = new Cylinder(this);
                cylinder.position = new PVector(150,150,150);
                cylinder.scale = min(width, height) / 25f;
                cylinder.baseSegments = cylinderBaseSegments;
                cylinder.sideSegments = cylinderSideSegments;
                cylinder.radius = cylinderRadius;
                cylinder.height = cylinderHeight;

                fill(255);
                stroke(0);
                cylinder.render();
                break;
        }

        popMatrix();

        popMatrix();

        drawText();

        System.out.println(frameRate);
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        super.mouseWheel(event);
        if (event.getCount() < 0) {
            this.zoom += 40;
        } else {
            this.zoom = this.zoom - 35 < 0 ? 0 : this.zoom - 35;
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        super.mousePressed(event);
        switch (event.getButton()) {
            case PConstants.LEFT: xOffset = mouseX- originVertex.x; yOffset = mouseY- originVertex.y; break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        super.mouseDragged(event);
        switch (event.getButton()) {
            case PConstants.LEFT: originVertex.x = mouseX-xOffset; originVertex.y = mouseY-yOffset; break;
            case PConstants.RIGHT: xOriginRotation += -(mouseY-pmouseY) * 0.1f; yOriginRotation += (mouseX-pmouseX) * 0.1f; break;
        }


    }

    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (key == 's') {
            shapeMode = shapeMode.next();
        } else if (key == '+') {
            switch (shapeMode) {
                case CIRCULAR_CONE:
                    switch (circularConeMode) {
                        case SEGMENTS:
                            circularConeSegments++;
                            break;
                        case RADIUS:
                            circularConeRadius += 0.1f;
                            break;
                        case HEIGHT:
                            circularConeHeight += 0.1f;
                            break;
                    }
                    break;
                case CYLINDER:
                    switch (cylinderMode) {
                        case BASE_SEGMENTS:
                            cylinderBaseSegments++;
                            break;
                        case SIDE_SEGMENTS:
                            cylinderSideSegments++;
                            break;
                        case RADIUS:
                            cylinderRadius += 0.1f;
                            break;
                        case HEIGHT:
                            cylinderHeight += 0.1f;
                            break;
                    }
                    break;
            }
        } else if (key == '-') {
            switch (shapeMode) {
                case CIRCULAR_CONE:
                    switch (circularConeMode) {
                        case SEGMENTS:
                            circularConeSegments--;
                            break;
                        case RADIUS:
                            circularConeRadius -= 0.1f;
                            break;
                        case HEIGHT:
                            circularConeHeight -= 0.1f;
                            break;
                    }
                    break;
                case CYLINDER:
                    switch (cylinderMode) {
                        case BASE_SEGMENTS:
                            cylinderBaseSegments--;
                            break;
                        case SIDE_SEGMENTS:
                            cylinderSideSegments--;
                            break;
                        case RADIUS:
                            cylinderRadius -= 0.1f;
                            break;
                        case HEIGHT:
                            cylinderHeight -= 0.1f;
                            break;
                    }
                    break;
            }
        } else if (key == 'm') {
            switch (shapeMode) {
                case CIRCULAR_CONE:
                    circularConeMode = circularConeMode.next();
                    break;
                case CYLINDER:
                    cylinderMode = cylinderMode.next();
                    break;
            }
        }
    }

    private void drawText() {
        pushMatrix();
        hint(DISABLE_DEPTH_TEST);
        fill(0);
        rect(0, height - 35, width, 35);
        translate(5, height - 5);
        textAlign(LEFT, BOTTOM);
        String shapeModeText = "";
        String manipulationModeText = "";
        float manipulationValue = 0;
        switch (shapeMode) {
            case CIRCULAR_CONE:
                shapeModeText = "Circular cone";
                switch (circularConeMode) {
                    case SEGMENTS:
                        manipulationModeText = "SEGMENTS";
                        manipulationValue = circularConeSegments;
                        break;
                    case RADIUS:
                        manipulationModeText = "RADIUS";
                        manipulationValue = circularConeRadius;
                        break;
                    case HEIGHT:
                        manipulationModeText = "HEIGHT";
                        manipulationValue = circularConeHeight;
                        break;
                }
                break;
            case CYLINDER:
                shapeModeText = "Cylinder";
                switch (cylinderMode) {
                    case BASE_SEGMENTS:
                        manipulationModeText = "BASE SEGMENTS";
                        manipulationValue = cylinderBaseSegments;
                        break;
                    case SIDE_SEGMENTS:
                        manipulationModeText = "SIDE SEGMENTS";
                        manipulationValue = cylinderSideSegments;
                        break;
                    case RADIUS:
                        manipulationModeText = "RADIUS";
                        manipulationValue = cylinderRadius;
                        break;
                    case HEIGHT:
                        manipulationModeText = "HEIGHT";
                        manipulationValue = cylinderHeight;
                        break;
                }
                break;
        }


        String text = "Shape (s): [NONE]";
        if (shapeMode != ShapeMode.NONE) {
            text = String.format("Shape (s): [%s] \t Mode (m): [%s] \t Value (+/-): [%.1f]", shapeModeText, manipulationModeText, manipulationValue);
        }

        textSize(20);
        fill(255);
        text(text, 0, 0);
        hint(ENABLE_DEPTH_TEST);
        popMatrix();
    }

    private enum ShapeMode {
        NONE, CIRCULAR_CONE, CYLINDER;
        private static final ShapeMode[] vals = values();
        public ShapeMode next()
        {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    private enum CircularConeManipulationMode {
        SEGMENTS, RADIUS, HEIGHT;
        private static final CircularConeManipulationMode[] vals = values();
        public CircularConeManipulationMode next()
        {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    private enum CylinderManipulationMode {
        BASE_SEGMENTS, SIDE_SEGMENTS, RADIUS, HEIGHT;
        private static final CylinderManipulationMode[] vals = values();
        public CylinderManipulationMode next()
        {
            return vals[(this.ordinal()+1) % vals.length];
        }
    }
}
