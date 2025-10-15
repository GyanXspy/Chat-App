package in.cp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.cp.main.config.AppConstant;
import in.cp.main.entity.Message;
import in.cp.main.entity.Room;
import in.cp.main.reposity.RoomRepositry;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin(AppConstant.FRONT_END_BASE_URL)
public class RoomController {
	@Autowired
	private RoomRepositry repositry;

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody String roomId) {
		if(repositry.findByRoomId(roomId)!= null) {
			//Room is already available
			return ResponseEntity.badRequest().body("Room is already exist");
		}
		Room room = new Room();
		room.setRoomId(roomId);
		Room saveRoom = repositry.save(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}
	
	@GetMapping("/{roomId}")
	public ResponseEntity<?> joinRoom(@PathVariable String roomId){
		Room room = repositry.findByRoomId(roomId);
		if(room == null) {
			return ResponseEntity.badRequest().body("Room already exist");
		}
		
//		Create new room
		return ResponseEntity.ok(room);
		
		
	}
	
	
	public ResponseEntity<List<Message>> getMessage(@PathVariable String roomId, 
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "size", defaultValue = "20", required = false) int size
			){
		
		Room room = repositry.findByRoomId(roomId);
		if(room == null) {
			return ResponseEntity.badRequest().build();
		}
		
		List<Message> message = room.getMessage();
		int start = Math.max(0, message.size());
		int end = Math.min(message.size(), start+size);
		List<Message> paginationMessage = message.subList(start, end);
		
		return ResponseEntity.ok(paginationMessage);
		
	}
}
