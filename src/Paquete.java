public class Paquete {

    // Atributos de la clase
    private final int idPaquete;
    private TipoPaquete tipoPaquete;
    private double peso;
    private double valorDeclarado;


    // Constructor de la clase
    public Paquete(TipoPaquete tipoPaquete, double peso, double valorDeclarado) {
        this.idPaquete = generarIdPaquete();
        this.tipoPaquete = tipoPaquete;
        this.peso = peso;
        this.valorDeclarado = valorDeclarado;
    }

    // Métodos de la clase
    private int generarIdPaquete() {
        return (int) (Math.random() * 1000000);
    }

    public int getIdPaquete() {
        return idPaquete;
    }

    public TipoPaquete getTipoPaquete() {
        return tipoPaquete;
    }

    public void setTipoPaquete(TipoPaquete tipoPaquete) {
        this.tipoPaquete = tipoPaquete;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public void setValorDeclarado(double valorDeclarado) {
        this.valorDeclarado = valorDeclarado;
    }

}
