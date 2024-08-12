package ec.edu.espol.proyectosbd2p;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import ec.edu.espol.proyectosbd2p.modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private Proyecto proyecto;

    @FXML
    private void initialize() {
        // Inicialización de campos si es necesario
    }

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
        // Ocultar o mostrar campos según el tipo de proyecto
        if (proyecto instanceof Publicidad) {
            txtIdDepCreativo.setVisible(true);

            if (proyecto instanceof PublicidadAnuncioCanal) {
                txtDuracion.setVisible(true);
            } else if (proyecto instanceof PublicidadAnuncioWeb) {
                txtTamanoBanner.setVisible(true);
            }
        }
        if (proyecto instanceof ProductoTienda) {
            txtCategoria.setVisible(true);
            txtPrecio.setVisible(true);
            txtIdDepProd.setVisible(true);
        }
        if (proyecto instanceof Segmento) {
            txtRating.setVisible(true);
            txtDuracion.setVisible(true);
            chkEstado.setVisible(true);
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

            if (proyecto instanceof PublicidadAnuncioCanal) {
                ((PublicidadAnuncioCanal) proyecto).setDuracion(Integer.parseInt(txtDuracion.getText()));
            } else if (proyecto instanceof PublicidadAnuncioWeb) {
                ((PublicidadAnuncioWeb) proyecto).setTamanoBanner(txtTamanoBanner.getText());
            }
        }
        if (proyecto instanceof ProductoTienda) {
            ((ProductoTienda) proyecto).setCategoria(txtCategoria.getText());
            ((ProductoTienda) proyecto).setPrecio(Integer.parseInt(txtPrecio.getText()));
            ((ProductoTienda) proyecto).setIdDepProd(txtIdDepProd.getText());
        }
        if (proyecto instanceof Segmento) {
            ((Segmento) proyecto).setRating(txtRating.getText());
            ((Segmento) proyecto).setDuracion(Integer.parseInt(txtDuracion.getText()));
            ((Segmento) proyecto).setEstado(chkEstado.isSelected());
            ((Segmento) proyecto).setIdDepProd(txtIdDepProd.getText());
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = generarConsultaSQL();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Asigna valores a los parámetros de la consulta SQL según el tipo de proyecto
            asignarParametros(pstmt);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Éxito", "Datos actualizados correctamente.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No se pudo actualizar los datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error de Base de Datos", e.getMessage());
        }
    }

    private String generarConsultaSQL() {
        if (proyecto instanceof PublicidadAnuncioCanal) {
            return "UPDATE publicidad_anuncio_canal SET titulo = ?, presupuesto = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, RUC = ?, numFactura = ?, id_dep_creativo = ?, comisionAEmpresa = ?, duracion = ? WHERE idProyecto = ?";
        } else if (proyecto instanceof PublicidadAnuncioWeb) {
            return "UPDATE publicidad_anuncio_web SET titulo = ?, presupuesto = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, RUC = ?, numFactura = ?, id_dep_creativo = ?, comisionAEmpresa = ?, tamanoBanner = ? WHERE idProyecto = ?";
        } else if (proyecto instanceof ProductoTienda) {
            return "UPDATE producto_tienda SET titulo = ?, presupuesto = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, RUC = ?, numFactura = ?, categoria = ?, precio = ?, id_dep_prod = ?, comisionAEmpresa = ? WHERE idProyecto = ?";
        } else if (proyecto instanceof Segmento) {
            return "UPDATE segmento SET titulo = ?, presupuesto = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, RUC = ?, numFactura = ?, rating = ?, duracion = ?, estado = ?, id_dep_prod = ?, comisionAEmpresa = ? WHERE idProyecto = ?";
        } else if (proyecto instanceof Publicidad) {
            return "UPDATE publicidad SET titulo = ?, presupuesto = ?, descripcion = ?, fechaInicio = ?, fechaFin = ?, RUC = ?, numFactura = ?, id_dep_creativo = ?, comisionAEmpresa = ? WHERE idProyecto = ?";
        } else {
            return "";
        }
    }

    private void asignarParametros(PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, proyecto.getTitulo());
        pstmt.setInt(2, proyecto.getPresupuesto());
        pstmt.setString(3, proyecto.getDescripcion());
        pstmt.setDate(4, java.sql.Date.valueOf(dpFechaInicio.getValue()));
        pstmt.setDate(5, java.sql.Date.valueOf(dpFechaFin.getValue()));
        pstmt.setString(6, proyecto.getRuc());
        pstmt.setString(7, proyecto.getNumFactura());

        if (proyecto instanceof Publicidad) {
            pstmt.setString(8, ((Publicidad) proyecto).getIdProyecto());
            pstmt.setDouble(9, proyecto.getComisionAEmpresa());

            if (proyecto instanceof PublicidadAnuncioCanal) {
                pstmt.setInt(10, ((PublicidadAnuncioCanal) proyecto).getDuracion());
                pstmt.setString(11, proyecto.getIdProyecto());
            } else if (proyecto instanceof PublicidadAnuncioWeb) {
                pstmt.setString(10, ((PublicidadAnuncioWeb) proyecto).getTamanoBanner());
                pstmt.setString(11, proyecto.getIdProyecto());
            } else {
                pstmt.setString(10, proyecto.getIdProyecto());
            }
        } else if (proyecto instanceof ProductoTienda) {
            pstmt.setString(8, ((ProductoTienda) proyecto).getCategoria());
            pstmt.setInt(9, ((ProductoTienda) proyecto).getPrecio());
            pstmt.setString(10, ((ProductoTienda) proyecto).getIdDepProd());
            pstmt.setDouble(11, proyecto.getComisionAEmpresa());
            pstmt.setString(12, proyecto.getIdProyecto());
        } else if (proyecto instanceof Segmento) {
            pstmt.setString(8, ((Segmento) proyecto).getRating());
            pstmt.setInt(9, ((Segmento) proyecto).getDuracion());
            pstmt.setBoolean(10, ((Segmento) proyecto).isEstado());
            pstmt.setString(11, ((Segmento) proyecto).getIdDepProd());
            pstmt.setDouble(12, proyecto.getComisionAEmpresa());
            pstmt.setString(13, proyecto.getIdProyecto());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}