package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaManagementHrisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaManagementHris.class);
        WaManagementHris waManagementHris1 = new WaManagementHris();
        waManagementHris1.setId(1L);
        WaManagementHris waManagementHris2 = new WaManagementHris();
        waManagementHris2.setId(waManagementHris1.getId());
        assertThat(waManagementHris1).isEqualTo(waManagementHris2);
        waManagementHris2.setId(2L);
        assertThat(waManagementHris1).isNotEqualTo(waManagementHris2);
        waManagementHris1.setId(null);
        assertThat(waManagementHris1).isNotEqualTo(waManagementHris2);
    }
}
