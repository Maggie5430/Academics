package lab5;

/**
 * CSS 143 B, Winter 2018 Lab5
 *
 * @author Margaret Connor
 * @version 1.0
 */
public class ColorWithAlpha extends SimpleColor {

    private int alpha;

    public ColorWithAlpha(int newAlpha) {
        if (newAlpha >= 0 && newAlpha <= 255) {
            this.alpha = newAlpha;
        } else {
            throw new ColorException();
        }
    }

    public ColorWithAlpha(int newRed, int newGreen, int newBlue, int newAlpha) {
        super(newRed, newGreen, newBlue);
        if (newAlpha >= 0 && newAlpha <= 255) {
            this.alpha = newAlpha;
        } else {
            throw new ColorException();
        }
    }

    public ColorWithAlpha(ColorWithAlpha newColorWithAlpha) {
        super(newColorWithAlpha.getR(), newColorWithAlpha.getB(), newColorWithAlpha.getG());
        this.alpha = (newColorWithAlpha.getAlpha());
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setAlpha(int newAlpha) {
        if (newAlpha >= 0 && newAlpha <= 255) {
            this.alpha = newAlpha;
        } else {
            throw new ColorException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", alpha:" + alpha;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ColorWithAlpha)) {
            return false;
        } else {
            ColorWithAlpha newObject = (ColorWithAlpha) o;
            return newObject.getAlpha() == this.alpha 
                    && newObject.getR() == this.getR() 
                    && newObject.getB() == this.getB() 
                    && newObject.getG() == this.getG();
        }
    }

}
