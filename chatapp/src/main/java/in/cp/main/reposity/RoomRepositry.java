package in.cp.main.reposity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.cp.main.entity.Room;
@Repository
public interface RoomRepositry extends MongoRepository<Room, String> {
	
	Room findByRoomId(String roomId);

}
