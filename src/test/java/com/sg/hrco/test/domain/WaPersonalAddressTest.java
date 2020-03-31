package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaPersonalAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaPersonalAddress.class);
        WaPersonalAddress waPersonalAddress1 = new WaPersonalAddress();
        waPersonalAddress1.setId(1L);
        WaPersonalAddress waPersonalAddress2 = new WaPersonalAddress();
        waPersonalAddress2.setId(waPersonalAddress1.getId());
        assertThat(waPersonalAddress1).isEqualTo(waPersonalAddress2);
        waPersonalAddress2.setId(2L);
        assertThat(waPersonalAddress1).isNotEqualTo(waPersonalAddress2);
        waPersonalAddress1.setId(null);
        assertThat(waPersonalAddress1).isNotEqualTo(waPersonalAddress2);
    }
}
