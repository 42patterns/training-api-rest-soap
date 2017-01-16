package ws.rest.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.module.SimpleModule;
import ws.libs.dictionary.DictionaryWord;

import java.io.IOException;

public class DictionaryWordModule extends SimpleModule {

    public DictionaryWordModule() {
        this.addDeserializer(DictionaryWord.class, new DictionaryWordDeserializer());
    }

    public class DictionaryWordDeserializer extends JsonDeserializer<DictionaryWord> {
        @Override
        public DictionaryWord deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            ObjectCodec oc = jsonParser.getCodec();
            JsonNode node = oc.readTree(jsonParser);

            return new DictionaryWord(node.get("englishWord").textValue(),
                    node.get("polishWord").textValue());
        }
    }
}
