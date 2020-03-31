package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaHostCompanyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaHostCompany.class);
        WaHostCompany waHostCompany1 = new WaHostCompany();
        waHostCompany1.setId(1L);
        WaHostCompany waHostCompany2 = new WaHostCompany();
        waHostCompany2.setId(waHostCompany1.getId());
        assertThat(waHostCompany1).isEqualTo(waHostCompany2);
        waHostCompany2.setId(2L);
        assertThat(waHostCompany1).isNotEqualTo(waHostCompany2);
        waHostCompany1.setId(null);
        assertThat(waHostCompany1).isNotEqualTo(waHostCompany2);
    }
}
