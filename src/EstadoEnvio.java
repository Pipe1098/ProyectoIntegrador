public enum EstadoEnvio {

    RECIBIDO(1),
    EN_RUTA(2),
    ENTREGADO(3);

    private int valor;

    EstadoEnvio(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
