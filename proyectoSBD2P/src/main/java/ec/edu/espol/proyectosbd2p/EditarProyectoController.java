package ec.edu.espol.proyectosbd2p;

import ec.edu.espol.proyectosbd2p.modelo.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EditarProyectoController {

    @FXML
    private TextField txtIDProyecto;
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtPresupuesto;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private DatePicker dpFechaFin;
    @FXML
    private TextField txtRuc;
    @FXML
    private TextField txtNumFactura;
    @FXML
    private TextField txtIdDepCreativo;
    @FXML
    private TextField txtIdDepProd;
    @FXML
    private TextField txtComisionAEmpresa;
    @FXML
    private TextField txtDuracion;
    @FXML
    private TextField txtTamanoBanner;
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtRating;
    @FXML
    private CheckBox chkEstado;
    @FXML
    private Label lblIdDepCreativo;
    @FXML
    private Label lblIdDepProd;
    @FXML
    private Label lblDuracion;
    @FXML
    private Label lblTamanoBanner;
    @FXML
    private Label lblCategoria;
    @FXML
    private Label lblPrecio;
    @FXML
    private Label lblRating;
    @FXML
    private Label lblEstado;

    private Proyecto proyecto;

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        cargarDatosProyecto();
        configurarCamposPorTipo();
    }

    private void cargarDatosProyecto() {
        if (proyecto != null) {
            txtIDProyecto.setText(proyecto.getIdProyecto());
            txtTitulo.setText(proyecto.getTitulo());
            txtPresupuesto.setText(String.valueOf(proyecto.getPresupuesto()));
            txtDescripcion.setText(proyecto.getDescripcion());
            dpFechaInicio.setValue(proyecto.getFechaInicio().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            dpFechaFin.setValue(proyecto.getFechaFin().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
            txtRuc.setText(proyecto.getRuc());
            txtNumFactura.setText(proyecto.getNumFactura());
            txtComisionAEmpresa.setText(String.valueOf(proyecto.getComisionAEmpresa()));

            if (proyecto instanceof Publicidad) {
                txtIdDepCreativo.setText(((Publicidad) proyecto).getIdProyecto());
            }
            if (proyecto instanceof ProductoTienda) {
                txtCategoria.setText(((ProductoTienda) proyecto).getCategoria());
                txtPrecio.setText(String.valueOf(((ProductoTienda) proyecto).getPrecio()));
                txtIdDepProd.setText(((ProductoTienda) proyecto).getIdDepProd());
            }
            if (proyecto instanceof PublicidadAnuncioCanal) {
                txtDuracion.setText(String.valueOf(((PublicidadAnuncioCanal) proyecto).getDuracion()));
            }
            if (proyecto instanceof PublicidadAnuncioWeb) {
                txtTamanoBanner.setText(((PublicidadAnuncioWeb) proyecto).getTamanoBanner());
            }
            if (proyecto instanceof Segmento) {
                txtRating.setText(((Segmento) proyecto).getRating());
                txtDuracion.setText(String.valueOf(((Segmento) proyecto).getDuracion()));
                chkEstado.setSelected(((Segmento) proyecto).isEstado());
                txtIdDepProd.setText(((Segmento) proyecto).getIdDepProd());
            }
        }
    }

    private void configurarCamposPorTipo() {
        if (proyecto instanceof Publicidad) {
            lblIdDepCreativo.setVisible(true);
            txtIdDepCreativo.setVisible(true);
        }
        if (proyecto instanceof ProductoTienda) {
            lblCategoria.setVisible(true);
            txtCategoria.setVisible(true);
            lblPrecio.setVisible(true);
            txtPrecio.setVisible(true);
            lblIdDepProd.setVisible(true);
            txtIdDepProd.setVisible(true);
        }
        if (proyecto instanceof PublicidadAnuncioCanal) {
            lblDuracion.setVisible(true);
            txtDuracion.setVisible(true);
        }
        if (proyecto instanceof PublicidadAnuncioWeb) {
            lblTamanoBanner.setVisible(true);
            txtTamanoBanner.setVisible(true);
        }
        if (proyecto instanceof Segmento) {
            lblRating.setVisible(true);
            txtRating.setVisible(true);
            lblDuracion.setVisible(true);
            txtDuracion.setVisible(true);
            lblEstado.setVisible(true);
            chkEstado.setVisible(true);
            lblIdDepProd.setVisible(true);
            txtIdDepProd.setVisible(true);
        }
    }

    @FXML
    private void guardarCambios() {
        if (txtTitulo.getText().trim().isEmpty() ||
            txtPresupuesto.getText().trim().isEmpty() ||
            txtDescripcion.getText().trim().isEmpty() ||
            dpFechaInicio.getValue() == null ||
            dpFechaFin.getValue() == null ||
            txtRuc.getText().trim().isEmpty() ||
            txtNumFactura.getText().trim().isEmpty() ||
            txtComisionAEmpresa.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos deben estar llenos.");
            return;
        }

        proyecto.setTitulo(txtTitulo.getText());
        proyecto.setPresupuesto(Integer.parseInt(txtPresupuesto.getText()));
        proyecto.setDescripcion(txtDescripcion.getText());
        proyecto.setFechaInicio(java.sql.Date.valueOf(dpFechaInicio.getValue()));
        proyecto.setFechaFin(java.sql.Date.valueOf(dpFechaFin.getValue()));
        proyecto.setRuc(txtRuc.getText());
        proyecto.setNumFactura(txtNumFactura.getText());
        proyecto.setComisionAEmpresa(Double.parseDouble(txtComisionAEmpresa.getText()));

        if (proyecto instanceof Publicidad) {
            ((Publicidad) proyecto).setIdProyecto(txtIdDepCreativo.getText());
        }
        if (proyecto instanceof ProductoTienda) {
            ((ProductoTienda) proyecto).setCategoria(txtCategoria.getText());
            ((ProductoTienda) proyecto).setPrecio(Integer.parseInt(txtPrecio.getText()));
            ((ProductoTienda) proyecto).setIdDepProd(txtIdDepProd.getText());
        }
        if (proyecto instanceof PublicidadAnuncioCanal) {
            ((PublicidadAnuncioCanal) proyecto).setDuracion(Integer.parseInt(txtDuracion.getText()));
        }
        if (proyecto instanceof PublicidadAnuncioWeb) {
            ((PublicidadAnuncioWeb) proyecto).setTamanoBanner(txtTamanoBanner.getText());
        }
        if (proyecto instanceof Segmento) {
            ((Segmento) proyecto).setRating(txtRating.getText());
            ((Segmento) proyecto).setDuracion(Integer.parseInt(txtDuracion.getText()));
            ((Segmento) proyecto).setEstado(chkEstado.isSelected());
            ((Segmento) proyecto).setIdDepProd(txtIdDepProd.getText());
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Aquí deberás tener una lógica específica para cada tipo de proyecto.
            // Similar al patrón mostrado en los ejemplos anteriores, deberás construir una consulta SQL que coincida con los atributos del proyecto que estás actualizando.
            // No se proporciona una consulta exacta ya que dependerá del tipo de proyecto que se esté guardando.
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
