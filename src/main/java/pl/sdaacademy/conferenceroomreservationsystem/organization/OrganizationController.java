package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/organizations")
class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }


    @GetMapping
    List<OrganizationDTO> getAll(@RequestParam(required = false) SortType sortType) {
        return organizationService.getAll(sortType);
    }

    @GetMapping("/{name}")
    OrganizationDTO getByName(@PathVariable String name) {
        return organizationService.getByName(name);
    }


    @PostMapping
    OrganizationDTO add(@Valid @RequestBody OrganizationRequest request) { //@Valid - łapiemy wyjątek w @ExceptionHandler(MethodArgumentNotValidException.class)
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
