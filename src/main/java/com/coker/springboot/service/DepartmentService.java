package com.coker.springboot.service;

import com.coker.springboot.dto.DepartmentDTO;
import com.coker.springboot.dto.PageDTO;
import com.coker.springboot.dto.SearchDTO;
import com.coker.springboot.model.Department;
import com.coker.springboot.repository.DepartmentRepo;
import jakarta.persistence.NoResultException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    CacheManager cacheManager;
    ModelMapper modelMapper = new ModelMapper();


    @Cacheable(cacheNames = "departmentList")
    public List<DepartmentDTO> findAll(){
        List<Department> departments = departmentRepo.findAll();
        return departments.stream().map(u -> convert(u)).collect(Collectors.toList());
    }
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "department_Search",allEntries = true),
            @CacheEvict(cacheNames = "departmentList")
    },
    put = {
            @CachePut(cacheNames = "department",key = "#departmentDTO.id"),
    })
    public void update(DepartmentDTO departmentDTO) {
        Department department = departmentRepo.findById(departmentDTO.getId()).orElse(null);
        if(department != null) {
            department.setName(department.getName());
        }

    }
    @Transactional
    @CacheEvict(cacheNames = "department_Search", allEntries = true)
    public void create(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        departmentRepo.save(department);
    }

    @Transactional
    @Caching( evict = {
            @CacheEvict(cacheNames = "department",key = "id"),
            @CacheEvict(cacheNames = "department_Search",allEntries = true)
    })
    public void delete(int id) {
    departmentRepo.delete(departmentRepo.getById(id) );
    }

    @Cacheable(cacheNames = "department", key = "#id",unless = "#result == null")
    public DepartmentDTO getById(int id) {
        System.out.println("Chua co cache");
       Department department =  departmentRepo.findById(id).orElseThrow(NoResultException::new);
       return convert(department);
    }
    @Transactional
    @Cacheable(cacheNames = "department_Search")
    public PageDTO<List<DepartmentDTO>> searchByName(SearchDTO searchDTO) {
        Sort sort = Sort.by("id").ascending();
        if (StringUtils.hasText(searchDTO.getSortedByColumn())){
            sort = Sort.by(searchDTO.getSortedByColumn()).ascending();
        }
        if(searchDTO.getCurrentPage() == null){
            searchDTO.setCurrentPage(0);
        }
        if(searchDTO.getSize() == null){
            searchDTO.setSize(10);
        }
        if(searchDTO.getKeyword() == null){
            searchDTO.setKeyword("");
        }

        PageRequest pageRequest = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize());
        Page<Department> departmentPage = departmentRepo.searchByName("%" +searchDTO.getKeyword()+"%",pageRequest);

        PageDTO<List<DepartmentDTO>> pageDTO = new PageDTO<>();
        pageDTO.setTotalPage(departmentPage.getTotalPages());
        pageDTO.setTotalElement(departmentPage.getTotalElements());

        List<DepartmentDTO> departmentDTOS = departmentPage.get().map(u -> convert(u)).collect(Collectors.toList());

        pageDTO.setData(departmentDTOS);

        return pageDTO;
    }
    private DepartmentDTO convert(Department department){
        return new ModelMapper().map(department, DepartmentDTO.class);
    }
}
