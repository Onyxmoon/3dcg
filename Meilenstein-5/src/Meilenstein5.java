import Figure.CoordinateSystem;
import Shape.CircularCone;
import Shape.Cylinder;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.*;
import processing.event.MouseEvent;


public class Meilenstein5 extends PApplet {

    //// Programm data
    CameraMode currentCameraMode = CameraMode.Perspective;
    boolean musicPaused = true;
    boolean explosionInProgress = false;

    //// 3D figures
    CoordinateSystem cs;
    boolean showWorldCoordinateSystem = false;
    boolean showModelCoordinateSystems = false;
    // Cones & cylinders
    float minZ = -10000;
    float maxZ = 1500;
    int shapeCount;
    Shape.CircularCone[] cones;
    Shape.Cylinder[] cylinders;

    //// Display data
    int currentDisplayWidth = 1920;
    int currentDisplayHeight = 1080;

    //// Camera data
    // Vectors
    PVector up = new PVector(0, 1, 0);
    PVector eye = new PVector(width / 2.0f, height / 2.0f, (height / 2.0f) / tan(PI * 30.0f / 180.0f));
    PVector center = new PVector(width / 2.0f, height / 2.0f, 0);

    // Rotation
    float xRotation = 0.0f;
    float yRotation = 0.0f;

    // Translation
    float xTransformationDelta = 0.0f;
    float yTransformationDelta = 0.0f;
    float zTransformationDelta = 0.0f;


    //// Music
    Minim minimAnalyzer;
    AudioPlayer player;
    FFT fft;

    // Percentages of the total spectrum for each frequency group
    float spectrumLowBand = 0.041f;
    float spectrumMiddleBand = 0.13f;
    float spectrumHighBand = 0.25f;


    // Running average score
    float scoreLowBand = 0;
    float scoreMiddleBand = 0;
    float scoreHighBand = 0;

    // Values of the previous music frame to avoid artifacts
    float oldScoreLowBand = scoreLowBand;
    float oldScoreMiddleBand = scoreMiddleBand;
    float oldScoreHighBand = scoreHighBand;

    // Softening value
    float scoreSofteningValue = 25;

    // Current amplitude of the total spectrum.
    float scoreVolume;

    public static void main(String[] args) {
        PApplet.main(Meilenstein5.class);
    }

    @Override
    public void settings() {
        super.settings();
        size(currentDisplayWidth, currentDisplayHeight, PConstants.P3D);
        //fullScreen(PConstants.P3D);
    }

    @Override
    public void setup() {
        super.setup();
        surface.setResizable(true);

        //// Initialize audio components
        minimAnalyzer = new Minim(this);
        player = minimAnalyzer.loadFile("PogoWeightlessGiving.wav");
        fft = new FFT(player.bufferSize(), player.sampleRate());

        //// Initialize shapes
        // Per used band, one shape
        shapeCount = (int) (fft.specSize() * spectrumHighBand * 0.6f);
        cones = new CircularCone[shapeCount];
        for (int i = 0; i < cones.length; i++) {
            cones[i] = new CircularCone(this);
            float xPosition = random(0, width);
            float yPosition = random(0, height);
            float zPosition = random(minZ, maxZ);
            cones[i].position = new PVector(xPosition, yPosition, zPosition);
        }

        cylinders = new Cylinder[(shapeCount)];
        for (int i = 0; i < cylinders.length; i++) {
            cylinders[i] = new Cylinder(this);
            // float xPosition = random(-(width / 2f), width / 2f);
            // float yPosition = random(-(width / 2f), height / 2f);
            float xPosition = random(0, width);
            float yPosition = random(0, height);
            float zPosition = random(minZ, maxZ);
            cylinders[i].position = new PVector(xPosition, yPosition, zPosition);
        }

        // Setup environment
        cs = new CoordinateSystem(this);
        cs.position = new PVector(0,0,0);
        cs.dimension = CoordinateSystem.Dimension.D3D;
        cs.axisLength = max(width, height);
        cs.axisThickness = 25;
    }

