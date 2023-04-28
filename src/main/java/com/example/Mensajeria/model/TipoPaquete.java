package com.example.Mensajeria.model;

public enum TipoPaquete {

        LIVIANO(1),
        MEDIANO(2),
        GRANDE(3);

        private int valor;

        TipoPaquete(int valor) {
            this.valor = valor;
        }
}
