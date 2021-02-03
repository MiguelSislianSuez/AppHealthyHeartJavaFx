package com.mass.jfxpersonal;

import java.net.URL;
import java.sql.JDBCType;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController implements Initializable {

    @FXML
    private TextField tfNombre;
    private boolean bNombre = false;
    @FXML
    private TextField tfApellido;
    private boolean bApellido = false;

    @FXML
    private TextArea tfDatos;
    private boolean bDatos = false;

    @FXML
    private DatePicker anio;

    @FXML
    TableView<TablaPacientes> tablaPacientes;

    @FXML
    ImageView imagen;

    @FXML
    private TextField url;

    @FXML
    private CheckBox h;
    @FXML
    private CheckBox l;
    @FXML
    private CheckBox g;
    @FXML
    private CheckBox t;
    @FXML
    private CheckBox b;
    @FXML
    private CheckBox i;

    @FXML
    private Button btnGuardar;

    int idPacienteSeleccionado = 0;

    TablaPacientesDao dao;

    @Override
    //con initialize le decimos lo que tiene que hacer al empezar a ejecutar el programa
    public void initialize(URL url, ResourceBundle rb) {
        //Instanciamos y llamamos metodo para crear la tabla si no existe
        dao = new TablaPacientesDao();
        cargarPacientesDB();
        configurarTamanoClumn();
        //Metodo salto de linea
        tfDatos.setWrapText(true);
        //el boton esta deshabilitado por defecto hasta que todos los campos está a true
        //btnGuardar.setDisable(true);

    }

    public void guardarObjPaciente() {
        //Creamos un objeto paciente a partir del fxml y le pasamos todos los datos al metodo guadar
        TablaPacientes paciente = new TablaPacientes();
        paciente.setNombre(tfNombre.getText());
        paciente.setApellido(tfApellido.getText());
        paciente.setAnio(anio.getValue());
        paciente.setDatos(tfDatos.getText());

        paciente.setH(h.isSelected());
        paciente.setL(l.isSelected());
        paciente.setG(g.isSelected());
        paciente.setT(t.isSelected());
        paciente.setB(b.isSelected());
        paciente.setI(i.isSelected());
        paciente.setId(idPacienteSeleccionado);
        paciente.setUrl(url.getText());

        //llamamos a la instancia y le decimos que llame al metodo guardar
        dao.guardarOactualizar(paciente);
        //carga la tabla después de guardar
        cargarPacientesDB();
        //limpiamos campos 
        
        tfNombre.clear();
        tfApellido.clear();
        tfDatos.clear();
        url.clear();
        idPacienteSeleccionado = 0;
    }

    public void eliminar() {
        //creamos el objeto a partir de los datos de la tabla de la bdd a difernecia del metodo guardar
        TablaPacientes paciente = tablaPacientes.getSelectionModel().getSelectedItem();//con estos metodos ya recive los datos de la tabla 
        dao.eliminar(paciente);
        cargarPacientesDB();

    }

    public void editar() {
        //obtenemos objeto de la tabla y lo pone en el fxml
        //Solo queremos que nos muestre los datos
        TablaPacientes paciente = tablaPacientes.getSelectionModel().getSelectedItem();
        tfNombre.setText(paciente.getNombre());
        tfApellido.setText(paciente.getApellido());
        anio.setValue(paciente.getAnio());
        tfDatos.setText(paciente.getDatos());

        h.setSelected(paciente.isH());
        l.setSelected(paciente.isL());
        g.setSelected(paciente.isG());
        t.setSelected(paciente.isT());
        b.setSelected(paciente.isB());
        i.setSelected(paciente.isI());
        idPacienteSeleccionado = paciente.getId();
        url.setText(paciente.getUrl());
        //condicion para imagenes por defecto
        Image img;
        if (paciente.getUrl() != null && !paciente.getUrl().equals("")) {
            //System.out.println(paciente.getUrl());
            img = new Image(paciente.getUrl());
        } else {
            img = new Image("/img/not.png");
        }

        imagen.setImage(img);
        cargarPacientesDB();

    }

    private void cargarPacientesDB() {

        //Creamos nuestro observable list que irá almacenando delFXMLdinamica
        ObservableList<TablaPacientes> pacienteTablaBD = FXCollections.observableArrayList();
        List<TablaPacientes> pacientesEncontradosEnBD = dao.cargarPacientesDB();
        //ahora añadimos todo de encontradosDB a pacientesTablaDb
        pacienteTablaBD.addAll(pacientesEncontradosEnBD);
        System.out.println(pacienteTablaBD);
        tablaPacientes.setItems(pacienteTablaBD);
        //tablaPacientes.setItems(pacienteTablaBD);

    }

    private void configurarTamanoClumn() {
        tablaPacientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<TableColumn<TablaPacientes, ?>> columnas = tablaPacientes.getColumns();
        columnas.get(0).setMaxWidth(1f * Integer.MAX_VALUE * 5);
        columnas.get(1).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(2).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(3).setMaxWidth(1f * Integer.MAX_VALUE * 10);
        columnas.get(4).setMaxWidth(1f * Integer.MAX_VALUE * 45);
        columnas.get(5).setMaxWidth(1f * Integer.MAX_VALUE * 20);

    }

    //metodos enlazados desde el scenBuilder
    public void actNombre() {
        bNombre = !tfNombre.getText().isEmpty();
        // activarDesactivarGuardar();
    }

    public void actApellido() {
        bApellido = !tfApellido.getText().isEmpty();
        //activarDesactivarGuardar();

    }

    public void actDatos() {
        bDatos = !tfDatos.getText().isEmpty();
        //activarDesactivarGuardar();

    }

    public void actAnio() {

        if (!anio.getValue().toString().equals("")) {
            activarDesactivarGuardar();
        }

    }

    public void activarDesactivarGuardar() {
        if ((bNombre == true) && (bApellido == true) && (bDatos == true)) {
            btnGuardar.setDisable(false);
        } else {
            btnGuardar.setDisable(true);

        }
    }
}
