package org.lasencinas.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TopicService {
	
	private List<Topic> topics = new ArrayList(Arrays.asList(
				new Topic("spring", "Spring Framework", "Spring Framework Description"),
				new Topic("java", "Core Java", "Core Java Description"),
				new Topic("javascript", "Javascript", "Javascript Description")
				));
	

	public List<Topic> getAllTopics(){
		return topics;
	}
	
	public Topic getTopic(String id ) {
		return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		
	}

	public void addTopic(Topic topic) {
		topics.add(topic);
		
	}

	public void updateTopic(String id, Topic topic) {
		for(int i = 0; i < topics.size(); i++) {
			Topic t= topics.get(i);
			if (t.getId().equals(id)) {
				topics.set(i ,topic);
				return;
			}
		}
		
	}

	public void deteteTopic(String id) {
		 topics.removeIf(t -> t.getId().equals(id));
	}
}
