package net.bondar.web.model.dto;

/**
 * Created by AzeraL on 24.12.2015.
 */
public class HobbyDto {
    private String title;
    private String description;

    public HobbyDto() {
    }

    public HobbyDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
