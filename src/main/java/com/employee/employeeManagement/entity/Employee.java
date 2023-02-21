package com.employee.employeeManagement.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity

//for single table
/*
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "position",discriminatorType = DiscriminatorType.STRING)
*/

//for table per class
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//for join
//@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    private String employeeName;
   // private String position;
    private String designation;
    private Integer salary;

    //bidirectional
/*    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    List<PhoneNumber> phoneNumbers;*/

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employeeId"),inverseJoinColumns =@JoinColumn(name = "project_id",referencedColumnName = "projectId"))
    public List<Project> projects;

/*
//unidirectional
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    List<PhoneNumber> phoneNumbers;*/

/*    public void addPhoneNumber(PhoneNumber number){
        if (number != null){
            if (phoneNumbers == null){
                phoneNumbers = new ArrayList<>();
            }
            number.setEmployee(this);
            phoneNumbers.add(number);
        }
    }*/
public void addProject(Project project){
    if (project != null){
        if (projects == null){
            projects = new ArrayList<>();
        }
        project.setEmployeeList((List<Employee>) this);
        projects.add(project);
    }
}
}
