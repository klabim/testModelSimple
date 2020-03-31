package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaHomeCompanyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaHomeCompany.class);
        WaHomeCompany waHomeCompany1 = new WaHomeCompany();
        waHomeCompany1.setId(1L);
        WaHomeCompany waHomeCompany2 = new WaHomeCompany();
        waHomeCompany2.setId(waHomeCompany1.getId());
        assertThat(waHomeCompany1).isEqualTo(waHomeCompany2);
        waHomeCompany2.setId(2L);
        assertThat(waHomeCompany1).isNotEqualTo(waHomeCompany2);
        waHomeCompany1.setId(null);
        assertThat(waHomeCompany1).isNotEqualTo(waHomeCompany2);
    }
}
