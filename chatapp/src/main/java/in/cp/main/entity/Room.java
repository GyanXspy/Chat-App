package in.cp.main.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection  = "rooms")
@Data
public class Room {

	@Id
	private String id;
	private String roomId;
	private List<Message> message = new ArrayList<Message>();
}
