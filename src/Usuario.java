public abstract class Usuario {

        // Atributos de la clase
        protected int cedula;
        protected String nombre;
        protected String apellidos;
        protected String celular;
        protected String correoElectronico;
        protected String direccionResidencia;
        protected String ciudad;

        // Constructor de la clase
        public Usuario(int cedula, String nombre, String apellidos, String celular, String correoElectronico, String direccionResidencia, String ciudad) {
            this.cedula = cedula;
            this.nombre = nombre;
            this.apellidos = apellidos;
            this.celular = celular;
            this.correoElectronico = correoElectronico;
            this.direccionResidencia = direccionResidencia;
            this.ciudad = ciudad;
        }

        // Métodos de la clase
        public int getCedula() {
            return cedula;
        }

        public void setCedula(int cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellidos() {
            return apellidos;
        }

        public void setApellidos(String apellidos) {
            this.apellidos = apellidos;
        }

        public String getCelular() {
            return celular;
        }

        public void setCelular(String celular) {
            this.celular = celular;
        }

        public String getCorreoElectronico() {
            return correoElectronico;
        }

        public void setCorreoElectronico(String correoElectronico) {
            this.correoElectronico = correoElectronico;
        }

        public String getDireccionResidencia() {
            return direccionResidencia;
        }

        public void setDireccionResidencia(String direccionResidencia) {
            this.direccionResidencia = direccionResidencia;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }
    }


