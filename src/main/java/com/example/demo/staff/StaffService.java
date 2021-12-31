package com.example.demo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getStaff() {
        return staffRepository.findAll();
    }

    public void addNewStaff(Staff staff) {
        Optional<Staff> studentOptional = staffRepository.findStaffByEmail(staff.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        staffRepository.save(staff);
    }

    public void deleteStaff(Long staffId) {
        boolean exist = staffRepository.existsById(staffId);
        if (!exist) {
            throw new IllegalStateException("Employee with " + staffId + " ID does not exist");
        }
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public void updateStaff(Long staffId,
                            String name,
                            String email) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new IllegalStateException("Employee with " + staffId + " ID does not exist"));

        if(name != null && name.length() > 0 && !Objects.equals(staff.getName(), name)){
            staff.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(staff.getEmail(), email)){
            Optional<Staff> staffOptional = staffRepository.findStaffByEmail(email);
            if(staffOptional.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            staff.setEmail(email);
        }

    }
}
