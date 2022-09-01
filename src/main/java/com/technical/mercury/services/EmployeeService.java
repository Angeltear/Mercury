package com.technical.mercury.services;

import com.technical.mercury.Configuration.SecurityConfiguration;
import com.technical.mercury.model.*;
import com.technical.mercury.model.Users.User;
import com.technical.mercury.model.Users.UserRoles;
import com.technical.mercury.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private final PayslipRepository payslipRepository;

    @Autowired
    private final PayrollParamsRepository payrollParamsRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final UserRolesRepository userRolesRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAllActive(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return employeeRepository.findAllByEmployeeManagerAndActive(emp, true);
    }

    public void save(Employee employee) {
            employeeRepository.save(employee);
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    //Salary handler
    public EmployeeSalary getSalaryById(Long id) {
        return employeeSalaryRepository.findById(id).orElse(null);
    }

    public List<EmployeeSalary> getSalariesByEmpId(Long empId) {
        return employeeSalaryRepository.findAllByEmployeeId(empId);
    }

    public void saveEmpSalary(EmployeeSalary employeeSalary) {
        employeeSalaryRepository.save(employeeSalary);
    }

    //History handler
    public EmployeeHistory getHistoryById(Long id) {
        return employeeHistoryRepository.findById(id).orElse(null);
    }

    public List<EmployeeHistory> getHistoryByEmpId(Long empId) {
        return employeeHistoryRepository.findAllByEmployeeId(empId);
    }

    public void saveEmpHistory(EmployeeHistory employeeHistory) {
        employeeHistoryRepository.save(employeeHistory);
    }

    //Vacation handler
    public Vacation getVacationById(Long id) {
        return vacationRepository.findById(id).orElse(null);
    }

    public List<Vacation> getVacationByRequestorId(Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return vacationRepository.findAllByEmployeeRequestor(emp);
    }

    public List<Vacation> getVacationByStatusAndRequestorId(String status, Long empId) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return vacationRepository.findAllByStatusAndEmployeeRequestor(status, emp);
    }

    public void saveEmpVacation(Vacation employeeVacation) {
        Employee employee = employeeVacation.getEmployeeRequestor();
        employeeVacation.setEmployeeApprover(employee.getEmployeeManager().getId());
        vacationRepository.save(employeeVacation);
    }

    public List<Vacation> getAllVacations() {
        return vacationRepository.findAll();
    }


    public List<Vacation> getVacationByStatusAndApproverId(String status, Long empId) {
        //  Employee emp = employeeRepository.findById(empId).orElse(null);
        //  return vacationRepository.findAllByStatusAndEmployeeApprover(status, emp != null ? emp.getEmployeeManager().getId() : null);
        return vacationRepository.findAllByStatusAndEmployeeApprover(status, empId);
    }

    public void confirmVacation(String confirmation, Long id) {
        Vacation vacation = vacationRepository.findById(id).orElse(null);
        if (vacation != null) {
            if (confirmation.equals("confirm")) {
                vacation.setStatus("Approved");
            } else if (confirmation.equals("deny")) {
                vacation.setStatus("Declined");
            }
            vacationRepository.save(vacation);
        }
    }

    public void processForMonth(String month, int year) {
        List<Employee> unprocessedEmployees = employeeRepository.findAllNotProcessedForMonth(month, year);
        Double incomeCeiling = payrollParamsRepository.findPayrollParamsByParameterName("IncomeCeiling").getParameterPercentage();
        Double withholds = 0.0;
        List<PayrollParams> payrollParams = new ArrayList<>();

        for (Employee e : unprocessedEmployees) {
            EmployeeSalary employeeSalary = new EmployeeSalary();
            Payslip payslip = new Payslip();
            withholds = 0.0;

            payrollParams = payrollParamsRepository.findPayrollParamsByParameterNameIsNot("IncomeCeiling");
            employeeSalary = employeeSalaryRepository.findEmployeeSalaryByEmployeeIdAndEndPeriodIsNull(e.getId());

            payslip.setEmployee(e);
            payslip.setMonth(month);
            payslip.setYear(year);
            payslip.setBaseSalary(employeeSalary.getSalary());

            for (PayrollParams param : payrollParams) {
                if (param.getParameterName().equals("Flat Tax")) {
                    withholds += employeeSalary.getSalary() * (param.getParameterPercentage() / 100);
                } else {
                    if (employeeSalary.getSalary() >= incomeCeiling) {
                        withholds += incomeCeiling * (param.getParameterPercentage() / 100);
                    } else {
                        withholds += employeeSalary.getSalary() * (param.getParameterPercentage() / 100);
                    }
                }
            }

            payslip.setWithholds(withholds);
            payslip.setReceivable(employeeSalary.getSalary() - withholds);

            payslipRepository.save(payslip);

        }

    }

    public Payslip findPayslip(Long empId, String month, int year) {
        Employee emp = employeeRepository.findById(empId).orElse(null);
        return payslipRepository.findPayslipByEmployeeAndMonthAndYear(emp, month, year);
    }

    public void addUser(Employee employee) {
        SecurityConfiguration securityConfiguration = new SecurityConfiguration();
        User user = userRepository.findByUserName(employee.getEmployeeEmail());
        List<UserRoles> currentRoles = userRolesRepository.findUserRolesByUser(user);

        if (employee.getUsers() != null){
            if (userRepository.findById(employee.getUsers().getId()).isPresent()){
                user = userRepository.findById(employee.getUsers().getId()).orElse(null);
            }
        }


        if (user == null) {
            user = new User();
            user.setPassword(securityConfiguration.getPasswordEncoder().encode(employee.getPassword()));
        }

        user.setUserName(employee.getEmployeeEmail());
        user.setActive(employee.isActive());
        user.setEmployee(employee);

        userRepository.save(user);

        if (currentRoles.size() > 0){
            userRolesRepository.deleteAllByUser(user);
        }

        UserRoles userRoles = new UserRoles();
        userRoles.setEmployee(employee);
        userRoles.setUser(user);
        userRoles.setUserRole("ROLE_EMPLOYEE");
        userRolesRepository.save(userRoles);

        if (employee.isHrRole()) {
            userRoles = new UserRoles();
            userRoles.setEmployee(employee);
            userRoles.setUser(user);
            userRoles.setUserRole("ROLE_EMPLOYEE");
            userRolesRepository.save(userRoles);
            userRoles.setUserRole("ROLE_HR");
            userRolesRepository.save(userRoles);
        }
        if (employee.isAdminRole()) {
            userRoles = new UserRoles();
            userRoles.setEmployee(employee);
            userRoles.setUser(user);
            userRoles.setUserRole("ROLE_ADMIN");
            userRolesRepository.save(userRoles);
        }


    }


}
