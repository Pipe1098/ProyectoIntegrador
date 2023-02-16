public class Empleado extends Usuario {

        // Atributos de la clase
        private int antiguedad;
        private String rh;
        private TipoEmpleado tipoEmpleado;

        // Constructor de la clase
        public Empleado(int cedula, String nombre, String apellidos, String celular, String correoElectronico, String direccionResidencia, String ciudad, int antiguedad, String rh, TipoEmpleado tipoEmpleado) {
            super(cedula, nombre, apellidos, celular, correoElectronico, direccionResidencia, ciudad);
            this.antiguedad = antiguedad;
            this.rh = rh;
            this.tipoEmpleado = tipoEmpleado;
        }

        // Métodos de la clase
        public int getAntiguedad() {
            return antiguedad;
        }

        public void setAntiguedad(int antiguedad) {
            this.antiguedad = antiguedad;
        }

        public String getRh() {
            return rh;
        }

        public void setRh(String rh) {
            this.rh = rh;
        }

        public TipoEmpleado getTipoEmpleado() {
            return tipoEmpleado;
        }

        public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
            this.tipoEmpleado = tipoEmpleado;
        }

}
