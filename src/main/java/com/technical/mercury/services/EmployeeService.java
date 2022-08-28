package com.technical.mercury.services;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.EmployeeHistory;
import com.technical.mercury.model.EmployeeSalary;
import com.technical.mercury.model.Vacation;
import com.technical.mercury.repository.EmployeeHistoryRepository;
import com.technical.mercury.repository.EmployeeRepository;
import com.technical.mercury.repository.EmployeeSalaryRepository;
import com.technical.mercury.repository.VacationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final EmployeeSalaryRepository employeeSalaryRepository;

    @Autowired
    private final EmployeeHistoryRepository employeeHistoryRepository;

    @Autowired
    private final VacationRepository vacationRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    //Salary handler
    public EmployeeSalary getSalaryById(Long id){ return employeeSalaryRepository.findById(id).orElse(null);}
    public List<EmployeeSalary> getSalariesByEmpId(Long empId) { return employeeSalaryRepository.findAllByEmployeeId(empId);}
    public void saveEmpSalary(EmployeeSalary employeeSalary){
        employeeSalaryRepository.save(employeeSalary);
    }

    //History handler
    public EmployeeHistory getHistoryById(Long id){ return employeeHistoryRepository.findById(id).orElse(null);}
    public List<EmployeeHistory> getHistoryByEmpId(Long empId) { return employeeHistoryRepository.findAllByEmployeeId(empId);}
    public void saveEmpHistory(EmployeeHistory employeeHistory){
        employeeHistoryRepository.save(employeeHistory);
    }

    //Vacation handler
    public Vacation getVacationById(Long id){ return vacationRepository.findById(id).orElse(null);}
    public List<Vacation> getVacationByRequestorId(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return vacationRepository.findAllByEmployeeRequestor(emp);
    }

    public List<Vacation> getVacationByStatusAndRequestorId(String status, Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return vacationRepository.findAllByStatusAndEmployeeRequestor(status, emp);
    }
    public void saveEmpVacation(Vacation employeeVacation){
        Employee employee = employeeVacation.getEmployeeRequestor();
        employeeVacation.setEmployeeApprover(employee.getEmployeeManager().getId());
        vacationRepository.save(employeeVacation);
    }

    public List<Vacation> getAllVacations(){ return vacationRepository.findAll();}


    public List<Vacation> getVacationByStatusAndApproverId(String status, Long empId) {
      //  Employee emp = employeeRepository.findById(empId).orElse(null);
      //  return vacationRepository.findAllByStatusAndEmployeeApprover(status, emp != null ? emp.getEmployeeManager().getId() : null);
        return vacationRepository.findAllByStatusAndEmployeeApprover(status, empId);
    }

    public void confirmVacation(String confirmation, Long id){
        Vacation vacation = vacationRepository.findById(id).orElse(null);
        if (vacation!= null){
            if (confirmation.equals("confirm")){
                vacation.setStatus("Approved");
            }
            else if(confirmation.equals("deny")){
                vacation.setStatus("Declined");
            }
            vacationRepository.save(vacation);
        }
    }



}
