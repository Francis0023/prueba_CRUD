import java.sql.*;

public class ConexionBD {
    private String servidor;
    private String usuario;
    private String contrasenia;

    public ConexionBD(String servidor, String usuario, String contrasenia) {
        this.servidor = servidor;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(servidor, usuario, contrasenia);
    }

    public ResultSet obtenerDatos() throws SQLException {
        try (Connection connection = conectar();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM empleados")) {
            return resultSet;
        }
    }

    public ResultSet buscarPorId(int id) throws SQLException {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM empleados WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();
        }
    }

    public void insertarDatos(String nombre, int salario, int celular) throws SQLException {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO empleados(nombre, salario, celular) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, salario);
            preparedStatement.setInt(3, celular);
            preparedStatement.executeUpdate();
        }
    }

    public void actualizarDatos(int id, String nombre, int salario, int celular) throws SQLException {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE empleados SET nombre = ?, salario = ?, celular = ? WHERE id = ?")) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, salario);
            preparedStatement.setInt(3, celular);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
    }

    public void eliminarDatos(int id) throws SQLException {
        try (Connection connection = conectar();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM empleados WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
