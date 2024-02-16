import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {
    JPanel main;
    private JTextField txtNombre;
    private JTextField txtSalario;
    private JTextField txtCelular;
    private JTextField txtId;
    private JButton btnGuardar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JTable table1;
    private DefaultTableModel tableModel;

    private ConexionBD conexionBD;

    public Principal() {
        conexionBD = new ConexionBD("jdbc:mysql://localhost:3306/compania", "root", "f123456");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Salario");
        tableModel.addColumn("Celular");
        table1.setModel(tableModel);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
                cargarDatos();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPorId();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDatos();
                cargarDatos();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDatos();
                cargarDatos();
            }
        });
    }

    private void guardarDatos() {
        String nombre = txtNombre.getText();
        int salario = Integer.parseInt(txtSalario.getText());
        int celular = Integer.parseInt(txtCelular.getText());

        try {
            conexionBD.insertarDatos(nombre, salario, celular);
            JOptionPane.showMessageDialog(null, "Datos guardados con éxito");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void buscarPorId() {
        try {
            int id = Integer.parseInt(txtId.getText());
            ResultSet resultSet = conexionBD.buscarPorId(id);

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int salario = resultSet.getInt("salario");
                int celular = resultSet.getInt("celular");

                txtNombre.setText(nombre);
                txtSalario.setText(String.valueOf(salario));
                txtCelular.setText(String.valueOf(celular));

                JOptionPane.showMessageDialog(null, "ID encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "ID no encontrado");
            }
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void eliminarDatos() {
        try {
            int id = Integer.parseInt(txtId.getText());
            conexionBD.eliminarDatos(id);
            JOptionPane.showMessageDialog(null, "Datos eliminados con éxito");
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void actualizarDatos() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        int salario = Integer.parseInt(txtSalario.getText());
        int celular = Integer.parseInt(txtCelular.getText());

        try {
            conexionBD.actualizarDatos(id, nombre, salario, celular);
            JOptionPane.showMessageDialog(null, "Datos actualizados con éxito");
        } catch (NumberFormatException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cargarDatos() {
        try {
            tableModel.setRowCount(0);
            ResultSet resultSet = conexionBD.obtenerDatos();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int salario = resultSet.getInt("salario");
                int celular = resultSet.getInt("celular");
                tableModel.addRow(new Object[]{id, nombre, salario, celular});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}

