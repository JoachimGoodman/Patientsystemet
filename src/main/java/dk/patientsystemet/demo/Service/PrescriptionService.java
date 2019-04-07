package dk.patientsystemet.demo.Service;

import dk.patientsystemet.demo.Model.Medicine;
import dk.patientsystemet.demo.Model.Prescription;
import dk.patientsystemet.demo.Repositories.ConsultationRepository;
import dk.patientsystemet.demo.Repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository db;

    public List<Prescription> findPrescriptionByPatient(int id) {
        ResultSet rs = db.findPrescriptionByPatient(id);
        List<Prescription> prescriptionsList = new ArrayList<>();
        try {
            while (rs.next()) {
                Prescription pre = new Prescription();
                pre.setId(rs.getInt("prescription.id"));
                pre.setDescription(rs.getString("prescription.description"));
                pre.setDate(rs.getString("prescription.date"));
                prescriptionsList.add(pre);
            }
            return prescriptionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prescription> findPrescriptionById(int id) {
        ResultSet rs = db.findPrescriptionByPatient(id);
        List<Prescription> prescriptionsList = new ArrayList<>();
        try {
            while (rs.next()) {
                Prescription pre = new Prescription();
                pre.setId(rs.getInt("prescription.id"));
                pre.setDescription(rs.getString("prescription.description"));
                pre.setDate(rs.getString("prescription.date"));
                prescriptionsList.add(pre);
            }
            return prescriptionsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Medicine> findMedicineByPrescription(int id) {
        ResultSet rs = db.findMedicineByPrescription(id);
        List<Medicine> medicineList = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine med = new Medicine();
                med.setId(rs.getInt("medicine.id"));
                med.setName(rs.getString("medicine.name"));
                medicineList.add(med);
            }
            return medicineList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<Medicine> getAllMedicine() {
        ResultSet rs = db.getAllMedicine();
        List<Medicine> medicineList = new ArrayList<>();
        try {
            while (rs.next()) {
                Medicine med = new Medicine();
                med.setId(rs.getInt("medicine.id"));
                med.setName(rs.getString("medicine.name"));
                medicineList.add(med);
            }
            return medicineList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createPrescription(Prescription prescription) {
        db.createPrescription(prescription);
        //return "success";
    }
}
