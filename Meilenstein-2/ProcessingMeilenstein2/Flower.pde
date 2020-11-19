import processing.sound.*;

class Flower {
  //Flower attributes
  public FlowerMode flowerMode = FlowerMode.STATIC;
  private int leafs = 8;
  private float stepAngle = TWO_PI / leafs;
  public color midpointColor;

  //Leaf attributes
  public float leafApertureFactor = 300;
  public float leafLength = 100;

  //Breath animation mode attributes
  public float breathDuration = 400;
  public float breathWeaknessFactor = 1200;
  private float breathCurrentTimeStep = 0;

  //Music animation mode attributes
  public PApplet parentApplet;
  private String soundFileName = "PogoWeightlessWatchingYou.wav";
  private FFT fft;
  private SoundFile soundFile;
  private float[]scoresBass = new float[5];
  private float[]scoresTreble = new float[5];
  private float scoreBass = 0;
  private float scoreTreble = 0;

  //Rotation animation mode attributes
  private float rotationAnimation;

  //Render attributes
  public float positionX = height / 2;
  public float positionY = width / 2;
  public float scaleFactor = min(height, width) / 1000f * 2f;
  public float rotation = 0;
  public color fillColor = color(40, 40, 110, 110);


  public int getLeafs() {
    return this.leafs;
  }

  public void setLeafs(int leafs) {
    this.leafs = leafs;
    stepAngle = TWO_PI / leafs;
  }

  public Flower(PApplet parentApplet) {
    //Parent applet as music playback endpoint
    this.parentApplet = parentApplet;

    //Preload sound file
    soundFile = new SoundFile(parentApplet, soundFileName);
    //Lowering volume with amplifi factor
    soundFile.amp(-0.8);
    //Amplitude analyzer
    fft = new FFT(parentApplet, 8);
    fft.input(soundFile);
  }

  public Flower(PApplet parentApplet, String soundFileName) {
    //Parent applet as music playback endpoint
    this.parentApplet = parentApplet;

    //Preload sound file
    soundFile = new SoundFile(parentApplet, soundFileName);
    //Lowering volume with amplifi factor
    soundFile.amp(-0.8);
    //Amplitude analyzer
    fft = new FFT(parentApplet, 8);
    fft.input(soundFile);
  }

  public void render() {

    //Generate: a leaf instance
    Leaf l = new Leaf();
    l.apertureFactor = this.leafApertureFactor;
    l.leafLength = this.leafLength;

    //Leaf: Consider the breath effect


    //Leaf: Render options for leafes
    noStroke();
    fill(fillColor);

    //Flower: Transform flower
    pushMatrix();
    translate(positionX, positionY);
    scale(scaleFactor);

    //Leaf: Consider animation mode
    if (flowerMode == FlowerMode.BREATHING) {
      //Manipulates the aperture factor for breathing - overwrites default values directly inside the leaf to preserve the original factor
      l.apertureFactor += breathApertureDelta();
      //automatic rotation
      rotate(rotationAnimation);
      rotationAnimation += radians(0.01);
      //input roation
      rotate(rotation);
    } else if (flowerMode == FlowerMode.MUSIC) {
      //start music - if not started
      playMusic();
      //animate leafes in sync with music
      float[] bassTrebleAmplitude = musicAperatureFactor();
      l.apertureFactor = l.apertureFactor * bassTrebleAmplitude[0];
      l.leafLength = l.leafLength * bassTrebleAmplitude[1];
      //rotate in sync with music
      rotate(rotationAnimation);
      rotationAnimation += radians(bassTrebleAmplitude[2] * 5);
    } else if (flowerMode == FlowerMode.ROTATE) {
      rotate(rotationAnimation);
      rotationAnimation += radians(0.25);
    } else {
      //Resets the rotation for other modes
      rotate(rotation);
      //Resets the time steps for breath if mode was changed during runtime to reset progress
      breathCurrentTimeStep = 0;
      //Pause music if was playing
      pauseMusic();
    }

    //Leaf: Render leafes
    for (int i = 0; i < leafs; i++) {
      pushMatrix();
      //Add 90 Degree offset of the start and the following leafes - first leaf should be facing down
      rotate(i * stepAngle + radians(90));
      l.render();
      popMatrix();
    }


    //Circle: Draw white circle in the middle
    fill(midpointColor);
    noStroke();
    circle(0, 0, l.leafLength / 2.2);
    popMatrix();
  }


