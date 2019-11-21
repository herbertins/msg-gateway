package br.com.condoux.payload;


import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@Builder
public class MessageOneSignal {

    @JsonProperty("app_id")
    private String appId;

    @JsonProperty("include_player_ids")
    private String[] includePlayerIds;

    @JsonProperty("data")
    private DataInformation data;

    @JsonProperty("headings")
    private ContentMessage headings;

    @JsonProperty("contents")
    private ContentMessage contents;

}
