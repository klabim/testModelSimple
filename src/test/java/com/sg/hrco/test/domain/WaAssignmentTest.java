package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaAssignmentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaAssignment.class);
        WaAssignment waAssignment1 = new WaAssignment();
        waAssignment1.setId(1L);
        WaAssignment waAssignment2 = new WaAssignment();
        waAssignment2.setId(waAssignment1.getId());
        assertThat(waAssignment1).isEqualTo(waAssignment2);
        waAssignment2.setId(2L);
        assertThat(waAssignment1).isNotEqualTo(waAssignment2);
        waAssignment1.setId(null);
        assertThat(waAssignment1).isNotEqualTo(waAssignment2);
    }
}
