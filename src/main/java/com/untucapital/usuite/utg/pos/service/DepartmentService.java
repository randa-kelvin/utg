package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.dto.request.DepartmentRequestDTO;
import com.untucapital.usuite.utg.dto.response.DepartmentResponseDTO;
import com.untucapital.usuite.utg.pos.model.Department;
import com.untucapital.usuite.utg.pos.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */
@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    //save department
    @Transactional(value = "transactionManager")
    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO request){

        Department department = new Department();
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(request, department);
        Department department1 = departmentRepository.save(department);
        BeanUtils.copyProperties(department1, responseDTO);

        return responseDTO;
    }
    //get department by id
    @Transactional(value = "transactionManager")
    public DepartmentResponseDTO getDepartmentById(Integer departmentId){

        DepartmentResponseDTO response= new DepartmentResponseDTO();
        Department department = departmentRepository.findById(departmentId).orElse(null);
        BeanUtils.copyProperties(department,response);

        return response;
    }
    //get all departments
    @Transactional(value = "transactionManager")
    public List<DepartmentResponseDTO> getAllDepartments(){

        List<DepartmentResponseDTO> response = new ArrayList<DepartmentResponseDTO>();
        List<Department> departmentList = departmentRepository.findAll();

        for(Department department : departmentList){
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
            BeanUtils.copyProperties(department, departmentResponseDTO);

            response.add(departmentResponseDTO);
        }

        return response;
    }
    //update department
    @Transactional(value = "transactionManager")
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO requestDTO){
        Department existingDepartment = departmentRepository.findById(requestDTO.getId()).orElse(null);
        DepartmentResponseDTO response = new DepartmentResponseDTO();

        assert existingDepartment != null;
        existingDepartment.setName(requestDTO.getName());
        Department department = departmentRepository.save(existingDepartment);
        BeanUtils.copyProperties(department, response);

        return response;
    }
    //delete department
    @Transactional(value = "transactionManager")
    public String deleteDepartment(Integer departmentId){
        Department exist = departmentRepository.findById(departmentId).orElse(null);

        if(exist == null){
            return "Department does not exist";
        }

        departmentRepository.delete(exist);
        return "Department deleted successfully";
    }
}
