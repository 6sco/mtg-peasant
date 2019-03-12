package com.mtgpeasant.type;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TypeController.class)
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
public class TypeControllerTest {

    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeService typeService;

    @InjectMocks
    @Autowired
    private TypeController typeController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getTypes_documentation() throws Exception {

        Mockito.when(typeService.getTypes()).thenReturn(Arrays.asList("Artifact", "Conspiracy", "Creature", "Enchantment", "..."));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("types",
                        responseFields(
                                fieldWithPath("types").description("All magic types."))));
    }

    @Test
    public void getSubTypes_documentation() throws Exception {

        Mockito.when(typeService.getSubTypes()).thenReturn(Arrays.asList("Advisor", "Aetherborn", "Ajani", "Alara", "..."));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/subtypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("subtypes",
                        responseFields(
                                fieldWithPath("subtypes").description("All magic subtypes."))));
    }

    @Test
    public void getSupertypes_documentation() throws Exception {

        Mockito.when(typeService.getSuperTypes()).thenReturn(Arrays.asList("Basic", "Host", "Legendary", "Ongoing", "Snow", "World"));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/supertypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("supertypes",
                        responseFields(
                                fieldWithPath("supertypes").description("All magic supertypes."))));
    }
}
