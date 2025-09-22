package com.repsrv.csweb.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repsrv.csweb.core.authorization.model.AuthResponse;
import com.repsrv.csweb.core.authorization.model.DivisionsInfo;
import com.repsrv.csweb.core.authorization.model.SuperUserPermissions;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AuthResponseDeserializer extends JsonDeserializer<AuthResponse> {
    @Override
    public AuthResponse deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException,
            JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode rootNode = mapper.readTree(jsonParser);

        String username = rootNode.get("username").asText();

        List<String> pointers = StreamSupport.stream(rootNode.get("pointers").spliterator(), false)
                .map(JsonNode::asText)
                .map(s -> s == null ? null : s.trim())
                .collect(Collectors.toList());

        SuperUserPermissions superPerms = mapper.treeToValue(rootNode.get("super_perms"), SuperUserPermissions.class);
        List<DivisionsInfo> divisions = mapper.readValue(rootNode.get("divisions").traverse(), 
                mapper.getTypeFactory().constructCollectionType(List.class, DivisionsInfo.class));

        return new AuthResponse(username, pointers, superPerms, divisions);
    }
}
