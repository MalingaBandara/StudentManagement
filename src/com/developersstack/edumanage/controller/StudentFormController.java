package com.developersstack.edumanage.controller;

import com.developersstack.edumanage.entity.Student;
import com.developersstack.edumanage.repo.custom.StudentRepo;
import com.developersstack.edumanage.repo.custom.impl.StudentRepoImpl;
import com.developersstack.edumanage.view.tm.StudentTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class StudentFormController {
    public AnchorPane context;
    public TextField txtId;
    public TextField txtName;
    public DatePicker txtDob;
    public TextField txtAddress;
    public TableView<StudentTm> tblStudents;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colDob;
    public TableColumn colAddress;
    public TableColumn colOption;
    public Button btn;
    public TextField txtSearch;

    String searchText="";

    private StudentRepo studentRepo = new StudentRepoImpl(); // student repo

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setStudentId();
        setTableData(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText=newValue;
            setTableData(searchText);
        });

        tblStudents.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null!=newValue){
                        setData(newValue);
                    }
        });
    }

    private void setData(StudentTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getFullName());
        txtAddress.setText(tm.getAddress());
        txtDob.setValue(LocalDate.parse(tm.getDob(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        btn.setText("Update Student");
    }

    private void setTableData(String searchText) {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();

        try {

                for ( Student st : studentRepo.findAllStudents(searchText) ) {
                        Button btn = new Button("Delete");
                        StudentTm tm = new StudentTm(
                                st.getStudentId(),
                                st.getFullName(),
                                new SimpleDateFormat("yyyy-MM-dd").format(st.getDateOfBirth()),
                                st.getAddress(),
                                btn
                        );

                        btn.setOnAction(e -> {
                            Alert alert = new Alert(
                                    Alert.AlertType.CONFIRMATION,
                                    "Are you sure?",
                                    ButtonType.YES, ButtonType.NO
                            );
                            Optional<ButtonType> buttonType = alert.showAndWait();
                            if (buttonType.get().equals(ButtonType.YES)) {
                                try{

                                    boolean isDeleted = studentRepo.deleteStudent(st.getStudentId());
                                        if (isDeleted){
                                            new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
                                            setStudentId();
                                            setTableData(searchText);
                                        } else {
                                            new Alert(Alert.AlertType.ERROR, "Try Again").show();
                                        }

                                } catch (SQLException | ClassNotFoundException exception) {
                                    exception.printStackTrace();

                                }
                                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                            }
                        });

                        obList.add(tm);

                    }

            tblStudents.setItems(obList);

        } catch(SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();

        }
    }

    // generate student id
    private void setStudentId() {

        try {

            String selectedId = studentRepo.findStudentLastId();

                if ( null != selectedId ) {

                        String splitData[] = selectedId.split("-");
                        String lastIdIntegerNumberAsAString = splitData[1];
                        int lastIntegerIdAsInt=Integer.parseInt(lastIdIntegerNumberAsAString);
                        lastIntegerIdAsInt++;
                        String generatedStudentId="S-"+lastIntegerIdAsInt;
                        txtId.setText(generatedStudentId);

                } else {
                        txtId.setText("S-1");

                }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    // Save / update student
    public void saveOnAction(ActionEvent actionEvent) {

        // create new student
        Student student = new Student(
                txtId.getText(),
                txtName.getText(),
                Date.from(txtDob.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                txtAddress.getText()
        );

            if (btn.getText().equalsIgnoreCase("Save Student")){  // save student

                        try {

                            boolean isSaved = studentRepo.saveStudent(student);

                                if (isSaved){
                                    setStudentId();
                                    clear();
                                    setTableData(searchText);
                                    new Alert(Alert.AlertType.INFORMATION, "Student saved!").show();
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Try Again").show();
                                }

                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.toString()).show();

                        }

            } else { // update student

                        try {

                            Student selectedStudent = studentRepo.findStudent(txtId.getText());

                            if ( null != selectedStudent) {

                                boolean isUpdated = studentRepo.updateStudent(student);

                                    if (isUpdated){
                                        new Alert(Alert.AlertType.INFORMATION, "Updated").show();
                                        setStudentId();
                                        clear();
                                        setTableData(searchText);
                                        btn.setText("Save Student");

                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Try Again").show();
                                    }

                            }


                        } catch (SQLException | ClassNotFoundException e) {
                                new Alert(Alert.AlertType.ERROR, e.toString()).show();

                            }


                }
    }


    private void clear(){
        txtDob.setValue(null);
        //txtName.setText("");
        txtName.clear();
        txtAddress.clear();
    }

    public void newStudentOnAction(ActionEvent actionEvent) {
        clear();
        setStudentId();
        btn.setText("Save Student");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
}
