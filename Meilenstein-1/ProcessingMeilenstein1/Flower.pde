class Flower {
  //Flower attributes
  private int leafs = 8;
  private float stepAngle = TWO_PI / leafs;

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
    Leaf l = new Leaf();
    l.apertureFactor = this.leafApertureFactor;
    l.length = this.leafLength;
    for (int i = 0; i < leafs; i++) {
      pushMatrix();
      rotate(i * stepAngle);
      l.render();
      popMatrix();
    }
    fill(255);
    noStroke();
    circle(0, 0, leafLength / 2.2);
  }
}
