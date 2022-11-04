package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;
import pl.sdaacademy.conferenceroomreservationsystem.organization.Organization;
import pl.sdaacademy.conferenceroomreservationsystem.organization.OrganizationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;
    private final OrganizationRepository organizationRepository;

    ConferenceRoomDTO add(ConferenceRoomRequest conferenceRoomRequest) {
        Organization organization = organizationRepository.findById(conferenceRoomRequest.getOrganization().getId())
                .orElseThrow(() -> new IllegalArgumentException("Can't find provided organization!"));

        ConferenceRoom conferenceRoom = conferenceRoomMapper.mapConferenceRoomRequestToConferenceRoom(conferenceRoomRequest);
        conferenceRoom.setOrganization(organization);
        return conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoomRepository.save(conferenceRoom));
    }

    ConferenceRoomDTO update(ConferenceRoomRequest conferenceRoomRequest) {
        final String updatedName = conferenceRoomRequest.getName();
        final String updatedIdentifier = conferenceRoomRequest.getIdentifier();
        final Integer updatedLevel = conferenceRoomRequest.getLevel();
        final Boolean updatedAvailability = conferenceRoomRequest.getAvailability();
        final Integer updatedNumberOfSeats = conferenceRoomRequest.getNumberOfSeats();
        final Organization updatedOrganization = conferenceRoomRequest.getOrganization();//TODO analyze whats need to be done
        ConferenceRoom conferenceRoomToUpdate = conferenceRoomRepository.findById(conferenceRoomRequest.getId())
                .map(cr -> {
                    cr.setName(updatedName != null ? updatedName : cr.getName());
                    cr.setIdentifier(updatedIdentifier != null ? updatedIdentifier : cr.getIdentifier());
                    cr.setLevel(updatedLevel != null ? updatedLevel : cr.getLevel());
                    cr.setAvailability(updatedAvailability != null ? updatedAvailability : cr.getAvailability());
                    cr.setNumberOfSeats(updatedNumberOfSeats != null ? updatedNumberOfSeats : cr.getNumberOfSeats());
                    cr.setOrganization(updatedOrganization != null ? updatedOrganization : cr.getOrganization());
                    return cr;
                })
                .orElseThrow(() -> new NoSuchElementException("Can't update not existing conference room"));

        return conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoomRepository.save(conferenceRoomToUpdate));
    }

    List<ConferenceRoomDTO> getAll() {
        List<ConferenceRoomDTO> result = conferenceRoomRepository.findAll()
                .stream()
                .map(conferenceRoom -> conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoom))
                .collect(Collectors.toList());
        return result;
    }

    ConferenceRoomDTO delete(String id) {
        ConferenceRoom conferenceRoomToDelete = conferenceRoomRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't delete not existing conference room"));

        conferenceRoomRepository.delete(conferenceRoomToDelete);
        return conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoomToDelete);
    }
}
