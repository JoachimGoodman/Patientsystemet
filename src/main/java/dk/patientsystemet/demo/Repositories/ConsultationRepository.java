package dk.patientsystemet.demo.Repositories;

import dk.patientsystemet.demo.Model.Consultation;
import dk.patientsystemet.demo.Model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ConsultationRepository {

    @Autowired
    DBConnect dbConnect;

    private PreparedStatement preparedStatement;

    public void createConsultation(Consultation consultation) throws SQLException {
        String sql = "INSERT INTO consultation (description, conclusion, date, fk_patient, fk_users) VALUES (?, ?, ?, ?, ?)";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, consultation.getDescription());
        preparedStatement.setString(2, consultation.getConclusion());
        preparedStatement.setString(3, "2019-04-04");
        preparedStatement.setInt(4, consultation.getPatientId());
        preparedStatement.setInt(5, consultation.getUserId());
        preparedStatement.execute();
        preparedStatement.close();

    }

    public ResultSet findConsultations(int id) throws SQLException {
        String sql = "SELECT * FROM consultation LEFT JOIN patient ON consultation.fk_patient = patient.id " +
                "LEFT JOIN users ON consultation.fk_users = users.id WHERE consultation.fk_patient=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void deleteConsultation(int id) throws SQLException {
        String sql = "DELETE FROM consultation WHERE id=?";
        preparedStatement = dbConnect.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }



}
