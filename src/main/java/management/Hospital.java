package management;

import java.util.ArrayList;

public class Hospital {
    private ArrayList<Department> departments = new ArrayList<Department>();

    public Hospital() {
    }

    public void add(Department d) {
        departments.add(d);
        addUniqueIdToDepartment(d);
    }

    public void remove(Department d) {
        departments.remove(d);
    }

    public void assign(Patient p, Department d) {
        if (departments.contains(d)) {
            d.add(p);
        }
    }

    public void assign(Staff s, Department d) {
        if (departments.contains(d)) {
            d.add(s);
        }
    }

    public void move(Patient p, Department d1, Department d2) {
        if (d1.getPatients().contains(p) && !(d2.getPatients().contains(p))) {
            d2.add(p);
            d1.remove(p);
        }
    }

    public void move(Staff s, Department d1, Department d2) {
        if (d1.getStaff().contains(s) && !(d2.getStaff().contains(s))) {
            d2.add(s);
            d1.remove(s);
        }
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    private void addUniqueIdToDepartment(Department department) {
        InformationGenerator.generateUniqueID(department);
    }

}
