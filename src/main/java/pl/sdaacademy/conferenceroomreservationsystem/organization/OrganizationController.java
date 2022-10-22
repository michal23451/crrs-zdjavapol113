package pl.sdaacademy.conferenceroomreservationsystem.organization;

import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    OrganizationDTO add(@RequestBody OrganizationRequest request) {
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
