package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/organizations")
class OrganizationController {
    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    List<OrganizationDTO> getAll() {
        return organizationService.getAll();
    }

    @PostMapping
    OrganizationDTO add(@Valid @RequestBody OrganizationRequest request) {
        return organizationService.add(request);
    }

    @DeleteMapping("/{organizationId}")
    OrganizationDTO delete(@PathVariable(name = "organizationId") Long organizationId) {
        return organizationService.delete(organizationId);
    }

    @PutMapping
    OrganizationDTO update(@RequestBody OrganizationRequest request) {
        return organizationService.update(request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
