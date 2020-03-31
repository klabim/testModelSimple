package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaLanguageTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaLanguage.class);
        WaLanguage waLanguage1 = new WaLanguage();
        waLanguage1.setId(1L);
        WaLanguage waLanguage2 = new WaLanguage();
        waLanguage2.setId(waLanguage1.getId());
        assertThat(waLanguage1).isEqualTo(waLanguage2);
        waLanguage2.setId(2L);
        assertThat(waLanguage1).isNotEqualTo(waLanguage2);
        waLanguage1.setId(null);
        assertThat(waLanguage1).isNotEqualTo(waLanguage2);
    }
}
