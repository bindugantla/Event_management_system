package org.jsp.event.controller;

import java.util.List;
import java.util.Optional;

import org.jsp.event.entity.Event;
import org.jsp.event.repository.EventRepository;
import org.jsp.event.responsestructure.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventController {
	@Autowired
	EventRepository repo;

//	@GetMapping("/hi")
//	public String hi() {
//		return "hello from event app";
//	}

	@PostMapping
	public ResponseStructure<Event> saveEvent(@RequestBody Event event) {
		// when we post the values id is not given in the request body event but
		// after saving event consist id thats why we do reinisializarion the entity
		// object.

		event = repo.save(event);
		ResponseStructure<Event> structure = new ResponseStructure<Event>();
		structure.setHttpStatus(200);
		structure.setMessage("Event Saved Successfully");
		structure.setBody(event);
		return structure;

	}

//	@PutMapping
//	public Event updateEvent(@RequestBody Event event) {
//		return repo.save(event);
//	}

//	@GetMapping
//	public List<Event> findAll() {
//		
//		return repo.findAll();
//	}

	@GetMapping
	public ResponseStructure<List<Event>> findAll() {

		List<Event> el = repo.findAll();
		ResponseStructure<List<Event>> structure = new ResponseStructure<>();
		if (el.isEmpty()) {

			structure.setHttpStatus(404);
			structure.setMessage("No events found in the Database");
			structure.setBody(el);
			return structure;
		}

		structure.setHttpStatus(200);
		structure.setMessage("All Events Found Successfully");
		structure.setBody(el);
		return structure;

//		if(el.size()>0) {
//			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
//			structure.setHttpStatus(200);
//			structure.setMessage("AllEvents Found Successfully");
//			structure.setBody(el);
//			return structure;	
//		}
//		else {
//			ResponseStructure<List<Event>> structure=new ResponseStructure<>();
//			structure.setHttpStatus(404);
//			structure.setMessage("No events found in the Database");
//			structure.setBody(el);
//			return structure;	
//		}

	}

	@GetMapping("/{id}")
	public ResponseStructure<Event> findEventById(@PathVariable int id) {

		Optional<Event> optional = repo.findById(id);
		ResponseStructure<Event> structure = new ResponseStructure<Event>();

		if (optional.isEmpty()) {
			structure.setHttpStatus(404);
			structure.setMessage("Invalid Event id unable to find");
			structure.setBody(null);
			return structure;
		}
		structure.setHttpStatus(200);
		structure.setMessage("Event Found Successfully");
		structure.setBody(optional.get());
		return structure;
	}

	

	@GetMapping("/guest/{guest}")
	public ResponseStructure<List<Event>> findEventsByGuest(@PathVariable String guest) {

		List<Event> gl = repo.findByGuest(guest);
		ResponseStructure<List<Event>> structure = new ResponseStructure<List<Event>>();
		if (gl.isEmpty()) {

			structure.setHttpStatus(404);
			structure.setMessage("Event is not found by that guest");
			structure.setBody(gl);
			return structure;
		}
		structure.setHttpStatus(200);
		structure.setMessage("Event find successfully by guest");
		structure.setBody(gl);
		return structure;

//		List<Event> events = new ArrayList<>();
//		List<Event> el = repo.findAll();
//
//		for (Event e : el) {
//			if (e.getGuest().equalsIgnoreCase(guest)) {
//				events.add(e);
//			}
//		}
//		return events;

	}

	@GetMapping("/title/{title}")
	public ResponseStructure<List<Event>> findEventByTitle(@PathVariable String title) {

		List<Event> el = repo.findByTitle(title);
		ResponseStructure<List<Event>> structure=new ResponseStructure<List<Event>>();
		if(el.isEmpty()) {
			structure.setHttpStatus(404);
			structure.setMessage("Event is not present by that title");
			structure.setBody(el);
			return structure;
		}
       structure.setHttpStatus(200);
       structure.setMessage("event is find Successfully by the title");
       structure.setBody(el);
       return structure;
//	    List<Event> el = repo.findAll();
//		for (Event e : el) {
//			if(e.getTitle().equalsIgnoreCase(title)) {
//				events.add(e);
//			}
//			
//		}
//		return el;

	}
}
