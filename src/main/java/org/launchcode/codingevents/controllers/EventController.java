package org.launchcode.codingevents.controllers;

import jakarta.validation.Valid;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;




@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("events",eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create") // lives at /events/create
    public String renderCreateEventForm(Model model){
        model.addAttribute(new Event());
        model.addAttribute("types",EventType.values());
        return "events/create";
    }


    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("types",EventType.values());
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:/events";

    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null){
            for (int id : eventIds){
                eventRepository.deleteById(id);
            }
        }

        return "redirect:/events";

    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        // controller code will go here
        //Event event = EventData.getByID(eventId);
        model.addAttribute("event", eventRepository.findById(eventId).get());
        model.addAttribute("types",EventType.values());
        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, @ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
//        Event event = EventData.getByID(eventId);
//        event.setName(name);
//        event.setDescription(description);
        if (errors.hasErrors()){
            model.addAttribute("types",EventType.values());
            //model.addAttribute("event", eventRepository.findById(eventId).get());
            model.addAttribute("eventId", eventId);
            return "events/edit";
        }
        Event event = eventRepository.findById(eventId).get();
        event.setContactEmail(newEvent.getContactEmail());
        event.setName(newEvent.getName());
        event.setDescription(newEvent.getDescription());
        event.setType(newEvent.getType());
        eventRepository.save(event);

        return "redirect:/events";
    }
}
