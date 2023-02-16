public enum TipoEmpleado {

    CONDUCTOR (1),
    REPARTIDOR (2),
    COORDINADOR(3);

    private int valor;

    TipoEmpleado(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    }

