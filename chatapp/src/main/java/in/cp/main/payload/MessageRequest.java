package in.cp.main.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

	private String content;
	private String sender;
	private String roomId;
	private LocalDateTime messageTime;
}
