package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class VariableCompensationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VariableCompensation.class);
        VariableCompensation variableCompensation1 = new VariableCompensation();
        variableCompensation1.setId(1L);
        VariableCompensation variableCompensation2 = new VariableCompensation();
        variableCompensation2.setId(variableCompensation1.getId());
        assertThat(variableCompensation1).isEqualTo(variableCompensation2);
        variableCompensation2.setId(2L);
        assertThat(variableCompensation1).isNotEqualTo(variableCompensation2);
        variableCompensation1.setId(null);
        assertThat(variableCompensation1).isNotEqualTo(variableCompensation2);
    }
}
