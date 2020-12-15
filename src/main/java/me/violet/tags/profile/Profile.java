package me.violet.tags.profile;

import com.avaje.ebean.Update;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.violet.tags.TagsPlugin;
import me.violet.tags.tag.Tag;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Profile {

    private String name;
    private UUID uuid;
    private Tag tag;

    @Getter
    private static Map<UUID, Profile> profiles = new HashMap<>();

    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public void load() {
        Document document = TagsPlugin.getInstance().getMongoManager().getProfiles().find(Filters.eq("uuid", this.uuid.toString())).first();
        if (document != null) { ;
            if (document.getString("tag") == null) {
                tag = null;
            } else {
                tag = Tag.getByName(document.getString("tag"));
            }
        }
        profiles.put(this.uuid, this);
    }

    public void save() {
        Document document = new Document();
        document.append("uuid", this.uuid.toString());
        document.append("name", this.name);
        document.append("tag", hasTag() ? this.tag.getName() : null);
        TagsPlugin.getInstance().getMongoManager().getProfiles().replaceOne(Filters.eq("uuid", this.uuid.toString())
                , document
                , new UpdateOptions().upsert(true));
    }


    public static Profile getByPlayer(Player player) {
        if (profiles.containsKey(player.getUniqueId())) {
            return profiles.get(player.getUniqueId());
        }
        return new Profile(player.getUniqueId(), player.getName());
    }


    public Tag getTag() {
        return this.tag;
    }

    public boolean hasTag() {
        return this.tag != null;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
