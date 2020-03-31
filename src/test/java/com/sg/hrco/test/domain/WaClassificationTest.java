package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaClassificationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaClassification.class);
        WaClassification waClassification1 = new WaClassification();
        waClassification1.setId(1L);
        WaClassification waClassification2 = new WaClassification();
        waClassification2.setId(waClassification1.getId());
        assertThat(waClassification1).isEqualTo(waClassification2);
        waClassification2.setId(2L);
        assertThat(waClassification1).isNotEqualTo(waClassification2);
        waClassification1.setId(null);
        assertThat(waClassification1).isNotEqualTo(waClassification2);
    }
}
