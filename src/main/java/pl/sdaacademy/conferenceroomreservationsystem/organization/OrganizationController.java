package pl.sdaacademy.conferenceroomreservationsystem.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
class OrganizationController {

    private final OrganizationService organizationService;

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

}
