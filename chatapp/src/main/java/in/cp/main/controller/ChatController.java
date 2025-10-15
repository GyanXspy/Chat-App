package in.cp.main.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import in.cp.main.config.AppConstant;
import in.cp.main.entity.Message;
import in.cp.main.entity.Room;
import in.cp.main.payload.MessageRequest;
import in.cp.main.reposity.RoomRepositry;

@Controller
@CrossOrigin(AppConstant.FRONT_END_BASE_URL)
public class ChatController {
	
	private RoomRepositry roomRepositry;
	
	public ChatController(RoomRepositry repositry) {
		this.roomRepositry = repositry;
		
	}
	
	@MessageMapping("/sendMessage/{roomId}")  // /app/sendMessage/{roomId}
	@SendTo("/topic/room/{roomId}")
	public Message sendMessage(
			@RequestBody MessageRequest request,
			@DestinationVariable String roomId
			) {
		
		Room room = roomRepositry.findByRoomId(request.getRoomId());
		Message message = new Message();
		message.setContent(request.getContent());
		message.setSender(request.getSender());
		message.setTimeStamp(LocalDateTime.now());
		
		
		if(room != null) {
			room.getMessage().add(message);
			roomRepositry.save(room);
		}else {
			throw new RuntimeException("Room not found");
		}
		return message;
	}
}