  private float breathApertureDelta() {
    float breathStep = leafApertureFactor / breathWeaknessFactor;
    float timeFactor = breathCurrentTimeStep > breathDuration / 2f ? abs(breathCurrentTimeStep - breathDuration) : breathCurrentTimeStep;
    float breathDelta = breathStep * timeFactor;
    breathCurrentTimeStep = breathCurrentTimeStep >= breathDuration 
      ? 0 : breathCurrentTimeStep >= (breathDuration - (breathDuration / 20f)) 
      ? breathCurrentTimeStep + 0.5f 
      : breathCurrentTimeStep <= (0f + (breathDuration / 20f)) 
      ? breathCurrentTimeStep + 0.5f 
      : breathCurrentTimeStep + 1f;
    //System.out.println("breathStep: " + breathStep + " - timeFactor" + timeFactor + " - breathCurrentTimeStep:" + breathCurrentTimeStep + " - breathDuration:" + breathDuration + " - breathDelta: " + breathDelta);
    return breathDelta;
  }

  private float[] musicAperatureFactor() {

    for (int i = 1; i < scoresBass.length; i++) {
      scoresBass[i-1] = scoresBass[i];
    }

    scoresBass[scoresBass.length-1] = (fft.analyze()[0] + fft.analyze()[1] + fft.analyze()[2] + fft.analyze()[3]) / 4f;


    scoreBass = 0;

    for (float element : scoresBass) {
      scoreBass += element;
    }

    scoreBass /= (float)scoresBass.length;

    for (int i = 1; i < scoresTreble.length; i++) {
      scoresTreble[i-1] = scoresTreble[i];
    }

    scoresTreble[scoresTreble.length-1] = (fft.analyze()[4] + fft.analyze()[5] + fft.analyze()[6] + fft.analyze()[7]) / 4f;

    scoreTreble = 0;

    for (float element : scoresTreble) {
      scoreTreble += element;
    }

    scoreTreble /= (float)scoresTreble.length;

    //oldBass = scoreBass;
    //oldTreble = scoreTreble;


    //scoreTreble = 0;

    //scoreBass += (fft.analyze()[0] + fft.analyze()[1] + fft.analyze()[2] + fft.analyze()[3]) / 4f;
    //scoreTreble += (fft.analyze()[4] + fft.analyze()[5] + fft.analyze()[6] + fft.analyze()[7]) / 4f;


    //if (oldBass > scoreBass && scoreBass > 0.025) {
    //  scoreBass = oldBass - 0.025;
    //}

    //if (oldTreble > scoreTreble && scoreTreble > 0.0025) {
    //  scoreTreble = oldTreble - 0.0025;
    //}

    float normalizedBass = -map(scoreBass, 0, 1, 1.1, 0.7);
    float normalizedTreble = map(scoreTreble, 0, 1, 1, 2.5);

    float amplitude = (fft.analyze()[0] + fft.analyze()[1] + fft.analyze()[2] + fft.analyze()[3] 
      + fft.analyze()[4] + fft.analyze()[5] + fft.analyze()[6] + fft.analyze()[7]) / 8f;

    float[] bt = { normalizedBass, normalizedTreble, amplitude};

    return bt;
  }

  private void playMusic() {
    if (!soundFile.isPlaying()) {
      soundFile.play();
    }
  }

  private void pauseMusic() {
    if (soundFile.isPlaying()) {
      soundFile.pause();
    }
  }
}


enum FlowerMode {
  STATIC, BREATHING, ROTATE, MUSIC;
  private static FlowerMode[] vals = values();
  public FlowerMode next()
  {
    return vals[(this.ordinal()+1) % vals.length];
  }
}
