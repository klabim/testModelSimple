package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaNationalityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaNationality.class);
        WaNationality waNationality1 = new WaNationality();
        waNationality1.setId(1L);
        WaNationality waNationality2 = new WaNationality();
        waNationality2.setId(waNationality1.getId());
        assertThat(waNationality1).isEqualTo(waNationality2);
        waNationality2.setId(2L);
        assertThat(waNationality1).isNotEqualTo(waNationality2);
        waNationality1.setId(null);
        assertThat(waNationality1).isNotEqualTo(waNationality2);
    }
}
