package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class FixedCompensationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FixedCompensation.class);
        FixedCompensation fixedCompensation1 = new FixedCompensation();
        fixedCompensation1.setId(1L);
        FixedCompensation fixedCompensation2 = new FixedCompensation();
        fixedCompensation2.setId(fixedCompensation1.getId());
        assertThat(fixedCompensation1).isEqualTo(fixedCompensation2);
        fixedCompensation2.setId(2L);
        assertThat(fixedCompensation1).isNotEqualTo(fixedCompensation2);
        fixedCompensation1.setId(null);
        assertThat(fixedCompensation1).isNotEqualTo(fixedCompensation2);
    }
}
