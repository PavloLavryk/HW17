package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }
    @GetMapping("/list")
    public String list (Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note-list";
    }
    @PostMapping("/delete")
    public String delete (@RequestParam Long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note-edit";
    }

    @PostMapping("/edit")
    public String update(Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }

}
