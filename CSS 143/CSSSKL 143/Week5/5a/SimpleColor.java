package lab5;

//TODO: if you use this class because you dont have your
//previous SimpleColor, you must document the code with comments
//and fix the setters so they don't invalidate the [0-255] rule
public class SimpleColor {

    private int r;
    private int g;
    private int b;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        if (r >= 0 && r <= 255) {
            this.r = r;
        } else {
            throw new ColorException();
        }
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        if (g >= 0 && g <= 255) {
            this.g = g;
        } else {
            throw new ColorException();
        }
    }

    public int getB() {

        return b;
    }

    public void setB(int b) {
        if (b >= 0 && b <= 255) {
            this.b = b;
        } else {
            throw new ColorException();
        }
    }

    public void setColor(int a, int b, int c) {
        setR(a);
        setG(b);
        setB(c);
    }

    public SimpleColor() {
    }

    public SimpleColor(int r, int g, int b) {
        setR(r);
        setG(g);
        setB(b);
    }

    public SimpleColor(SimpleColor b) {
        this(b.r, b.g, b.b);
    }

}
