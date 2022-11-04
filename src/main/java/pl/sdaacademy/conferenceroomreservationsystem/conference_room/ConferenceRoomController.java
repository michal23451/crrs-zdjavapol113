package pl.sdaacademy.conferenceroomreservationsystem.conference_room;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conferencerooms")
@RequiredArgsConstructor
public class ConferenceRoomController {
    private final ConferenceRoomService conferenceRoomService;

    @GetMapping
    List<ConferenceRoomDTO> getAll() {
        return conferenceRoomService.getAll();
    }

    @PostMapping
    ConferenceRoomDTO add(@RequestBody ConferenceRoomRequest conferenceRoomRequest) {
        return conferenceRoomService.add(conferenceRoomRequest);
    }

    @PutMapping
    ConferenceRoomDTO update(@RequestBody ConferenceRoomRequest conferenceRoomRequest) {
        return conferenceRoomService.update(conferenceRoomRequest);
    }

    @DeleteMapping("/{id}")
    ConferenceRoomDTO delete(@PathVariable String id) {
        return conferenceRoomService.delete(id);
    }

}
