package weatherbot.model;


public class Fact {
    private double temp;
    private double feels_like;
    private double winds_speed;
    private double pressure_mm;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public double getWinds_speed() {
        return winds_speed;
    }

    public void setWinds_speed(double winds_speed) {
        this.winds_speed = winds_speed;
    }

    public double getPressure_mm() {
        return pressure_mm;
    }

    public void setPressure_mm(double pressure_mm) {
        this.pressure_mm = pressure_mm;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "temp=" + temp +
                ", feels_like=" + feels_like +
                ", winds_speed=" + winds_speed +
                ", pressure_mm=" + pressure_mm +
                '}';
    }
}
