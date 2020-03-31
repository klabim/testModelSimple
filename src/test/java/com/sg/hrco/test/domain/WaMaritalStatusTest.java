package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaMaritalStatusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaMaritalStatus.class);
        WaMaritalStatus waMaritalStatus1 = new WaMaritalStatus();
        waMaritalStatus1.setId(1L);
        WaMaritalStatus waMaritalStatus2 = new WaMaritalStatus();
        waMaritalStatus2.setId(waMaritalStatus1.getId());
        assertThat(waMaritalStatus1).isEqualTo(waMaritalStatus2);
        waMaritalStatus2.setId(2L);
        assertThat(waMaritalStatus1).isNotEqualTo(waMaritalStatus2);
        waMaritalStatus1.setId(null);
        assertThat(waMaritalStatus1).isNotEqualTo(waMaritalStatus2);
    }
}
