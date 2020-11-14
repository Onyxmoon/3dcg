class Flower {
  //Flower attributes
  private int leafs = 8;
  private float stepAngle = TWO_PI / leafs;

  //Leaf attributes
  public float leafApertureFactor = 300;
  public float leafLength = 100;

  //Breath attributes
  public boolean breath = false;
  public float breathDuration = 400;
  public float breathWeaknessFactor = 1200;
  private float breathCurrentTimeStep = 0;

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
  }

  public void render() {
    //Consider the breath effect
    if (breath) {
      this.leafApertureFactor += breathApertureDelta();
    }

    //Generate a leaf instance
    Leaf l = new Leaf();
    l.apertureFactor = this.leafApertureFactor;
    l.leafLength = this.leafLength;

    //Render options for leafes
    noStroke();
    fill(fillColor);

    //Render leafes
    pushMatrix();
    translate(positionX, positionY);
    scale(scaleFactor);
    rotate(rotation);
    for (int i = 0; i < leafs; i++) {
      pushMatrix();
      rotate(i * stepAngle);
      l.render();
      popMatrix();
    }
    //Draw white circle in the middle
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
}
