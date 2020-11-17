import processing.sound.*;

class Flower {
  //Flower attributes
  public FlowerMode flowerMode = FlowerMode.STATIC;
  private int leafs = 8;
  private float stepAngle = TWO_PI / leafs;

  //Leaf attributes
  public float leafApertureFactor = 300;
  public float leafLength = 100;

  //Breath animation mode attributes
  public float breathDuration = 400;
  public float breathWeaknessFactor = 1200;
  private float breathCurrentTimeStep = 0;

  //Music animation mode attributes


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

  public Flower() {
    playAudio();
  }

  public void render() {

    //Generate: a leaf instance
    Leaf l = new Leaf();
    l.apertureFactor = this.leafApertureFactor;
    l.leafLength = this.leafLength;

    //Leaf: Consider the breath effect
    if (flowerMode == FlowerMode.BREATHING) {
      //Manipulates the aperture factor for breathing - overwrites default values directly inside the leaf to preserve the original factor
      l.apertureFactor += breathApertureDelta();
    } else {
      //Resets the time steps for breath if mode was changed during runtime to reset progress
      breathCurrentTimeStep = 0;
    }

    //Leaf: Consider the music mode
    {
      
    }

    //Leaf: Render options for leafes
    noStroke();
    fill(fillColor);

    //Flower: Transform flower
    pushMatrix();
    translate(positionX, positionY);
    scale(scaleFactor);
    if (flowerMode == FlowerMode.ROTATE) {
      rotate(rotationAnimation);
      rotationAnimation += radians(0.25);
    } else {
      rotate(rotation);
    }

    //Leaf: Render leafes
    for (int i = 0; i < leafs; i++) {
      pushMatrix();
      rotate(i * stepAngle);
      l.render();
      popMatrix();
    }


    //Circle: Draw white circle in the middle
    fill(255);
    noStroke();
    circle(0, 0, leafLength / 2.2);
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

  private void playAudio() {
     SoundFile file = new SoundFile(ProcessingMeilenstein2.this, dataPath("pogo.mp3"));
     file.play();
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