    @Override
    public void draw() {

        setCamera();
        resizeHandler();

        if (!musicPaused) {
            analyzeMusicFrame(fft);
        }

        background(scoreLowBand / 20, scoreMiddleBand / 20, scoreHighBand / 20);

        drawCones();
        drawCylinders();

        if (explosionInProgress) {
            explosion();
        }

        if (showWorldCoordinateSystem) {
            cs.render();
        }

        if (keyPressed) {
            keyHold();
        }

        //System.out.printf("[Low: %f] [Middle: %f] [High: %f] - TOTAL: %f\n", scoreLowBand, scoreMiddleBand, scoreHighBand, scoreVolume);
    }

    private void setCamera() {
        eye.x = (width / 2.0f) + xTransformationDelta;
        eye.y = (height / 2.0f) + yTransformationDelta;
        eye.z = ((height / 2.0f) / tan(PI * 30.0f / 180.0f)) + zTransformationDelta;

        //// Rotation
        // parametric equation for sphere
        center.x = sin(radians(yRotation)) * cos(radians(xRotation)) + eye.x;
        center.y = sin(radians(xRotation))+ eye.y;
        center.z = -cos(radians(yRotation)) * cos(radians(xRotation)) + eye.z;

        switch (currentCameraMode) {
            case Perspective:
                perspective();
                camera(eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
                break;
            case Orthogonal:
                ortho(-width / 2f, width / 2f, -height / 2f, height / 2f, 0, 2000f);
                camera(eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
                break;
            case X:
                ortho(0, width, -height, 0, -2000, 2000);
                camera(eye.x, 0, 0, 0, 0, 0, 0, 1, 0);
                break;
            case Y:
                ortho(0, width, -height, 0, -2000, 2000);
                camera(0, eye.y, 0, 0, 0, 0, 1, 0, 0);
                break;

            case Z:
                ortho(0, width, -height, 0, -2000, 2000);
                camera(0, 0, eye.z, 0, 0, 0, 0, 1, 0);
                break;

            case Isometric: {
                ortho(0, width, -height, 0, -2000, 2000);
                float perAxis = (float) Math.sqrt (Math.pow(eye.z, 2) / 2);
                camera(perAxis, -perAxis, perAxis, 0, 0, 0, 0, 1, 0);
                break;
            }
            default:
                break;
        }


    }

    /**
     * Recalculates the positions if window resize affects programm
     */
    private void resizeHandler() {
        if (currentDisplayWidth != width || currentDisplayHeight != height) {
            int deltaX = (width - currentDisplayWidth) / 2;
            int deltaY = (height - currentDisplayHeight) / 2;

            for (CircularCone cone : cones) {
                cone.position.x += deltaX;
                cone.position.y += deltaY;
            }

            cs.axisLength = max(width, height);

            currentDisplayWidth = width;
            currentDisplayHeight = height;
        }
    }

    private void toggleMusic() {
        if (musicPaused) {
            musicPaused = false;
            player.play();
        } else {
            musicPaused = true;
            player.pause();
        }
    }

    /**
     * Recalculates the current amplitude per frequency band group
     *
     * @param fft Current instance of the FFT algorithm applied to the input to be analysed.
     */
    private void analyzeMusicFrame(FFT fft) {
        // Analyse the next music frame in the buffer
        fft.forward(player.mix);

        // Preserve previous values
        oldScoreLowBand = scoreLowBand;
        oldScoreMiddleBand = scoreMiddleBand;
        oldScoreHighBand = scoreHighBand;

        // Reinitialise variables for current values
        scoreLowBand = 0;
        scoreMiddleBand = 0;
        scoreHighBand = 0;

        // Calculation of the current amplitudes of the music frame
        for (int i = 0; i < fft.specSize() * spectrumLowBand; i++) {
            scoreLowBand += fft.getBand(i);
        }

        for (int i = (int) (fft.specSize() * spectrumLowBand); i < fft.specSize() * spectrumMiddleBand; i++) {
            scoreMiddleBand += fft.getBand(i);
        }

        for (int i = (int) (fft.specSize() * spectrumMiddleBand); i < fft.specSize() * spectrumHighBand; i++) {
            scoreHighBand += fft.getBand(i);
        }

        // Softening
        if (oldScoreLowBand > scoreLowBand) {
            scoreLowBand = oldScoreLowBand - scoreSofteningValue;
        }

        if (oldScoreMiddleBand > scoreMiddleBand) {
            scoreMiddleBand = oldScoreMiddleBand - scoreSofteningValue;
        }

        if (oldScoreHighBand > scoreHighBand) {
            scoreHighBand = oldScoreHighBand - scoreSofteningValue;
        }

        // Current amplitude of the total spectrum. Frequencies of higher bands were weighted more heavily.
        scoreVolume = 0.625f * scoreLowBand + 0.825f * scoreMiddleBand + 1 * scoreHighBand;
    }

    private void explosion() {
        if (!musicPaused) {
            toggleMusic();
        }

        explosionInProgress = true;

        for (CircularCone cone: cones) {
            PVector origin = new PVector(0,0,0);
            cone.position.add(PVector.add(origin, PVector.mult(cone.position.normalize(null), 10f)));
        }

        for (Cylinder cylinder : cylinders) {
            PVector origin = new PVector(0,0,0);
            cylinder.position.add(PVector.add(origin, PVector.mult(cylinder.position.normalize(null), 10f)));
        }

    }

    private void resetExplosion() {
        explosionInProgress = false;
        for (int i = 0; i < shapeCount; i++) {
            cones[i].position.set(random(0, width), random(0, height), random(minZ, maxZ));
            cylinders[i].position.set(random(0, width), random(0, height), random(minZ, maxZ));
        }
    }

    /**
     * Draws cones
     */
    private void drawCones() {
        for (int i = 0; i < cones.length; i++) {

            // float bandIntensity = fft.getBand(i);
            float bandIntensity = fft.getBand((int)map(i, 0, cones.length, 0, fft.specSize() * spectrumHighBand));

            float xRotation = random(0, 1);
            float yRotation = random(0, 1);
            float zRotation = random(0, 1);

            cones[i].bodyColor = color(scoreLowBand * 0.65f, scoreMiddleBand * 0.65f, scoreHighBand * 0.65f, bandIntensity * 5f);

            cones[i].radius = 50 + (bandIntensity / 2f);
            cones[i].height = 100 + (bandIntensity / 2f);

            cones[i].strokeColor = color(255, 155 - (25 * bandIntensity));

            cones[i].strokeWeight = 1 + (scoreVolume / 250);
            cones[i].rotationX += (bandIntensity * (xRotation / 1000)) / 50;
            cones[i].rotationY += (bandIntensity * (yRotation / 1000)) / 50;
            cones[i].rotationZ += (bandIntensity * (zRotation / 1000)) / 50;

            if (!musicPaused) {
                cones[i].position.z += (1 + (bandIntensity / 5)+ (pow((scoreVolume / 150), 2)));
            }

            // Reset z if maximum Z is exceeded
            if (cones[i].position.z >= maxZ) {
                cones[i].position.set(random(0, width), random(0, height), minZ);
            }

            cones[i].render();

            if (showModelCoordinateSystems && i % 2 == 0) {
                CoordinateSystem csModel = new CoordinateSystem(this);
                csModel.dimension = CoordinateSystem.Dimension.D3D;
                csModel.axisLength = 120;
                csModel.axisThickness = 5;
                csModel.position = cones[i].position;
                csModel.rotationX += cones[i].rotationX;
                csModel.rotationY += cones[i].rotationY;
                csModel.rotationZ += cones[i].rotationZ;
                csModel.render();
            }

            // System.out.printf("X: %f Y: %f Z: %f\n",  cones[i].position.x,  cones[i].position.y,  cones[i].position.z);
        }
    }

    private void drawCylinders() {
        for (int i = 0; i < cylinders.length; i++) {

            // float bandIntensity = fft.getBand(i);
            float bandIntensity = fft.getBand((int)map(i, 0, cones.length, 0, fft.specSize() * spectrumHighBand));

            float xRotation = random(0, 1);
            float yRotation = random(0, 1);
            float zRotation = random(0, 1);

            cylinders[i].bodyColor = color(scoreLowBand * 0.65f, scoreMiddleBand * 0.65f, scoreHighBand * 0.65f, bandIntensity * 5f);

            cylinders[i].radius = 50 + (bandIntensity / 2f);
            cylinders[i].height = 90 + (bandIntensity / 2f);

            cylinders[i].strokeColor = color(255, 155 - (25 * bandIntensity));

            cylinders[i].strokeWeight = 1 + (scoreVolume / 250);
            cylinders[i].rotationX += (bandIntensity * (xRotation / 1000)) / 10;
            cylinders[i].rotationY += (bandIntensity * (yRotation / 1000)) / 10;
            cylinders[i].rotationZ += (bandIntensity * (zRotation / 1000)) / 10;

            if (!musicPaused) {
                cylinders[i].position.z += (1 + (bandIntensity / 5)+ (pow((scoreVolume / 152), 2)));
            }

            // Reset z if maximum Z is exceeded
            if (cylinders[i].position.z >= maxZ) {
                cylinders[i].position.set(random(0, width), random(0, height), minZ);
            }

            cylinders[i].render();

            // Render Coordinate System
            if (showModelCoordinateSystems && i % 2 == 0) {
                CoordinateSystem csModel = new CoordinateSystem(this);
                csModel.dimension = CoordinateSystem.Dimension.D3D;
                csModel.axisLength = 120;
                csModel.axisThickness = 5;
                csModel.position = cylinders[i].position;
                csModel.rotationX += cylinders[i].rotationX;
                csModel.rotationY += cylinders[i].rotationY;
                csModel.rotationZ += cylinders[i].rotationZ;
                csModel.render();
            }

            // System.out.printf("X: %f Y: %f Z: %f\n",  cones[i].position.x,  cones[i].position.y,  cones[i].position.z);
        }
    }


    @Override
    public void mouseDragged(MouseEvent event) {
        super.mouseDragged(event);
        xRotation = constrain(xRotation + -(mouseY - pmouseY) * 0.05f, -89, 89);
        yRotation += -(mouseX-pmouseX) * 0.05f;
        setCamera();
    }

    private void keyHold() {
        //PMatrix3D matrix = new PMatrix3D();
        //PVector translationPath = new PVector();
        float xRotationDelta = 0;
        float yRotationDelta = 0;

        if (keyCode == PConstants.UP) {
            xRotationDelta -= 90;
        } else if (keyCode == PConstants.DOWN) {
            xRotationDelta += 90;
        } else if (keyCode == PConstants.LEFT) {
            yRotationDelta -= 90;
        } else if (keyCode == PConstants.RIGHT) {
            yRotationDelta += 90;
        } else if (key == '+') {
            xRotationDelta = 0;
        } else if (key == '-') {
            xRotationDelta += 180;
        } else {
            return;
        }

        float xRotationAltered = xRotation + xRotationDelta;
        float yRotationAltered = yRotation + yRotationDelta;

        xTransformationDelta += sin(radians(yRotationAltered)) * cos(radians(xRotationAltered)) * 5f;
        yTransformationDelta += sin(radians(xRotationAltered)) * 5f;
        zTransformationDelta += -cos(radians(yRotationAltered)) * cos(radians(xRotationAltered)) * 5f;
    }

    @Override
    public void keyPressed() {
        super.keyPressed();

        if (key == 'm') {
            showModelCoordinateSystems = !showModelCoordinateSystems;
        } else if (key == 'c') {
            showWorldCoordinateSystem = !showWorldCoordinateSystem;
        } else if (key == 'p') {
            toggleMusic();
        } else if (key == 'e') {
            if (explosionInProgress) {
                resetExplosion();
            } else {
                explosion();
            }
        } else if (key == '1') {
            currentCameraMode = CameraMode.Perspective;
        } else if (key == '2') {
            currentCameraMode = CameraMode.Orthogonal;
        } else if (key == '3') {
            currentCameraMode = CameraMode.X;
        } else if (key == '4') {
            currentCameraMode = CameraMode.Y;
        } else if (key == '5') {
            currentCameraMode = CameraMode.Z;
        } else if (key == '6') {
            currentCameraMode = CameraMode.Isometric;
        }
    }

    public enum CameraMode { Perspective, Orthogonal, X, Y, Z, Isometric }


}
