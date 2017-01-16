package ws.rest.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract class DictionaryWordMixIn {

    public DictionaryWordMixIn(@JsonProperty("englishWord") String english,
                               @JsonProperty("polishWord") String polish) {
    }

}
