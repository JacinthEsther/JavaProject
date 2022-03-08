package africa.semicolon.sendAm.data.model;

public class PackageDescription {
    private String name;
    private double weightInGrammes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightInGrammes() {
        return weightInGrammes;
    }

    public void setWeightInGrammes(double weightInGrammes) {
        this.weightInGrammes = weightInGrammes;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PackageDescription{");
        sb.append("name='").append(name).append('\'');
        sb.append(", weightInGrammes=").append(weightInGrammes);
        sb.append('}');
        return sb.toString();
    }
}
