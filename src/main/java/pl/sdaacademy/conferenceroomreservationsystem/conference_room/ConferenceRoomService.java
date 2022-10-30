package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sdaacademy.conferenceroomreservationsystem.SortType;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConferenceRoomService {
    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomMapper conferenceRoomMapper;

    List<ConferenceRoomDTO> getAll(SortType sortType) {
        if (sortType != null) {
            List<ConferenceRoomDTO> result = conferenceRoomRepository.findAll(sortType.getSort("name"))
                    .stream()
                    .map(conferenceRoom -> conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoom))
                    .collect(Collectors.toList());
            return result;
        } else {
            List<ConferenceRoomDTO> result = conferenceRoomRepository.findAll()
                    .stream()
                    .map(conferenceRoom -> conferenceRoomMapper.mapConferenceRoomToConferenceRoomDTO(conferenceRoom))
                    .collect(Collectors.toList());
            return result;
        }
    }
}
