package rw.pacis.ne.equipment_distribution_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateEmployeeDTO;
import rw.pacis.ne.equipment_distribution_system.dtos.CreateOrUpdateLaptopDTO;
import rw.pacis.ne.equipment_distribution_system.payload.ApiResponse;
import rw.pacis.ne.equipment_distribution_system.services.IEmployeeService;
import rw.pacis.ne.equipment_distribution_system.services.ILaptopService;
import rw.pacis.ne.equipment_distribution_system.utils.Constants;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin
public class EmployeeController {
    private final IEmployeeService service;


    @Autowired
    public EmployeeController(IEmployeeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getEmployees()));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getPaginated(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.DESC, "id");
        return ResponseEntity.ok(ApiResponse.success(this.service.getEmployeesPaginated(pageable)));
    }


    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateOrUpdateEmployeeDTO dto) {
        return ResponseEntity.ok(ApiResponse.success(this.service.addNewEmployee(dto)));
    }
}
