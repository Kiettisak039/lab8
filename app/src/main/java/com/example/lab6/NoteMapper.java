package com.example.lab6;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
public class NoteMapper {

    static Gson gson = new Gson();

    // OOP -> Entity
    public static NoteEntity toEntity(Note note) {
        if (note instanceof TextNote) {
            return new NoteEntity(note.title, "text", null, ((TextNote) note).getTextContent(),note. createdDate);
        }
        /*else if (note instanceof ChecklistNote) {
            String jsonItems = gson.toJson(((ChecklistNote) note).getItems());
            return new NoteEntity(note.title, "checklist", jsonItems, null, note.createdDate);
        }*/
        return null;
    }

    // Entity -> OOP
    public static Note fromEntity(NoteEntity entity) {
        if (entity.type.equals("text")) {
            TextNote textNote = new TextNote();
            textNote .title = entity.title;
            textNote.setTextContent(entity.content);
            textNote.createdDate = (entity.createdDate);
            return textNote;
        } /*else if (entity.type.equals("checklist")) {
            List<String> items = gson.fromJson(entity.checklistItemsJson, new TypeToken<List<String>>(){}.getType());
            return new ChecklistNote(entity.title, entity.createdDate, items);
        }*/
        return null;
    }
}
