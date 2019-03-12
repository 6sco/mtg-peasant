package com.mtgpeasant.set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtgpeasant.gather.model.Set;
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

import java.util.Collections;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SetController.class)
@RunWith(SpringRunner.class)
@AutoConfigureRestDocs
public class SetControllerTest {

    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SetService setService;

    @InjectMocks
    @Autowired
    private SetController setController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getSets_documentation() throws Exception {

        Set set = Set.builder().name("Tenth Edition").code("10E").type("core").releaseDate("2007-07-13").build();

        Mockito.when(setService.getSets("Tenth Edition", "10E")).thenReturn(Collections.singletonList(set));

        mockMvc.perform(RestDocumentationRequestBuilders.get("/sets?name={name}&code={code}", "Tenth Edition", "10E"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"sets\":[{\"code\":\"10E\",\"releaseDate\":\"2007-07-13\",\"name\":\"Tenth Edition\",\"type\":\"core\"}]}"))
                .andDo(document("sets",
                        requestParameters(
                                parameterWithName("name").optional().description("The set name.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false)),
                                parameterWithName("code").description("The set code.")
                                        .attributes(key("type").value("string"))
                                        .attributes(key("required").value(false))),
                        responseFields(
                                fieldWithPath("sets[]code").description("The code name of the set."),
                                fieldWithPath("sets[]releaseDate").description("When the set was released (YYYY-MM-DD). For promo sets, the date the first card was released."),
                                fieldWithPath("sets[]name").description("The name of the set."),
                                fieldWithPath("sets[]type").description("Type of set. One of: “core”, “expansion”, “reprint”, “box”, “un”, “from the vault”, “premium deck”, “duel deck”, “starter”, “commander”, “planechase”, “archenemy”, “promo”, “vanguard”, “masters”"))));
    }
}
