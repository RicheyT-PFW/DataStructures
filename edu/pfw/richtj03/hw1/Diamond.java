package hw1;
import java.util.Arrays;
public class Diamond implements Cloneable, Comparable<Diamond> {

    private String stockNumber;
    private Double size;
    private String clarityGrade;
    private Character colorGrade;
    private String cut;

    // Clarity ranking array for compareTo
    private static final String[] CLARITYS = {
        "FL", "IF", "VVS1", "VVS2", "VS1", "VS2", "SI1", "SI2", "I1", "I2", "I3"
    };

    public Diamond(String stockNumber, Double size, String clarityGrade, Character colorGrade, String cut) {
        if (stockNumber == null) {
            throw new IllegalArgumentException("Stock Number cannot be null.");
        } else {
            this.stockNumber = stockNumber;
        }

        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null.");
        } else {
            this.size = size;
        }

        if (clarityGrade == null) {
            throw new IllegalArgumentException("Clarity grade cannot be null.");
        } else {
            this.clarityGrade = clarityGrade;
        }

        if (colorGrade == null) {
            throw new IllegalArgumentException("Color grade cannot be null.");
        } else {
            this.colorGrade = colorGrade;
        }

        if (cut == null) {
            throw new IllegalArgumentException("Cut cannot be null.");
        } else {
            this.cut = cut;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Diamond ");
        sb.append(this.hashCode());
        sb.append("\n------------------------------------");
        sb.append("\nStock Number:\t");
        sb.append(this.stockNumber);
        sb.append("\nSize:\t");
        sb.append(this.size);
        sb.append("\nClarity Grade:\t");
        sb.append(this.clarityGrade);
        sb.append("\nColor Grade:\t");
        sb.append(this.colorGrade);
        sb.append("\nCut:\t");
        sb.append(this.cut);
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public Diamond clone() throws CloneNotSupportedException {
        return (Diamond) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o == null) {
            return result;
        }

        if (!(o instanceof Diamond)) {
            return result;
        }

        if(this == o) {
            return true;
        }

        Diamond d = (Diamond) o;

        if (!this.clarityGrade.equals(d.clarityGrade)) {
            return result;
        }

        if (!this.colorGrade.equals(d.colorGrade)) {
            return result;
        }

        if (!this.cut.equals(d.cut)) {
            return result;
        }

        if (!this.size.equals(d.size)) {
            return result;
        }

        if (!this.stockNumber.equals(d.stockNumber)) {
            return result;
        }
        result = true;
        return result;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
                this.stockNumber,
                this.colorGrade,
                this.clarityGrade,
                this.size,
                this.cut
        );
    }

    @Override
    public int compareTo(Diamond d) {
        if (d == null) {
            throw new NullPointerException("D cannot be null");
        }

        if (this == d) {
            return 0;
        }

        // Compare size (descending)
        int cmp = d.size.compareTo(this.size);
        if (cmp != 0) return cmp;

        // Compare clarity based on ranking
        int thisRank = Arrays.asList(CLARITYS).indexOf(this.clarityGrade);
        int otherRank = Arrays.asList(CLARITYS).indexOf(d.clarityGrade);
        cmp = Integer.compare(thisRank, otherRank);
        if (cmp != 0) return cmp;

        // Compare color 
        return this.colorGrade.compareTo(d.colorGrade);

    }

    // Getters
    public String getStockNumber() { return stockNumber; }
    public Double getSize() { return size; }
    public String getClarityGrade() { return clarityGrade; }
    public Character getColorGrade() { return colorGrade; }
    public String getCut() { return cut; }
}
