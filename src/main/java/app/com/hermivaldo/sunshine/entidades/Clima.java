package app.com.hermivaldo.sunshine.entidades;

/**
 * @author  Hermivaldo Braga on 28/09/2015.
 * @version 0.1
 * Objeto para criaçao de um molde para salvar os objetos
 */
public class Clima {

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    private float max;
    private float min;
    private String main;
    private String description;

}
