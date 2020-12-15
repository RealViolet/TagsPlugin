package me.violet.tags.tag;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Data;
import me.violet.tags.TagsPlugin;
import me.violet.tags.utils.CC;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

@Data
public class Tag {

    public static final Map<String, Tag> TAG_MAP = new HashMap<>();

    private String name;
    private String displayName;

    public Tag(String name) {
        this.name = name;
    }

    public Tag(Document document) {
        name = document.getString("name");
        displayName = document.getString("displayName");
    }

    public static void loadTags() {
        for (Document document : TagsPlugin.getInstance().getMongoManager().getTags().find()) {
            Tag tag = new Tag(document);
            TAG_MAP.put(tag.getName(), tag);
        }
    }

    public static void saveTags() {
        TAG_MAP.values().forEach(Tag::saveTag);
    }

    public static void saveTag(Tag tag) {
        TagsPlugin.getInstance().getMongoManager().getTags()
                .replaceOne(Filters.eq("name", tag.getName()), tag.toBson(),
                        new ReplaceOptions().upsert(true));
    }
    public Document toBson() {
        return new Document()
                .append("name", this.name)
                .append("displayName", this.displayName);
    }

    public void delete() {
        TagsPlugin.getInstance().getMongoManager().getTags().findOneAndDelete(Filters.eq("name", this.name));
        TAG_MAP.remove(this.name);
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return CC.translate(this.displayName);
    }

    public static boolean exists(String name) {
        return TAG_MAP.containsKey(name);
    }

    public static Tag getByName(String name) {
        return TAG_MAP.getOrDefault(name, new Tag(name));
    }
}
