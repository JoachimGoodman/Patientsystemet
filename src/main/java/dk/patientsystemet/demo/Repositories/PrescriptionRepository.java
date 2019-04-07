package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import dk.patientsystemet.demo.Model.Prescription;
import java.sql.*;

@Repository
public class PrescriptionRepository {

    @Autowired
    DBConnect dbConnect;

    private PreparedStatement preparedStatement;

    public ResultSet findPrescriptionByPatient(int id) {
        try {
            String sql = "SELECT * FROM prescription " +
                    "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                    "INNER JOIN users ON prescription.fk_users = users.id " +
                    "WHERE prescription.fk_patient = ?";

            /*String sql =    "SELECT * FROM junction_prescription_and_medicine " +
                            "INNER JOIN prescription ON junction_prescription_and_medicine.fk_prescription = prescription.id " +
                            "INNER JOIN medicine ON junction_prescription_and_medicine.fk_medicine = medicine.id " +
                            "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                            "INNER JOIN users ON prescription.fk_users = users.id " +
                            "WHERE prescription.fk_patient = ?";*/
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet findPrescriptionById(int id) {
        try {
            String sql = "SELECT * FROM prescription " +
                    "INNER JOIN patient ON prescription.fk_patient = patient.id " +
                    "INNER JOIN users ON prescription.fk_users = users.id " +
                    "WHERE prescription.id = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet findMedicineByPrescription(int id) {
        try {
            String sql = "SELECT * FROM junction_prescription_and_medicine " +
                    "INNER JOIN medicine ON junction_prescription_and_medicine.fk_medicine = medicine.id " +
                    "WHERE fk_prescription = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void createPrescription(Prescription prescription) {
        try {
            String sql = "INSERT INTO prescription (description, fk_patient, fk_users) VALUES (?, ?, ?)";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, prescription.getDescription());
            preparedStatement.setInt(2, prescription.getPatientId());
            preparedStatement.setInt(3, prescription.getDoctorId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void editPrescription() {

    }
    public void deletePrescription() {

    }
    public ResultSet getAllMedicine() {
        try {
            String sql = "SELECT * FROM medicine";
            preparedStatement = dbConnect.getConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
