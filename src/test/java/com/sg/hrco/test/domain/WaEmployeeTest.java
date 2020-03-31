package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaEmployeeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaEmployee.class);
        WaEmployee waEmployee1 = new WaEmployee();
        waEmployee1.setId(1L);
        WaEmployee waEmployee2 = new WaEmployee();
        waEmployee2.setId(waEmployee1.getId());
        assertThat(waEmployee1).isEqualTo(waEmployee2);
        waEmployee2.setId(2L);
        assertThat(waEmployee1).isNotEqualTo(waEmployee2);
        waEmployee1.setId(null);
        assertThat(waEmployee1).isNotEqualTo(waEmployee2);
    }
}
