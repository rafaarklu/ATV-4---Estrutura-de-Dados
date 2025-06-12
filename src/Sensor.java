public class Sensor {
    private String id;
    private String data;
    private double valorAQI;

    public Sensor(String id, String data, double valorAQI) {
        this.id = id;
        this.data = data;
        this.valorAQI = valorAQI;
    }

    public double getValorAQI() {
        return valorAQI;
    }
}
