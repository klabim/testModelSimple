package com.sg.hrco.test.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.sg.hrco.test.web.rest.TestUtil;

public class WaJobTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WaJob.class);
        WaJob waJob1 = new WaJob();
        waJob1.setId(1L);
        WaJob waJob2 = new WaJob();
        waJob2.setId(waJob1.getId());
        assertThat(waJob1).isEqualTo(waJob2);
        waJob2.setId(2L);
        assertThat(waJob1).isNotEqualTo(waJob2);
        waJob1.setId(null);
        assertThat(waJob1).isNotEqualTo(waJob2);
    }
}
