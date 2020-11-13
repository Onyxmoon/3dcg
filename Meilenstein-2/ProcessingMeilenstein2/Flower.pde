class Flower {
  //Flower attributes
  private int leafs = 8;
  private float stepAngle = TWO_PI / leafs;
  public boolean breathe = false;

  //Leaf attributes
  public float leafApertureFactor = 300;
  public float leafLength = 100;

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
    //Generate a leaf instance
    Leaf l = new Leaf();
    l.apertureFactor = this.leafApertureFactor;
    l.length = this.leafLength;
    
    //To-Do
    //Consider the breath effect
    if(breathe) {
      this.leafApertureFactor += easeInOutElastic(sin(second()));
    }
    
    //Render leafes
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
  }

  /**
   * @param  x  absolute progress of the animation in the bounds of 0 (beginning of the animation) and 1 (end of animation)
   * @return breath progress (between 0 and 1)
   * @author https://easings.net/en#easeInOutElastic
   */
  private float easeInOutElastic(float x) {
    float c5 = (2 * PI) / 4.5;

    return x == 0 ? 0 
      : x == 1 
      ? 1 
      : x < 0.5 
      ? -(pow(2, 20 * x - 10) * sin((20 * x - 11.125) * c5)) / 2 
      : (pow(2, -20 * x + 10) * sin((20 * x - 11.125) * c5)) / 2 + 1;
  }
}
