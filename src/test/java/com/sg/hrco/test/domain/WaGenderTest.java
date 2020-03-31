package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaGenderTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaGender.class);
        WaGender waGender1 = new WaGender();
        waGender1.setId(1L);
        WaGender waGender2 = new WaGender();
        waGender2.setId(waGender1.getId());
        assertThat(waGender1).isEqualTo(waGender2);
        waGender2.setId(2L);
        assertThat(waGender1).isNotEqualTo(waGender2);
        waGender1.setId(null);
        assertThat(waGender1).isNotEqualTo(waGender2);
    }
}
