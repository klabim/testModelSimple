package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaSeniorityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaSeniority.class);
        WaSeniority waSeniority1 = new WaSeniority();
        waSeniority1.setId(1L);
        WaSeniority waSeniority2 = new WaSeniority();
        waSeniority2.setId(waSeniority1.getId());
        assertThat(waSeniority1).isEqualTo(waSeniority2);
        waSeniority2.setId(2L);
        assertThat(waSeniority1).isNotEqualTo(waSeniority2);
        waSeniority1.setId(null);
        assertThat(waSeniority1).isNotEqualTo(waSeniority2);
    }
}
