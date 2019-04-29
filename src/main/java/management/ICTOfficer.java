package management;

import exceptions.FormatException;
import storage.Dao;
import storage.DaoStaffImpl;

import java.util.Date;

public class ICTOfficer extends Staff implements IRegistering<Staff>, IChangeInformation {
    private final Dao<Staff> dao = new DaoStaffImpl<>();

    /**
     * @param person with all members not null
     * @param department to add person to
     * @return boolean representing if the registering was successful
     */
    public boolean registerPerson(Staff person, Department department) {
        // Check that the person is not registered
        if (isPersonRegistered(person, department)) {
            return false;
        }

        // Give unique id
        addUniqueIdToPerson(person);

        // Give person email
        String email = InformationGenerator.generateEmail(person);
        person.setEmail(email);

        // Add person to database
        dao.save(person);
        department.getStaff().add(person);
        return true;
    }

    public boolean isPersonRegistered(Staff person, Department department) {
        // Search for same Unique ID
        for (Person staff : department.getStaff()) {
            // TODO: Optimize find functionality, now is O(n)
            if (staff.getUniqueId() == person.getUniqueId()) {
                return true;
            }
        }
        return false;
    }

    public void setDoctorInformation(Doctor doctor, Speciality speciality, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        doctor.setSpeciality(speciality);

        setPersonInformation(doctor, name, surname, birthdate, gender, homeAddress, phoneNumber);
    }

    private void addUniqueIdToPerson(Person person) {
        person.setUniqueId(InformationGenerator.generateUniqueID());
    }

    @SuppressWarnings("Duplicates")
    public void setPersonInformation(Person person, String name, String surname, Date birthdate, int gender,
                                     String homeAddress, int phoneNumber) {
        setPersonName(person, name);
        setPersonSurname(person, surname);
        setPersonBirthdate(person, birthdate);
        setPersonGender(person, gender);
        setPersonHomeAddress(person, homeAddress);
        setPersonPhoneNumber(person, phoneNumber);
    }

    public boolean setPersonPhoneNumber(Person person, int phoneNumber) {
        try {
            person.setPhoneNumber(phoneNumber);
            return true;
        } catch (FormatException e) {
            return false;
        }
    }

    public void setPersonHomeAddress(Person person, String homeAddress) {
        person.setHomeAddress(homeAddress);
    }

    public void setPersonGender(Person person, int gender) {
        person.setGender(gender);
    }

    public void setPersonBirthdate(Person person, Date birthdate) {
        person.setBirthdate(birthdate);
    }

    public void setPersonSurname(Person person, String surname) {
        person.setSurname(surname);
    }

    public void setPersonName(Person person, String name) {
        person.setName(name);
    }
}
